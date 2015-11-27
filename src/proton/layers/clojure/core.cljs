(ns proton.layers.clojure.core
  (:use [proton.layers.base :only [get-keybindings get-packages]]))

(defmethod get-keybindings :clojure
  []
  {})

(defmethod get-packages :clojure
  []
  [:Parinfer])
