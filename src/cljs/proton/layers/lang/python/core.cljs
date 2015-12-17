(ns proton.layers.lang.python.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :lang/python
  [_ config]
  (println "init python"))

(defmethod get-packages :lang/python
  []
  [:autocomplete-python
   :python-yapf
   :linter-pep8])

(defmethod get-keymaps :lang/python [] [])
(defmethod get-initial-config :lang/python [] [])
(defmethod get-keybindings :lang/python [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-packages :python [] (get-packages :lang/python))
(defmethod get-keymaps :python [] (get-keymaps :lang/python))
(defmethod get-keybindings :python [] (get-keybindings :lang/python))
(defmethod get-initial-config :python [] (get-initial-config :lang/python))
(defmethod init-layer! :python [] (init-layer! :lang/python))
(defmethod describe-mode :lang/python [] {})
