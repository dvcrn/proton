(ns proton.layers.lang.typescript.core
  (:require [proton.lib.helpers :refer [console!]]
            [proton.lib.mode :as mode])
  (:use [proton.layers.base :only [init-layer! init-package get-packages describe-mode register-layer-dependencies]]))


(defmethod init-layer! :lang/typescript
  [_ {:keys [config layers]}]
  (console! "init" :lang/typescript))

(defmethod get-packages :lang/typescript
  []
  [:atom-typescript
   :linter-tslint])

(defmethod describe-mode :lang/typescript
  []
  {:mode-name :typescript
   :atom-grammars ["TypeScript"]
   :atom-scope ["source.typescript"]
   :mode-keybindings
   {:f {:action "typescript:find-references"
        :title "find references"}
    :d {:action "typescript:dependency-view"
        :title "dependency view"}
    :g {:action "typescript:go-to-declaration"
        :title "go to declaration"}}})
