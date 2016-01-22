(ns proton.layers.lang.handlebars.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/handlebars []
  [:atom-handlebars])

(defmethod init-layer! :lang/handlebars []
  (console! "init" :lang/handlebars)
  (register-layer-dependencies :tools/linter
    [:linter-handlebars]))

(defmethod describe-mode :lang/handlebars []
  {:mode-name :handlebars-major-mode
   :atom-scope "text.html.handlebars"
   :atom-grammars "HTML (Handlebars)"})
