(ns proton.lib.mode

  (:require [cljs.nodejs :as nodejs]
            [proton.lib.keymap :as km :refer [set-proton-keys-for-mode]]
            [proton.lib.atom :as atom-env :refer [get-active-editor]]))

(def path (nodejs/require "path"))

(defonce modes (atom {}))
(defonce editors (atom {}))

(defn- find-first [f coll] (first (filter f coll)))

(defn find-editor-by-id [editor-id]
  (get @editors editor-id))

(defn editor-grammar [editor] (.-name (.getGrammar editor)))
(defn editor-grammar-scope [editor] (.-scopeName (.getGrammar editor)))

(defn file-extension [editor]
  "Returns file extenstion.
  For dotfiles returns file name."
  (if-let [file-path (.getPath editor)]
   (let [parsed (.parse path (.getPath editor))
         extension (.-ext parsed)]
    (if (= extension "")
        (.-base parsed)
        extension))
   ""))

(defn is-mode-activated? [editor] (get editor :active))

(defn get-mode [mode-name]
  (get @modes mode-name))

(defn get-current-editor-mode []
  (if-let [active-editor (atom-env/get-active-editor)]
    (if-let [editor (find-editor-by-id (.-id active-editor))]
      (editor :mode))))

(defn package-mode-name [package-name]
  (keyword (str (name package-name) "-mode")))

(defn define-mode [mode-name options]
  (if-let [mode-keybindings (options :mode-keybindings)]
    (km/set-proton-keys-for-mode mode-name mode-keybindings))
  (swap! modes assoc-in [mode-name] (dissoc options :mode-keybindings)))

(defn define-package-mode [mode-name options]
  (define-mode (package-mode-name mode-name) options))

(defn define-keybindings [name keymap]
  (swap! modes assoc-in [name :mode-keybindings] keymap))

(defn map-modes-with [key] (map #(vector (first %) (get-in (second %) [key])) (map identity @modes)))

(defn link-modes [parent-mode child-mode]
  (let [childs (get (get-mode parent-mode) :child)]
    (swap! modes update-in [parent-mode :child] #(distinct (conj childs child-mode)))))

(defn filter-modes-by-key-val [key val]
  (filter #(or (some #{val} (second %)) (= (second %) val)) (map-modes-with key)))

(defn find-mode-by-grammar [grammar]
  (if-let [filtered (first (filter-modes-by-key-val :atom-grammars grammar))]
   (first filtered)
   nil))

(defn find-mode-by-scope [scope]
  (if-let [filtered (first (filter-modes-by-key-val :atom-scope scope))]
   (first filtered)
   nil))

(defn mode-extensions-filter [ext]
  (fn [coll]
    (if-let [extensions (second coll)]
     (let [reg? (not (empty? (filter #(re-find % ext) (filter regexp? extensions))))
           str? (not (nil? (some #{ext} (filter string? extensions))))]
      (or reg? str?))
     false)))

(defn find-mode-by-file-extension [extension]
  (if-let [filtered (first (filter (mode-extensions-filter extension) (map-modes-with :file-extensions)))]
    (first filtered)
    nil))

(defn get-mode-keybindings [editor]
  (when-not (nil? editor)
    (if-let [mode-name (get-in @editors [(.-id editor) :mode])]
      (get-in @modes [mode-name :mode-keybindings])
      nil)))

(defn get-available-mode [editor]
   (let [by-grammar (find-mode-by-grammar (editor-grammar editor))
         by-extension (find-mode-by-file-extension (file-extension editor))
         by-scope (find-mode-by-scope (editor-grammar-scope editor))]
    (or by-grammar by-extension by-scope)))

(defn activate-mode [editor]
  (when-not (nil? editor)
    (when-not (get @editors (.-id editor))
      (if-let [mode (get-available-mode editor)]
       (do
          (swap! editors assoc (.-id editor) {:active true :mode mode})
          (.onDidDestroy editor (fn []
                                  (swap! editors dissoc (.-id editor))))
          (if-let [init-fn (get-in @modes [mode :init])]
             (init-fn)))))))

(defn- unset-mode-atom-fn [modes mode-name]
  (let [updated-childs (into {} (map (fn [[k v]] (update-in {k v} [k :child] (partial remove #{mode-name}))) modes))]
    (dissoc updated-childs mode-name)))

(defn unset-mode! [mode-name]
  (swap! modes unset-mode-atom-fn mode-name))

(defn cleanup! []
  (reset! modes {})
  (reset! editors {}))
