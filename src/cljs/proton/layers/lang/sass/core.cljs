(ns proton.layers.lang.sass.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies]]))

(def sass-modes
  {:sass-major-mode {:atom-scope "source.sass" :atom-grammars "Sass"}
   :scss-major-mode {:atom-scope "source.css.scss" :atom-grammars "SCSS"}})

(defmethod get-packages :lang/sass []
  [:language-sass
   :atom-css-comb
   :css-snippets
   :pigments
   :autocomplete-sass])

(defmethod init-layer! :lang/sass []
  (console! "init" :lang/sass)
  (register-layer-dependencies :tools/linter
    [:linter-sass-lint])
  (doall (map #(mode/define-mode (key %) (val %)) sass-modes)))

(defmethod init-package [:lang/sass :atom-css-comb] []
  (mode/define-package-mode :atom-css-comb
    {:mode-keybindings
      {:f {:category "format"
           :c {:action "css-comb:comb" :target actions/get-active-editor :title "css comb"}}}})
  (mode/link-modes :scss-major-mode (mode/package-mode-name :atom-css-comb))
  (mode/link-modes :sass-major-mode (mode/package-mode-name :atom-css-comb)))
