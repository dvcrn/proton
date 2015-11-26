(ns proton.layers.core.core
  (:use [proton.layers.base :only [get-keybindings get-packages]]))

(defmethod get-keybindings :core
  []
  {:w {:category "window"
       :m {:action "maximise"}}
   :b {:category "buffer"
       :m {:action "maximise"}}
   :p {:category "project"
       :t {:action "tree-view:toggle"}}})


(defmethod get-packages :core
  []
  [:vim-mode :foo-mode])
