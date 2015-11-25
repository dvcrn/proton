(ns proton.layers.core.core
  (:use [proton.layers.base :only [get-keybindings get-packages]]))

(defmethod get-keybindings :core
  []
  {:g {:category "git"
       :s {:action "git_status"}
       :c {:action "git_commit"}
       :p {:action "git_push"}
       :P {:action "git_pull"}}
   :w {:category "window"
       :m {:action "maximise"}}
   :b {:category "buffer"
       :m {:action "maximise"}}
   :p {:category "project"
       :t {:action "tree-view:toggle"}}})


(defmethod get-packages :core
  []
  [:vim-mode :foo-mode])
