(ns proton.layers.lang.haml.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/haml []
  [:language-haml])

(defmethod init-layer! :lang/haml []
  (console! "init" :lang/haml)
  (register-layer-dependencies :tools/linter
    [:linter-haml]))

(defmethod describe-mode :lang/haml []
  {:mode-name :haml-major-mode
   :atom-grammars ["Coffee Haml" "Ruby Haml"]
   :atom-scope ["text.hamlc" "text.haml"]})
