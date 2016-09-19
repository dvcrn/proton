(ns proton.layers.lang.elm.core
  (:require [proton.lib.mode :as mode]
            [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies describe-mode get-initial-config]]))

(defmethod init-layer! :lang/elm []
  (console! "init" :lang/elm)
  (register-layer-dependencies :tools/linter [:linter-elm-make]))

(defmethod get-initial-config :lang/elm []
  [["elm-format.binary" "elm-format"]])

(defmethod get-packages :lang/elm []
  [:language-elm
   :elm-format
   :elmjutsu])

(defmethod init-package [:lang/elm :linter-elm-make] []
  (console! "init linter-elm-make" :lang/elm)
  (mode/define-package-mode :linter-elm-make
    {:mode-keybindings
      {:L {:category "linters"
           :f {:action "linter-elm-make:quick-fix" :target actions/get-active-editor :title "fix"}
           :F {:action "linter-elm-make:quick-fix-all" :target actions/get-active-editor :title "fix all"}}}})
  (mode/link-modes :elm-major-mode (mode/package-mode-name :linter-elm-make)))

(defmethod init-package [:lang/elm :elmjutsu] []
  (console! "init elmjutsu" :lang/elm)
  (mode/define-package-mode :elmjutsu
    {:mode-keybindings
      {:g {:category "go to"
           :g {:action "elmjutsu:go-to-definition" :target actions/get-active-editor :title "definition"}
           :r {:action "elmjutsu:find-usages" :target actions/get-active-editor :title "references"}
           :s {:action "elmjutsu:go-to-symbol" :target actions/get-active-editor :title "symbol"}
           :q {:action "elmjutsu:go-back" :target actions/get-active-editor :title "back from definition"}
           :n {:action "elmjutsu:go-to-next-usage" :target actions/get-active-editor :title "next usage"}
           :p {:action "elmjutsu:go-to-previous-usage" :target actions/get-active-editor :title "previous usage"}}
       :r {:category "refactor"
           :s {:action "elmjutsu:rename-symbol" :target actions/get-active-editor :title "rename symbol"}}
       :t {:category "toggle"
           :s {:action "elmjutsu:toggle-sidekick" :target actions/get-active-editor :title "sidekick"}}}})
  (mode/link-modes :elm-major-mode (mode/package-mode-name :elmjutsu)))

(defmethod describe-mode :lang/elm
  []
  {:mode-name :elm-major-mode
   :atom-scope ["source.elm"]})
