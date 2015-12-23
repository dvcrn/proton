(ns proton.layers.tools.minimap.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/minimap
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/minimap))

(defmethod get-keybindings :tools/minimap
  []
  {:t {:category "toggles"
       :m {:action "minimap:toggle"
           :title "minimap"}}})

(defmethod get-packages :tools/minimap
  []
  [:minimap])

(defmethod get-keymaps :tools/minimap [] [])
(defmethod get-initial-config :tools/minimap [] [])
(defmethod describe-mode :tools/minimap [] {})
