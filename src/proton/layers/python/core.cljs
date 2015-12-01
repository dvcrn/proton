(ns proton.layers.python.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :python
  [_ config]
  (println "init python"))

(defmethod get-initial-config :python [] [])

(defmethod get-keybindings :python
  []
  {})

(defmethod get-packages :python
  []
  [:autocomplete-python
   :python-yapf])

(defmethod get-keymaps :python
  []
  [])
