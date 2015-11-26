(ns proton.layers.git.core
  (:use [proton.layers.base :only [get-keybindings get-packages]]))

(defmethod get-keybindings :git
  []
  {:g {:category "git"
       :s {:action "git-plus:status"}
       :c {:action "git-plus:commit"}}})

(defmethod get-packages :git
  []
  [:git-plus])
