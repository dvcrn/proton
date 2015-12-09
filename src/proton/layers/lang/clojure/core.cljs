(ns proton.layers.lang.clojure.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :lang/clojure
  [_ config]
  (println "init clojure"))

(defmethod get-packages :lang/clojure
  []
  [:Parinfer])

(defmethod get-keymaps :lang/clojure [] [])
(defmethod get-initial-config :lang/clojure [] [])
(defmethod get-keybindings :lang/clojure [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-keymaps :clojure [] (get-keymaps :lang/clojure))
(defmethod get-keybindings :clojure [] (get-keybindings :lang/clojure))
(defmethod get-initial-config :clojure [] (get-initial-config :lang/clojure))
(defmethod init-layer! :clojure [] (init-layer! :lang/clojure))
