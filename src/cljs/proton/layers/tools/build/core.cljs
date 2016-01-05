(ns proton.layers.tools.build.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/build
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/build))

(defmethod get-keybindings :tools/build
  []
  {:c {:category "compile (build)"
       :b {:action "build:trigger"
           :title "trigger"}
       :v {:action "build:toggle-panel"
           :title "toggle-panel"}
       :g {:action "build:error-match"
           :title "error-match"}
       :h {:action "build:error-match-first"
           :title "error-match-first"}
       :t {:action "build:select-active-target"
           :title "select-active-target"}}})

(defmethod get-packages :tools/build
  []
  [:build])


(defmethod get-keymaps :tools/build [] [])
(defmethod get-initial-config :tools/build [] [])
(defmethod describe-mode :tools/build [] {})
