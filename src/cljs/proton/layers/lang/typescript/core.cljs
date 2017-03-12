(ns proton.layers.lang.typescript.core
  (:require [proton.lib.helpers :refer [console!]]
            [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.mode :as mode])
  (:use [proton.layers.base :only [init-layer! init-package get-packages describe-mode register-layer-dependencies]]))

(defmethod init-layer! :lang/typescript
  [_ {:keys [config layers]}]
  (console! "init" :lang/typescript)
  (register-layer-dependencies :tools/linter [:linter-tslint]))

(defmethod get-packages :lang/typescript
  []
  [:atom-typescript])

(defmethod init-package [:lang/typescript :atom-typescript] []
  (console! "init atom-typescript package" :lang/typescript)
  (mode/define-package-mode :atom-typescript
    {:mode-keybindings
      {:g {:category "go to"
           :g {:action "typescript:go-to-declaration"
               :target actions/get-active-editor
               :title "definition"}
           :u {:action "typescript:find-references"
               :target actions/get-active-editor
               :title "references"}
           :q {:action "typescript:return-from-declaration"
               :title "from definition"}}
       :e {:category "errors"
           :f {:action "typescript:quick-fix"
               :target actions/get-active-editor
               :title "quick fix"}}
       :c {:category "compile"
           :c {:action "typescript:build"
               :title "compile project"}
           :i {:action "typescript:create-tsconfig.json-project-file"
               :title "init tsconfig"}}
       :f {:category "file"
           :f {:action "typescript:format-code"
               :target actions/get-active-editor
               :title "format file"}}
       :r {:category "refactor"
           :n {:action "typescript:rename-refactor"
               :target actions/get-active-editor
               :title "rename"}}
       :s {:category "show/search"
           :d {:action "typescript:dependency-view"
               :title "dependency view"}
           :o {:action "typescript:output-toggle"
               :title "output"}
           :s {:action "typescript:toggle-semantic-view"
               :title "semantic view"}
           :t {:action "typescript:show-type"
               :title "type"}
           :a {:action "typescript:ast"
               :target actions/get-active-editor
               :title "ast"}
           :A {:action "typescript:ast-all"
               :target actions/get-active-editor
               :title "ast full"}}
       :d {:category "debug"
           :b {:action "typescript:toggle-breakpoint"
               :target actions/get-active-editor
               :title "toggle breakpoint"}}}})
  (mode/link-modes :typescript-major-mode (mode/package-mode-name :atom-typescript)))

(defmethod describe-mode :lang/typescript
  []
  {:mode-name :typescript-major-mode
   :atom-grammars ["TypeScript"]
   :atom-scope ["source.ts"]})
