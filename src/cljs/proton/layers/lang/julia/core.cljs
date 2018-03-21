(ns proton.layers.lang.julia.core
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.helpers :as helpers]
            [proton.lib.mode :as mode])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode init-package]]))

(defmethod get-initial-config :lang/julia
  []
  [["julia-client.juliaOptions.bootMode" "Server"]])

(defmethod init-layer! :lang/julia
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/julia))

(defmethod get-packages :lang/julia
  []
  [:language-julia
   :latex-completions
   :julia-client
   :ink])

(defmethod init-package [:lang/julia :julia-client] []
  (helpers/console! "init julia-client package" :lang/julia)
  (mode/define-package-mode :julia-client
    {:mode-keybindings
      {:r {:action "julia-client:open-a-repl"
           :title "Open a REPL"}
       :s {:action "julia-client:start-julia"
           :title "Start Julia"}
       :m {:action "julia-client:set-working-module"
           :target actions/get-active-editor
           :title "Set working module"}
       :d {:action "julia-client:toggle-documentation"
           :target actions/get-active-editor
           :title "Documentation"}
       :R {:action "julia-client:start-repl-client"
           :title "Start REPL client"}
       :f {:category "file/folder"
           :d {:action "julia:open-startup-file"
               :title "Open juliarc"}
           :f {:action "julia-client:work-in-file-folder"
               :target actions/get-active-editor
               :title "Work in file folder"}
           :h {:action "julia-client:work-in-home-folder"
               :title "Work in home folder"}
           :p {:action "julia-client:work-in-project-folder"
               :target actions/get-active-editor
               :title "Work in project folder"}
           :w {:action "julia-client:select-working-folder"
               :title "Choose working folder"}
           :j {:action "julia:open-julia-home"
               :title "Open Julia home"}
           :k {:action "julia:open-package-in-new-window"
               :title "Open package in new window"}}
       :c {:category "clear"
           :c {:action "julia-client:clear-console"
               :title "Console"}}
       :e {:category "evaluate"
           :e {:action "julia-client:run-and-move"
               :target actions/get-active-editor
               :title "Block and move"}
           :b {:action "julia-client:run-block"
               :target actions/get-active-editor
               :title "Block"}
           :a {:action "julia-client:run-file"
               :target actions/get-active-editor
               :title "All"}}
       :l {:action "julia-client:reset-loading-indicator"
           :title "Reset indicator"}
       :k {:category "kill/break"
           :k {:action "julia-client:kill-julia"
               :title "Kill"}
           :r {:action "julia-client:reset-julia-server"
               :title "Reset"}
           :i {:action "julia-client:interrupt-julia"
               :title "Interrupt"}}
       :w {:category "workspace"
           :c {:action "julia-client:open-console"
               :title "Open console"}
           :p {:action "julia-client:open-plot-pane"
               :title "Open plot pane"}
           :w {:action "julia-client:open-workspace"
               :title "Open workspace"}
           :r {:action "julia-client:reset-workspace"
               :title "Reset workspace"}}}})
  (mode/link-modes :julia-major-mode (mode/package-mode-name :julia-client)))

(defmethod init-package [:lang/julia :ink] []
  (helpers/console! "init ink package" :lang/julia)
  (mode/define-package-mode :inline-results
    {:mode-keybindings
      {:c {:a {:action "inline-results:clear-all"
               :target actions/get-active-editor
               :title "All results"}
           :r {:action "inline:clear-current"
               :target actions/get-active-editor
               :title "Result"}}}})
  (mode/link-modes :julia-major-mode (mode/package-mode-name :inline-results)))

(defmethod describe-mode :lang/julia
 []
 {:mode-name :julia-major-mode
  :atom-scope ["source.julia"]})

(defmethod get-keymaps :lang/julia []
  [{:selector "atom-text-editor.vim-mode-plus.normal-mode"
    :keymap [["escape" "inline:clear-current"]]}
   {:selector "atom-text-editor.vim-mode.normal-mode"
    :keymap [["escape" "inline:clear-current"]]}])

(defmethod get-keybindings :lang/julia [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-packages :julia [] (get-packages :lang/julia))
(defmethod get-keymaps :julia [] (get-keymaps :lang/julia))
(defmethod get-keybindings :julia [] (get-keybindings :lang/julia))
(defmethod get-initial-config :julia [] (get-initial-config :lang/julia))
(defmethod init-layer! :julia [] (init-layer! :lang/julia))
