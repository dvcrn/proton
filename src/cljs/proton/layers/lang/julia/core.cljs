(ns proton.layers.lang.julia.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod get-initial-config :lang/julia
  []
  [])

(defmethod init-layer! :lang/julia
  [_ config]
  (println "init julia"))

(defmethod get-packages :lang/julia
  []
  [:language-julia
   :latex-completions
   :julia-client
   :ink])

(defmethod get-keymaps :lang/julia [] [])
(defmethod get-keybindings :lang/julia [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-keymaps :julia [] (get-keymaps :lang/julia))
(defmethod get-keybindings :julia [] (get-keybindings :lang/julia))
(defmethod get-initial-config :julia [] (get-initial-config :lang/julia))
(defmethod init-layer! :julia [] (init-layer! :lang/julia))
