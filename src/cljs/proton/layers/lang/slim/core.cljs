(ns proton.layers.lang.slim.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/slim []
  [:language-slim])

(defmethod init-layer! :lang/slim []
  (console! "init" :lang/slim)
  (register-layer-dependencies :tools/linter
    [:linter-slim]))

(defmethod describe-mode :lang/slim []
  {:mode-name :slim-major-mode
   :atom-grammars "Ruby Slim"
   :atom-scope "text.slim"})
