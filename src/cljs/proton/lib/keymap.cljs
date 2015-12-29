(ns proton.lib.keymap
  (:require [clojure.string :as string :refer [join split]]
            [proton.lib.atom :as atom-env]
            [proton.layers.core.actions :as actions]
            [proton.lib.pane_manager :as panes]))

; todo remove
(defn select-window-fn [n]
  (fn []
    (panes/focus-on-item n)))

(def atom-keymap (atom {}))
(def proton-keymap (atom {}))
(def mode-keymap (atom {}))
(def keymap-category (atom {}))

(defn- gen-key [keybinding func])

(defn- gen-category [keybinding category]
  {keybinding {:name category}})

(defn define-keymap [keymap-type keybinding func]
  (swap! keymap-type assoc-in (gen-key keybinding func)))

(defn proton-leader-set-key-for-mode [mode keybinding func]
  (swap! mode-keymap assoc-in [mode] (gen-key keybinding func)))

(defn proton-set-keys [& args]
  (prn (into (hash-map) (partition 2 args))))

(defn proton-leader-set-mode-keys [mode & args]
  (prn mode (into (hash-map) (partition 3 args))))

(defn define-category [keybinding category]
  (swap! keymap-category assoc-in (gen-category keybinding category)))

(defn set-mode-keys [mode-name & body]
  (partition 2 body))

(defn set-proton-leader-keys [& args]
  (let [keybindings-map (map identity (reduce #(assoc %1 (first %2) (into (hash-map) (rest %2))) {} (partition 2 args)))
        category-filter-fn (fn [v] (contains? (last v) :category))
        category-map (filter category-filter-fn keybindings-map)
        keybindings (remove category-filter-fn keybindings-map)]
    (swap! keymap-category merge (into (hash-map) category-map))
    (swap! proton-keymap merge (into (hash-map) keybindings))))

(defn atom-action
  ([action]
   (atom-action action nil action))
  ([action target]
   (atom-action action target action))
  ([action target title]
   (fn []
    (let [selector (when (string? target) (js/document.querySelector target))
          dom-target (if (nil? target) (.getView atom-env/views atom-env/workspace) (or selector (target js/atom)))]
        (when-not (nil? action)
          (.log js/console (str "[proton] Dispatching " action " to "))
          (.log js/console dom-target)
          (.dispatch atom-env/commands dom-target action))))))

(defn- is-exec? [hash]
  (let [{:keys [action target fx title]} hash]
    (or action target fx title)))

(defn- convert-from-hash-iter [combo keybinding-key hash]
  (let [combo (string/join " " (conj (string/split combo #" ") (name keybinding-key)))
        current (into (hash-map) hash)]
      (if (is-exec? current)
        {combo current}
        (do
          (let [hash (filter #(map? (first (into [] (rest %)))) (map identity current))
                category (get current :category)
                keybinding (reduce concat (map #(convert-from-hash-iter combo (first %) (rest %)) hash))]
              (if (nil? category)
                keybinding
                (concat keybinding [combo {:category category}])))))))

(defn convert-from-hash-map [hash]
  (flatten (reduce concat (map #(convert-from-hash-iter "" (first %) (rest %)) (map identity hash)))))

(defn cleanup! []
  (reset! atom-keymap {})
  (reset! proton-keymap {})
  (reset! mode-keymap {})
  (reset! keymap-category {}))
