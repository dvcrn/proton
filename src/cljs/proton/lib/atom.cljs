(ns proton.lib.atom
  (:require [proton.lib.helpers :refer [generate-div process->html deep-merge]]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]))

(def commands (.-commands js/atom))
(def workspace (.-workspace js/atom))
(def keymaps (.-keymaps js/atom))
(def views (.-views js/atom))
(def config (.-config js/atom))
(def grammars (.-grammars (.-grammars js/atom)))

(def element (atom (generate-div "test" "proton-which-key")))
(def bottom-panel (atom (.addBottomPanel workspace
                                       (clj->js {:visible false
                                                  :item @element}))))

(def modal-element (atom (generate-div "test" "proton-modal-panel")))
(def modal-panel (atom (.addModalPanel workspace (clj->js {:visible false
                                                           :item @modal-element}))))

(defn update-bottom-panel [html] (aset @element "innerHTML" html))
(defn update-modal-panel [html] (aset @modal-element "innerHTML" html))
(defn show-modal-panel [] (.show @modal-panel))
(defn hide-modal-panel [] (.hide @modal-panel))
(defn show-bottom-panel [] (.show @bottom-panel))
(defn hide-bottom-panel [] (.hide @bottom-panel))

(def steps (atom []))
(defn reset-process-steps! [] (reset! steps []))
(defn insert-process-step!
  ([text] (insert-process-step! text "[..]"))

  ([text status]
   (swap! steps conj [text status])
   (update-modal-panel (process->html @steps))))

(defn amend-last-step!
  ([text] (amend-last-step! text "[..]"))

  ([text status]
   (swap! steps #(conj (into [] (butlast %)) [text status]))
   (update-modal-panel (process->html @steps))))

(defn mark-last-step-as-completed! []
  (amend-last-step! (str (get (last @steps) 0)) "[ok]"))

(defn activate-proton-mode! []
  (.log js/console "[proton] Proton Chain activated!")
  (let [editors (.getTextEditors workspace)]
      (doseq [editor editors]
        (let [view (.getView views editor)
              classList (.-classList view)]
            (.remove classList "vim-mode")
            (.remove classList "vim-mode-plus")
            (.add classList "proton-mode")
            (show-bottom-panel)))))

(defn deactivate-proton-mode! []
  (.log js/console "[proton] Proton Chain deactivated!")
  (let [editors (.getTextEditors workspace)]
      (doseq [editor editors]
        (let [view (.getView views editor)
              classList (.-classList view)]
            (.remove classList "proton-mode")
            (.add classList "vim-mode")
            (.add classList "vim-mode-plus")
            (hide-bottom-panel)))))

(defn eval-action! [tree sequence]
  (let [action (get-in tree (conj sequence :action))
        target (get-in tree (conj sequence :target))
        selector (when (string? target) (js/document.querySelector target))
        fx (get-in tree (conj sequence :fx))
        dom-target (if (nil? target) (.getView views workspace) (or selector (target js/atom)))]

    ;; functions always go first
    (if (not (nil? fx))
      (fx)
      (do
        (.log js/console (str "[proton] Dispatching " action " to "))
        (.log js/console dom-target)
        (.dispatch commands dom-target action)))
    (deactivate-proton-mode!)))

(defn get-all-settings []
  (let [config-obj (.getAll config)
        parsed-config (atom [])]
    ;; First level are different selectors. The most interesting one being '*'
    (goog.object/forEach config-obj
      (fn [subobj _ _]
        ;; second level are the actual config objects inside the selectorso
        ;; .value holds the actual configuration object for the selector
        (goog.object/forEach (.-value subobj)
          (fn [obj config-prefix _]
            ;; third level is the actual config string. Like core.{xxx}
            (goog.object/forEach obj
              (fn [val config-postfix _]
                (swap! parsed-config conj (str config-prefix "." config-postfix))))))))
    @parsed-config))

(defn set-config! [selector value]
  (.log js/console (str "[proton] Setting " selector " to " (clj->js value)))
  (.set config selector (clj->js value)))

(defn add-to-config! [selector value]
  (let [previous-config (js->clj (.get config selector))]
    (set-config! selector (conj previous-config value))))

(defn remove-val-from-config! [selector value]
  (let [config (js->clj (.get config selector))
        new-config (filter #(not (= value %)) config)]
      (set-config! selector new-config)))

(defn unset-config! [selector]
  (.unset config selector))

(defn set-keymap! [selector bindings]
  (let [binding-map (reduce deep-merge (map #(hash-map (get % 0) (get % 1)) bindings))
        selector-bound-map (hash-map selector binding-map)]
    (.add keymaps "custom-keymap" (clj->js selector-bound-map))))

(defn get-active-editor []
  (if-let [editor (.getActiveTextEditor workspace)]
   (if-not (.isMini editor) editor nil)
   nil))

(defn find-grammar-by-name [name]
 (first (filter #(= (.-name %) name) grammars)))

(defn set-grammar [grammar]
  (if-let [editor (get-active-editor)]
    (if (string? grammar)
     (.setGrammar editor (find-grammar-by-name grammar))
     (.setGrammar editor grammar))))
