(ns proton.layers.lang.csharp.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :as helpers :refer [console!]]
            [proton.lib.atom :as atom-env]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base :only [init-layer! get-packages get-initial-config get-keybindings get-keymaps register-layer-dependencies describe-mode init-package]]))

(defmethod get-initial-config :lang/csharp []
  [["omnisharp-atom.enableAdvancedFileNew" false]
   ["omnisharp-atom.useIcons" false]])

(defmethod init-layer! :lang/csharp []
  (helpers/console! "init" :lang/csharp))

(defmethod get-packages :lang/csharp []
  [:atom-yeoman
   :json-schema
   :omnisharp-atom])

(defmethod init-package [:lang/csharp :omnisharp-atom] []
  (helpers/console! "init omnisharp package")
  (mode/define-package-mode :omnisharp-atom
    {:mode-keybindings
      {:g {:category "goto"
           :g {:title "definition"
               :action "omnisharp-atom:go-to-definition"
               :target actions/get-active-editor}
           :u {:title "show usages"
               :action "omnisharp-atom:find-usages"
               :target actions/get-active-editor}
           :t {:title "type lookup"
               :action "omnisharp-atom:type-lookup"
               :target actions/get-active-editor}}
       :r {:category "refactor"
           :u {:title "fix usings"
               :action "omnisharp-atom:fix-usings"
               :target actions/get-active-editor}
           :r {:title "rename"
               :action "omnisharp-atom:rename"
               :target actions/get-active-editor}}}})
      ;;
      ;; Couldn't get OmniSharp's test runner to work
      ;; will add this back once I get it working
      ;;
      ;  :t {:category "tests"
      ;      :a {:title "run all"
      ;          :action "omnisharp-atom:run-all-tests"
      ;          :target actions/get-active-editor}
      ;      :t {:title "run selected"
      ;          :action "omnisharp-atom:run-single-test"
      ;          :target actions/get-active-editor}
      ;      :p {:title "run previous"
      ;          :action "omnisharp-atom:run-last-test"
      ;          :target actions/get-active-editor}
      ;      :f {:title "run fixture"
      ;          :action "omnisharp-atom:run-fixture-tests"
      ;          :target actions/get-active-editor}}}})
  (mode/link-modes :csharp-major-mode (mode/package-mode-name :omnisharp-atom)))

(defmethod describe-mode :lang/csharp []
  {:mode-name :csharp-major-mode
   :atom-grammars ["C#"]
   :atom-scope ["source.cs"]})

(defmethod get-keybindings :lang/csharp [] {})
(defmethod get-keymaps :lang/csharp [] [])
