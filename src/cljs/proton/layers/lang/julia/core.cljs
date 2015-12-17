(ns proton.layers.lang.julia.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod get-initial-config :lang/julia
  []
  [])

(defmethod init-layer! :lang/julia
  [_ {:keys [config layers]}]
  (println "init julia"))

(defmethod get-packages :lang/julia
  []
  [:language-julia
   :latex-completions
   :julia-client
   :ink])

(defmethod describe-mode :lang/julia
 []
 {:mode-name :julia
  :atom-scope ["source.julia"]
  :mode-keybindings
    {:r {:action "julia-client:open-a-repl"
         :title "Open a REPL"}
     :s {:action "julia-client:start-julia"
         :title "Start Julia"}
     :R {:action "julia-client:start-repl-client"
         :title "Start REPL client"}
     :c {:category "clear"
           :c {:action "julia-client:clear-console"
               :title "Console"}
           :i {:action "inline-results:clear-all"
               :title "Inline"}}
     :l {:action "julia-client:reset-loading-indicator"
         :title "Reset indicator"}
     :k {:action "julia-client:kill-julia"
         :title "Kill Julia"}}})

(defmethod get-keymaps :lang/julia [] [])
(defmethod get-keybindings :lang/julia [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-packages :julia [] (get-packages :lang/julia))
(defmethod get-keymaps :julia [] (get-keymaps :lang/julia))
(defmethod get-keybindings :julia [] (get-keybindings :lang/julia))
(defmethod get-initial-config :julia [] (get-initial-config :lang/julia))
(defmethod init-layer! :julia [] (init-layer! :lang/julia))
