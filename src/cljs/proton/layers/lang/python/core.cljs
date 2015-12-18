(ns proton.layers.lang.python.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode register-layer-dependencies]]))

(register-layer-dependencies :tools/linter [:linter-pep8])

(defmethod init-layer! :lang/python
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/python))

(defmethod get-packages :lang/python []
  [:autocomplete-python
   :python-yapf])

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
