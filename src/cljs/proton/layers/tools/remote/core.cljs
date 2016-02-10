(ns proton.layers.tools.api.core
  (:require [proton.lib.helpers :as helpers]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base
         :only [init-layer! get-initial-config get-keybindings
                get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/api
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/api))

(defmethod get-keybindings :tools/api
  []
  {:a {:category "applications"
       :r {:title "rest client"
           :action "rest-client:show"}}})

(defmethod get-packages :tools/api
  []
  [:rest-client])


(defmethod get-keymaps :tools/api [] [])
(defmethod get-initial-config :tools/api [] [])
(defmethod describe-mode :tools/api [] {})
