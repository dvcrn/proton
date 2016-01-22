(ns proton.layers.lang.less.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/less []
  [:language-less
   :atom-css-comb
   :pigments
   :css-snippets])

(defmethod init-layer! :lang/less []
  (console! "init" :lang/less)
  (register-layer-dependencies :tools/linter
    [:linter-less]))

(defmethod init-package [:lang/less :atom-css-comb] []
  (mode/define-package-mode :atom-css-comb
    {:mode-keybindings
      {:f {:category "format"
           :c {:action "css-comb:comb" :target actions/get-active-editor :title "css comb"}}}})
  (mode/link-modes :less-major-mode (mode/package-mode-name :atom-css-comb)))

(defmethod describe-mode :lang/less []
  {:mode-name :less-major-mode
   :atom-scope "source.css.less"
   :atom-grammars "Less"})
