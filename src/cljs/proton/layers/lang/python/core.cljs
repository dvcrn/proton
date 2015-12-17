(ns proton.layers.lang.python.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(def packages (atom [:autocomplete-python :python-yapf]))

(defmethod init-layer! :lang/python
  [_ {:keys [config layers]}]
  (println "init python")
  (if (contains? (set layers) :tools/linter)
    (swap! packages conj :linter-pep8)))

(defmethod get-packages :lang/python [] @packages)

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
