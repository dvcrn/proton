(ns proton.layers.base)

;; multimethods to be used inside layer
(defmulti get-packages
  (fn [layer-name]
    (keyword layer-name)))

(defmulti get-keybindings
  (fn [layer-name]
    (keyword layer-name)))

(defmulti get-keymaps
  (fn [layer-name]
    (keyword layer-name)))
