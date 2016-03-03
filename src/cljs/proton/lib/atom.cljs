(ns proton.lib.atom
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [proton.lib.helpers :refer [generate-div process->html deep-merge console!]]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]
            [cljs.core.async :as async :refer [chan >!]]))

;; reference to atom shell API
(def ashell (node/require "atom"))

;; get atom.CompositeDisposable so we can work with it
(def composite-disposable (.-CompositeDisposable ashell))
(def buffered-process (.-BufferedProcess ashell))

;; Initialise new composite-disposable so we can add stuff to it later
(def subscriptions (new composite-disposable))

(def commands (.-commands js/atom))
(def workspace (.-workspace js/atom))
(def keymaps (.-keymaps js/atom))
(def views (.-views js/atom))
(def config (.-config js/atom))
(def grammars (.-grammars (.-grammars js/atom)))
(def workspace-view (.getView views workspace))
(def packages (.-packages js/atom))

(def element (atom (generate-div "test" "proton-which-key")))
(def bottom-panel (atom (.addBottomPanel workspace
                                       (clj->js {:visible false
                                                  :item @element}))))

(def last-action (atom {}))
(def modal-element (atom (generate-div "test" "proton-modal-panel")))
(def modal-panel (atom (.addModalPanel workspace (clj->js {:visible false
                                                           :item @modal-element}))))

(defn update-bottom-panel [html] (aset @element "innerHTML" html))
(defn update-modal-panel [html]
  (let [element @modal-element]
    (aset element "innerHTML" html)
    (aset element "scrollTop" (.-scrollHeight element))))

(defn show-modal-panel [] (.show @modal-panel))
(defn hide-modal-panel [] (.hide @modal-panel))
(defn show-bottom-panel [] (.show @bottom-panel))
(defn hide-bottom-panel [] (.hide @bottom-panel))

(defn get-apm-path []
  (.getApmPath packages))

(defn get-config [selector]
  (.get config selector))

(defn set-config!
  ([selector value]
   (set-config! selector value {}))
  ([selector value opts]
   (console! (str "Setting " selector " to " (clj->js value) " options: " (clj->js opts)))
   (.set config selector (clj->js value) (clj->js opts))))

(defn add-to-config! [selector value]
  (let [previous-config (js->clj (.get config selector))]
    (set-config! selector (conj previous-config value))))

(defn remove-val-from-config! [selector value]
  (let [config (js->clj (.get config selector))
        new-config (filter #(not (= value %)) config)]
      (set-config! selector new-config)))

(defn unset-config! [selector]
  (.unset config selector))

(def steps (atom []))

(defn reset-process-steps! [] (reset! steps []))
(defn insert-process-step!
  ([text] (insert-process-step! text "<span class='proton-status-progress'>[..]</span>"))

  ([text status]
   (swap! steps conj [text status])
   (update-modal-panel (process->html @steps))))

(defn amend-last-step!
  ([text] (amend-last-step! text "<span class='proton-status-progress'>[..]</span>"))

  ([text status]
   (swap! steps #(conj (into [] (butlast %)) [text status]))
   (update-modal-panel (process->html @steps))))

(defn mark-last-step-as-completed! []
  (amend-last-step! (str (get (last @steps) 0)) "<span class='proton-status-ok'>[ok]</span>"))

(defn input-provider-class []
  (if-let [selected-provider (get-config "proton.core.vim-provider")]
    (do
      (case selected-provider
        "vim-mode" ["vim-mode"]
        "vim-mode-plus" ["vim-mode-plus"]))
    [""]))

(defn editor-toggle-classes [class-list remove?]
  (let [editors (.getTextEditors workspace)]
    (doseq [editor editors]
      (let [editor-view (.getView views editor)
            classList (.-classList editor-view)
            class-list-fn (fn [class-name] (if remove? (.remove classList class-name) (.add classList class-name)))]
        (doall (map class-list-fn class-list))))))

(defn activate-proton-mode! []
  (console! "Chain activated!")
  (let [classList (.-classList workspace-view)]
    (.add classList "proton-mode")
    (editor-toggle-classes (input-provider-class) true)
    (show-bottom-panel)))

(defn deactivate-proton-mode! []
  (console! "Chain deactivated!")
  (let [classList (.-classList workspace-view)]
    (.remove classList "proton-mode")
    (editor-toggle-classes (input-provider-class) false)
    (hide-bottom-panel)))

(defn is-proton-mode-active? []
  (let [classList (array-seq (.-classList workspace-view))]
    (not (nil? (some #{"proton-mode"} classList)))))

(defn eval-action! [{:keys [action target fx] :as command-map}]
  (let [selector (when (string? target) (js/document.querySelector target))
        dom-target (if (nil? target) (.getView views workspace) (or selector (target js/atom)))]

    ;; functions always go first
    (if (not (nil? fx))
      (fx)
      (do
        (console! (str "Dispatching: " action))
        (reset! last-action command-map)
        (.dispatch commands dom-target action)))
    (deactivate-proton-mode!)))

(defn eval-actions! [action-vector]
  (doall (map eval-action! action-vector))
  (reset! last-action action-vector))

(defn eval-last-action! []
  (if (vector? @last-action)
    (eval-actions! @last-action)
    (eval-action! @last-action)))

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


(defn set-keymap!
  ([selector bindings]
   (set-keymap! selector bindings 0))
  ([selector bindings priority]
   (let [binding-map (reduce deep-merge (map #(hash-map (get % 0) (get % 1)) bindings))
         selector-bound-map (hash-map selector binding-map)]
    (.add keymaps "custom-keymap" (clj->js selector-bound-map) priority))))

(defn clear-keymap! []
  (.removeBindingsFromSource keymaps "custom-keymap"))

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

(defn is-activated? [package-name]
  (.isPackageActive packages package-name))

(defn is-package-disabled? [package-name]
  (.isPackageDisabled packages package-name))

(defn get-all-packages []
  (.getAvailablePackageNames packages))

(defn get-package [package-name]
  (.getLoadedPackage packages package-name))

(defn is-package-installed? [package-name]
  (if (.isPackageLoaded packages package-name)
    true
    (let [pkgs (get-all-packages)]
      (not (= -1 (.indexOf pkgs package-name))))))

(defn is-package-bundled? [package-name]
  (.isBundledPackage packages (name package-name)))

(defn enable-package [package-name]
  (console! (str "enabling package " (name package-name)))
  (.enablePackage packages package-name))


(defn disable-package
  ([package-name]
   (disable-package package-name false))

  ([package-name force]
   (if (or (is-activated? package-name) force)
       (do
         (when-not (is-package-disabled? package-name)
          (do
            (console! (str "disabling package " package-name))
            (.disablePackage packages package-name)))))))

(defn is-activated? [package-name]
  (.isPackageActive packages package-name))

(defn reload-package [package-name]
  (disable-package package-name)
  (enable-package package-name))

(defn force-reload-package [package-name]
  (disable-package package-name true)
  (enable-package package-name))

(defn buffered-process-> [command args]
  (let [out-chan (chan)]
    (new buffered-process
      (clj->js
        { :command command
          :args args
          :stdout #(go (>! out-chan {:stdout %}))
          :stderr #(when-not (nil? %) (go (>! out-chan {:stderr %})))}))
    out-chan))
