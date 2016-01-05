(ns proton.lib.keymap
  #_(:require [proton.core]
              [proton.lib.mode])
  (:require [proton.lib.atom :as atom-env :refer [workspace commands views eval-action!]]))

(defonce atom-keymap (atom {}))
(defonce proton-keymap (atom {}))
(defonce mode-keymap (atom {}))

(def get-current-editor-mode #(proton.lib.mode.get-current-editor-mode))

(defn set-proton-keys-for-mode
  "Define multiple key bindings associated with mode."
  [mode-name keybindings-map]
  (swap! mode-keymap merge (assoc {} mode-name keybindings-map)))

(defn set-proton-leader-keys
  "Define multiple key bindings with associated action for `proton-keymap`."
  [keybindings-map]
  (swap! proton-keymap merge keybindings-map))

(defn- is-exec? [hash]
  (let [{:keys [action target fx title]} hash]
    (or action target fx title)))

(defn cleanup! []
  (reset! atom-keymap {})
  (reset! proton-keymap {})
  (reset! mode-keymap {}))

(defn get-mode-keybindings [mode-name]
  (get @mode-keymap (keyword mode-name)))

(defn find-keybindings [ks]
  (let [current-mode (get-current-editor-mode)
        mode-prefix-key (keyword (first proton.core.mode-keys))
        mode-keymap {mode-prefix-key (conj (get @proton-keymap mode-prefix-key) (get-mode-keybindings current-mode))}
        keymap (merge @proton-keymap mode-keymap)]
      (get-in keymap ks)))

(defn exec-binding [keymap-item]
  (atom-env/eval-action! keymap-item))

(defn is-action? [{:keys [fx action]}]
  (not (nil? (or fx action))))
