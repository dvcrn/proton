(ns proton.layers.tools.terminal.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/terminal
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/terminal))

(defmethod get-keybindings :tools/terminal
  []
  {:quote {:action "platformio-ide-terminal:toggle"
           :title "terminal"}})

(defmethod get-packages :tools/terminal
  []
  [:platformio-ide-terminal])

(defmethod get-keymaps :tools/terminal [] [])
(defmethod get-initial-config :tools/terminal [] [])
(defmethod describe-mode :tools/terminal [] {})
