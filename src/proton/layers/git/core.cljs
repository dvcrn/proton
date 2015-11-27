(ns proton.layers.git.core
  (:use [proton.layers.base :only [get-keybindings get-packages get-keymaps]]))

(defmethod get-keybindings :git
  []
  {:g {:category "git"
       :a {:action "git-plus:add"}
       :S {:action "git-plus:status"}
       :s {:action "git-plus:stage-files"}
       :P {:action "git-plus:push"}
       :c {:action "git-plus:commit"}}})

(defmethod get-packages :git
  []
  [:git-plus])

(defmethod get-keymaps :git
  []
  [])
