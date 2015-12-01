(ns proton.layers.base)

(defn dispatch [layer-name]
  (keyword layer-name))

;; multimethods to be used inside layer
(defmulti init-layer! dispatch)
(defmulti get-initial-config dispatch)
(defmulti get-packages dispatch)
(defmulti get-keybindings dispatch)
(defmulti get-keymaps dispatch)
