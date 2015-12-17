(ns proton.layers.tools.linter.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(def layer-state (atom {}))

(def linter-keymap
  {:e {:category "errors"
         :t {:action "linter:toggle"
             :title "toggle linter"}
         :n {:action "linter:next-error"
             :title "next error"}
         :p {:action "linter:previous-error"
             :title "previous error"}
         :l {:action "linter:lint"
             :title "lint now"}
         :T {:action "linter:togglePanel"
             :title "toggle panel"}}})

(def nuclide-keymap
  {:e {:category "errors"
         :s {:action "nuclide-diagnostics-ui:toggle-table"
             :title "show errors"
             :target (fn [atom] (.getView (.-views atom) (.-workspace atom)))}}})

(defmethod init-layer! :tools/linter
  [_ config]
  (println "init linter")
  (let [config-map (into (hash-map) config)]
    (swap! layer-state assoc :provider (config-map "proton.linter.provider"))))

(defmethod get-initial-config :tools/linter []
  [["proton.linter.provider" :linter]])

(defmethod get-packages :tools/linter []
  (case (@layer-state :provider)
    :nuclide [:nuclide-diagnostics-ui :nuclide-diagnostics-store]
    :atom [:linter]))

(defmethod get-keybindings :tools/linter [] []
  (case (@layer-state :provider)
    :atom linter-keymap
    :nuclide nuclide-keymap))

(defmethod describe-mode :tools/linter [] {})
(defmethod get-keymaps :tools/linter [] [])
