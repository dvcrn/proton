(ns proton.layers.apps.notes.core
  (:require [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-keybindings get-packages init-package]]))

(defmethod init-layer! :apps/notes []
  (console! "init" :apps/notes))

(defmethod get-packages :apps/notes []
  [:nvatom])

(defmethod get-keybindings :apps/notes []
  {:a {:category "apps"
       :n {:action "nvatom:toggle" :title "show notes"}}})
