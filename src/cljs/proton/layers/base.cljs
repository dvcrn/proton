(ns proton.layers.base
  (:require [proton.lib.helpers :as helpers]))

(defn dispatch [layer-name]
  (keyword layer-name))

;; multimethods to be used inside layer
(defmulti init-layer! dispatch)
(defmulti get-initial-config dispatch)
(defmulti get-packages dispatch)
(defmulti get-keybindings dispatch)
(defmulti get-keymaps dispatch)
(defmulti describe-mode dispatch)

(defn gen-error [f]
  (str f " does not exist."))

(defmethod init-layer! :default [key]
  (helpers/console! (gen-error "init-layer!") key))

(defmethod get-initial-config :default [key]
  (helpers/console! (gen-error "get-initial-config") key)
  [])

(defmethod get-packages :default [key]
  (helpers/console! (gen-error "get-packages") key)
  [])

(defmethod get-keybindings :default [key]
  (helpers/console! (gen-error "get-keybindings") key)
  {})

(defmethod get-keymaps :default [key]
  (helpers/console! (gen-error "get-keymaps") key)
  [])

(defmethod describe-mode :default [key]
  (helpers/console! (gen-error "describe-mode") key)
  {})
