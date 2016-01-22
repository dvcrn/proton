(ns proton.layers.lang.jade.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages init-package register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/jade []
  [:language-jade])

(defmethod init-layer! :lang/jade []
  (console! "init" :lang/jade)
  (register-layer-dependencies :tools/linter
    [:linter-jade]))

(defmethod describe-mode :lang/jade []
  {:mode-name :jade-major-mode
   :atom-scope ["source.jade" "source.pyjade"]
   :atom-grammars ["Jade" "Jade (Python)"]})
