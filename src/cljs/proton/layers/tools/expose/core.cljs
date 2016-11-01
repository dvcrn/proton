(ns proton.layers.tools.expose.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/expose
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/expose))

(defmethod get-keybindings :tools/expose
  []
  {:b {:e {:action "expose:toggle"
           :title "exposé"}}})

(defmethod get-packages :tools/expose
  []
  [:expose])

(defmethod get-initial-config :tools/expose [] [])
(defmethod get-keymaps :tools/expose [] [])
(defmethod describe-mode :tools/expose [] {})
