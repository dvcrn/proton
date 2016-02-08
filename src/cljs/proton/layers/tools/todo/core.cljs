(ns proton.layers.tools.todo.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/todo
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/todo))

(defmethod get-keybindings :tools/todo
  []
  {:p {:category "project"
       :T {:action "todo-show:find-in-project"
           :title "find project TODOs"}}
   :b {:category "buffer"
       :T {:action "todo-show:find-in-open-files"
           :title "find buffer TODOs"}}})

(defmethod get-packages :tools/todo
  []
  [:todo-show])

(defmethod get-keymaps :tools/todo [] [])
(defmethod get-initial-config :tools/todo [] [])
(defmethod describe-mode :tools/todo [] {})
