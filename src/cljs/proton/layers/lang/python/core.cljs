(ns proton.layers.lang.python.core
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.mode :as mode]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode register-layer-dependencies init-package]]))

(defmethod init-layer! :lang/python
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/python)
  (register-layer-dependencies :tools/linter [:linter-flake8]))

(defmethod init-package [:lang/python :autocomplete-python] []
  (mode/define-package-mode :autocomplete-python
    {:mode-keybindings
     {:g {:category "goto"
          :g {:action "autocomplete-python:go-to-definition" :target actions/get-active-editor :title "definition"}}}})
  (mode/link-modes :python-major-mode (mode/package-mode-name :autocomplete-python)))

(defmethod init-package [:lang/python :python-yapf] []
  (mode/define-package-mode :python-yapf
    {:mode-keybindings
      {:= {:action "python-yapf:formatCode" :target actions/get-active-editor :title "yapf format file"}}})
  (mode/link-modes :python-major-mode (mode/package-mode-name :python-yapf)))

(defmethod init-package [:lang/python :python-tools] []
  (mode/define-package-mode :python-tools
    {:mode-keybindings
      {:g {:category "goto"
           :u {:action "python-tools:show-usages" :target actions/get-active-editor :title "show usages"}}
       :S {:action "python-tools:select-all-string" :target actions/get-active-editor}}})
  (mode/link-modes :python-major-mode (mode/package-mode-name :python-tools)))

(defmethod init-package [:lang/python :python-isort] []
  (mode/define-package-mode :python-isort
    {:mode-keybindings
      {:r {:category "refactor"
           :i {:category "imports"
               :s {:action "python-isort:sortImports" :target actions/get-active-editor :title "sort imports"}}}}})
  (mode/link-modes :python-major-mode (mode/package-mode-name :python-isort)))

(defmethod get-packages :lang/python []
  [:autocomplete-python
   :python-tools
   :python-isort
   :python-yapf])

(defmethod describe-mode :lang/python []
  {:mode-name :python-major-mode
   :atom-grammars ["Python"]
   :atom-scope ["source.python"]})

(defmethod get-keymaps :lang/python [] [])
(defmethod get-initial-config :lang/python [] [])
(defmethod get-keybindings :lang/python [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-packages :python [] (get-packages :lang/python))
(defmethod get-keymaps :python [] (get-keymaps :lang/python))
(defmethod get-keybindings :python [] (get-keybindings :lang/python))
(defmethod get-initial-config :python [] (get-initial-config :lang/python))
(defmethod init-layer! :python [] (init-layer! :lang/python))
