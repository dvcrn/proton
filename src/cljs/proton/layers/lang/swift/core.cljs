(ns proton.layers.lang.swift.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]]
            [proton.layers.core.actions :refer [get-active-editor]])
  (:use [proton.layers.base :only [init-layer! get-packages register-layer-dependencies describe-mode init-package]]))

(defmethod get-packages :lang/swift []
  [:language-swift
   :autocomplete-swift])

(defmethod init-layer! :lang/swift []
  (console! "init" :lang/swift)
  (register-layer-dependencies :tools/linter
    [:linter-swiftlint])

  (register-layer-dependencies :tools/build
    [:build-xcodebuild]))

(defmethod describe-mode :lang/swift []
  {:mode-name :swift-major-mode
   :atom-grammars ["Swift"]
   :atom-scope ["source.swift"]})
