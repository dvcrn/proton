(ns proton.layers.base
  (:require [proton.lib.helpers :as helpers]))

(defn dispatch [layer-name] (keyword layer-name))
(def layer-dependencies (atom {}))
(def package-dependencies (atom {}))

;; multimethods to be used inside layer
(defmulti init-layer! dispatch)
(defmulti get-initial-config dispatch)
(defmulti get-packages dispatch)
(defmulti get-keybindings dispatch)
(defmulti get-keymaps dispatch)
(defmulti describe-mode dispatch)

(defn register-package-dependencies [key deps]
  (let [package-deps @package-dependencies]
    ;; check if we already have that key inside the deps
    (if (contains? package-deps key)
      (swap! package-dependencies assoc key (into [] (distinct (concat (get package-deps key) deps))))
      (swap! package-dependencies assoc key deps))))

(defn register-layer-dependencies [key deps]
  (let [layer-deps @layer-dependencies]
    ;; check if we already have that key inside the deps
    (if (contains? layer-deps key)
      (swap! layer-dependencies assoc key (into [] (distinct (concat (get layer-deps key) deps))))
      (swap! layer-dependencies assoc key deps))))

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
