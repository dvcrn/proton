(ns proton.lib.atom
  (:require [proton.lib.helpers :refer [generate-div]]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]))

(def commands (.-commands js/atom))
(def workspace (.-workspace js/atom))
(def keymaps (.-keymaps js/atom))
(def views (.-views js/atom))


(def element (atom (generate-div "test")))
(def modal-panel (atom (.addBottomPanel workspace
                                       (clj->js {:visible false
                                                  :item @element}))))

(defn insert-html [html]
  (aset @element "innerHTML" html))

(defn show-panel []
  (.show @modal-panel))

(defn hide-panel []
  (.hide @modal-panel))

(defn activate-proton-mode! []
  (.log js/console "----> Proton Chain activated!")
  (let [editors (.getTextEditors workspace)]
      (doseq [editor editors]
        (let [view (.getView views editor)
              classList (.-classList view)]
            (.remove classList "vim-mode")
            (.add classList "proton-mode")
            (show-panel)))))

(defn deactivate-proton-mode! []
  (.log js/console "----> Proton Chain deactivated!")
  (let [editors (.getTextEditors workspace)]
      (doseq [editor editors]
        (let [view (.getView views editor)
              classList (.-classList view)]
            (.remove classList "proton-mode")
            (.add classList "vim-mode")
            (hide-panel)))))

(defn eval-action! [tree sequence]
  (println "evalling")
  (let [action (get-in tree (conj sequence :action))]
    (.dispatch commands (.getView views workspace) action)
    (deactivate-proton-mode!)))
