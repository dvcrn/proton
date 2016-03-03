(ns proton.lib.keymap
  #_(:require [proton.core]
              [proton.lib.mode])
  (:require [proton.lib.atom :as atom-env :refer [workspace commands views eval-action! eval-actions!]]
            [proton.lib.helpers :as helpers :refer [deep-merge]]))

(defonce atom-keymap (atom {}))
(defonce proton-keymap (atom {}))
(defonce mode-keymap (atom {}))

(def get-current-editor-mode #(proton.lib.mode.get-current-editor-mode))
(defn get-mode-info [mode-name] (proton.lib.mode.get-mode mode-name))

(defn set-proton-keys-for-mode
  "Define multiple key bindings associated with mode."
  [mode-name keybindings-map]
  (swap! mode-keymap helpers/deep-merge (assoc {} mode-name keybindings-map)))

(defn set-proton-leader-keys
  "Define multiple key bindings with associated action for `proton-keymap`."
  [keybindings-map]
  (swap! proton-keymap helpers/deep-merge keybindings-map))

(defn- is-exec? [hash]
  (let [{:keys [action target fx title]} hash]
    (or action target fx title)))

(defn cleanup! []
  (reset! atom-keymap {})
  (reset! proton-keymap {})
  (reset! mode-keymap {}))

(defn get-mode-keybindings [mode-name]
  (when-not (nil? mode-name)
    (if-let [mode-info (get-mode-info mode-name)]
      (let [mode-map (or (get @mode-keymap (keyword mode-name)) {})
            child-modes (mode-info :child)]
        (if-not (nil? child-modes)
          (helpers/deep-merge (reduce #(helpers/deep-merge %1 (get-mode-keybindings %2)) {} child-modes) mode-map)
          mode-map)))))

(defn find-keybindings [ks]
  (let [current-mode (get-current-editor-mode)
        mode-prefix-key (keyword (first proton.core.mode-keys))
        mode-keymap {mode-prefix-key (conj (get @proton-keymap mode-prefix-key) (get-mode-keybindings current-mode))}
        keymap (merge @proton-keymap mode-keymap)]
      (get-in keymap ks)))

(defn exec-binding [keymap-item]
  ;; if it is a vector, it means we are dealing with a multi keybinding
  (if (vector? keymap-item)
    (eval-actions! keymap-item)
    (eval-action! keymap-item)))

(defn is-action? [{:keys [fx action]}]
  (not (nil? (or fx action))))

(defn unset-keymap-for-mode! [mode-name]
  (swap! mode-keymap dissoc mode-name))
