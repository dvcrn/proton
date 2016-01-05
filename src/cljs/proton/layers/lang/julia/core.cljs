(ns proton.layers.lang.julia.core
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod get-initial-config :lang/julia
  []
  [])

(defmethod init-layer! :lang/julia
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/julia))

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
     :m {:action "julia-client:set-working-module"
         :target actions/get-active-editor
         :title "Set working module"}
     :d {:action "julia:open-startup-file"
         :title "Open juliarc"}
     :R {:action "julia-client:start-repl-client"
         :title "Start REPL client"}
     :f {:category "Choose folder"
              :f {:action "julia-client:work-in-file-folder"
                  :target actions/get-active-editor
                  :title "File"}
              :h {:action "julia-client:work-in-home-folder"
                  :title "Home"}
              :p {:action "julia-client:work-in-project-folder"
                  :target actions/get-active-editor
                  :title "Project"}}
     :c {:category "clear"
           :c {:action "julia-client:clear-console"
               :title "Console"}
           :i {:action "inline-results:clear-all"
               :target actions/get-active-editor
               :title "Inline"}}
     :e {:category "evaluate"
           :b {:action "julia-client:evaluate"
               :target actions/get-active-editor
               :title "Block"}
           :a {:action "julia-client:evaluate-all"
               :target actions/get-active-editor
               :title "All"}}
     :t {:category "toggles"
           :c {:action "julia-client:toggle-console"
               :title "console"}
           :l {:action "julia-client:reset-loading-indicator"
               :title "Loading indicator"}
           :d {:action "julia-client:toggle-documentation"
               :target actions/get-active-editor
               :title "Documentation"}
           :m {:action "julia-client:toggle-methods"
               :target actions/get-active-editor
               :title "Methods"}}
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
