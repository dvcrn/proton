(ns proton.layers.lang.clojure.core
  (:require [proton.lib.mode :as mode :refer [define-mode define-keybindings]]
            [proton.lib.atom :as atom-env :refer [set-grammar]])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :lang/clojure
  [_ config]
  ()
  (mode/define-mode :clojure
     :atom-grammars ["Clojure"]
     :file-extensions [#"\.proton$"]
     :init (fn []
            (atom-env/set-grammar "Clojure")))
  (mode/define-keybindings :clojure
    {:t {:category "toggles"
         :p {:action "parinfer:toggleMode" :title "Toggle Parinfer Mode"}}})
  (println "init clojure"))

(defmethod get-packages :lang/clojure
  []
  [:Parinfer])

(defmethod get-keymaps :lang/clojure [] [])
(defmethod get-initial-config :lang/clojure [] [])
(defmethod get-keybindings :lang/clojure [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-packages :clojure [] (get-packages :lang/clojure))
(defmethod get-keymaps :clojure [] (get-keymaps :lang/clojure))
(defmethod get-keybindings :clojure [] (get-keybindings :lang/clojure))
(defmethod get-initial-config :clojure [] (get-initial-config :lang/clojure))
(defmethod init-layer! :clojure [] (init-layer! :lang/clojure))
