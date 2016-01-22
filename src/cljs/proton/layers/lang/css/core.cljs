(ns proton.layers.lang.css.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/css []
  [:language-css
   :atom-css-comb
   :autoprefixer
   :autocomplete-css
   :pigments
   :css-snippets])

(defmethod init-layer! :lang/css []
  (console! "init" :lang/css)
  (register-layer-dependencies :tools/linter
    [:linter-csslint]))

(defmethod init-package [:lang/css :atom-css-comb] []
  (mode/define-package-mode :atom-css-comb
    {:mode-keybindings
      {:f {:category "format"
           :c {:action "css-comb:comb" :target "atom-text-editor:not([mini])" :title "css comb"}}}})
  (mode/link-modes :css-major-mode (mode/package-mode-name :atom-css-comb)))

(defmethod init-package [:lang/css :autoprefixer] []
  (mode/define-package-mode :autoprefixer
    {:mode-keybindings
      {:f {:category "format"
           :a {:action "autoprefixer"}}}})
  (mode/link-modes :css-major-mode (mode/package-mode-name :autoprefixer)))

(defmethod describe-mode :lang/css []
  {:mode-name :css-major-mode
   :atom-scope "source.css"
   :atom-grammars "CSS"})
