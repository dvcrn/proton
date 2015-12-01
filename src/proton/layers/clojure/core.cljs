(ns proton.layers.clojure.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :clojure
  [_ config]
  (println "init clojure"))

(defmethod get-initial-config :clojure [] [])

(defmethod get-keybindings :clojure
  []
  {})

(defmethod get-packages :clojure
  []
  [:Parinfer])

(defmethod get-keymaps :clojure
  []
  [])
