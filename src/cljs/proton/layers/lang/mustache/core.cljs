(ns proton.layers.lang.mustache.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages init-package describe-mode]]))

(defmethod get-packages :lang/mustache []
  [:language-mustache])

(defmethod init-layer! :lang/mustache []
  (console! "init" :lang/mustache))

(defmethod describe-mode :lang/mustache []
  {:mode-name :mustache-major-mode
   :atom-scope "text.html.mustache"
   :child [:html-major-mode]})
