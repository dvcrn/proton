(ns proton.layers.python.core
  (:use [proton.layers.base :only [get-keybindings get-packages get-keymaps]]))

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
