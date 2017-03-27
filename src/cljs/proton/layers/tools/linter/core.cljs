(ns proton.layers.tools.linter.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/linter
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/linter))

(defmethod get-packages :tools/linter []
  [:linter-ui-default
   :busy-signal
   :intentions
   :linter])

(defmethod get-keybindings :tools/linter [] []
  {:e {:category "errors"
       :t {:action "linter:toggle-active-editor"
           :title "toggle linter"}
       :n {:action "linter-ui-default:next"
           :title "next error"}
       :p {:action "linter-ui-default:previous"
           :title "previous error"}
       :N {:action "linter-ui-default:next-error-in-current-file"
           :title "next error in current file"}
       :P {:action "linter-ui-default:previous-error-in-current-file"
           :title "previous error in current file"}
       :l {:action "linter:lint"
           :title "lint now"}
       :T {:action "linter-ui-default:toggle-panel"
           :title "toggle panel"}
       :f {:action "linter-ui-default:apply-all-solutions"
           :title "apply all solutions"}}})


(defmethod describe-mode :tools/linter [] {})
(defmethod get-keymaps :tools/linter [] [])
