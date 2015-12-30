(ns proton.lib.keymap
  (:require [clojure.string :as string :refer [join split]]
            [proton.lib.atom :as atom-env :refer [workspace commands views eval-action!]]
            [proton.layers.core.actions :as actions]
            [proton.lib.pane_manager :as panes]))

(defonce atom-keymap (atom {}))
(defonce proton-keymap (atom {}))
(defonce mode-keymap (atom {}))
(defonce keymap-category (atom {}))

(defn get-current-editor-mode [] (proton.lib.mode/get-current-editor-mode))

; Example usage:
; (set-proton-keys-for-mode :clojure
;   "t p" {:action "some-action" :target "target"}
;   "g shift-D" {:action "some-action2" :target "target2"}
;   "m g ctrl-alt-d" {:action "some-action3" :target "target3"})"
(defn set-proton-keys-for-mode [mode-name & args]
  "Define multiple key bindings associated with mode."
  {:pre [(even? (count args))]}
  (let [keybindings-map (reduce #(assoc %1 (key (vec %2)) (val (vec %2))) {} (partition 2 args))]
    (swap! mode-keymap merge (assoc {} mode-name keybindings-map))))

; Example usage:
; (set-proton-leader-keys
;   "g d" {:action "some-action" :target "target"}
;   "g shift-D" {:action "some-action2" :target "target2"}
;   "m g ctrl-alt-d" {:action "some-action3" :target "target3"})"
(defn set-proton-leader-keys
  "Define multiple key bindings with associated action for `proton-keymap`.
  Accept pair `keybinding` `options`."
  [& args]
  {:pre [(even? (count args))]}
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

(defn- atom-keystroke [character]
  (if-let [uppercased? (not (empty? (filter #(= character %) (map char (range 65 91)))))]
    (str "shift-" character)
    character))

(defn- convert-from-hash-iter [combo keybinding-key hash]
  (let [combo (string/join " " (conj (string/split combo #" ") (atom-keystroke (name keybinding-key))))
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
  "Convert hash-map formated keybindings to appropriate format used to
  store key bindings in keymaps."
  (flatten (reduce concat (map #(convert-from-hash-iter "" (first %) (rest %)) (map identity hash)))))

(defn cleanup! []
  (reset! atom-keymap {})
  (reset! proton-keymap {})
  (reset! mode-keymap {})
  (reset! keymap-category {}))

(defn get-mode-keybindings [mode-name]
  (get @mode-keymap (keyword mode-name)))

(defn- prepend-keystroke-for-keymap
  "Adds `keystroke` prefix for each key binding in specified `keymap`."
  [keystroke keymap]
  (reduce #(assoc %1 (str keystroke " " (key (vec %2))) (val (vec %2))) {} keymap))

(defn find-proton-keybindings
  "Find all key bindings in `proton-keymap` and `mode-keymap` associated with 'keybinding'. Keybinding is simple string which contains of atom keystrokes separated by space character, e.g. \"g d\", \"w shift-V\". Returns collection of matched items."
  [keybinding]
  (let [current-mode (get-current-editor-mode)
        mode-prefix-key (name (first proton.core/mode-keys))
        keymap (merge ((comp (partial prepend-keystroke-for-keymap mode-prefix-key) get-mode-keybindings) current-mode) @proton-keymap @keymap-category)
        filtered-keymap (filter #(not= -1 (.indexOf (name (first %)) keybinding)) keymap)
        matched-keybinding (first (filter #(= keybinding (name (first %))) filtered-keymap))]
      ;; when nothing found return nil
      (if ((comp not nil?) matched-keybinding)
        ;; check if matched keybinding is category and it has linked keybindings
        (if (and ((val matched-keybinding) :catogry) (> 1 (count filtered-keymap)))
          ;; return all filtered items
          filtered-keymap
          ;; when not category return found items
          ;; when found single item then this item is action
          (if (not (empty? filtered-keymap))
            filtered-keymap)))))

(defn exec-binding [keymap-item]
  (atom-env/eval-action! (val keymap-item)))
