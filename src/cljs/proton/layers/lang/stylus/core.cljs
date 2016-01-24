(ns proton.layers.lang.stylus.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages register-layer-dependencies describe-mode]]))

(defmethod get-packages :lang/stylus []
  [:Stylus
   :pigments])

(defmethod init-layer! :lang/stylus []
  (console! "init" :lang/stylus)
  (register-layer-dependencies :tools/linter
    [:linter-stylint]))

(defmethod describe-mode :lang/stylus []
  {:mode-name :stylus-major-mode
   :atom-scope "source.stylus"
   :atom-grammars "Stylus"})
