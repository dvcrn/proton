(ns proton.lib.doc_helper
  (:require [goog.string :as gstring]
            [goog.string.format :as gformat]
            [proton.layers.base :as layers]
            [clojure.string :as cstring]))

(def package-configurations-header (str "Name|Default|Type|Title|Description\n"
                                        "-|-|-|-|-\n"))

(defn package-by-name [package-name]
  (first (filter #(= package-name (.-name %)) (.getLoadedPackages js/atom.packages))))

(defn package-configs [package-name]
  (if-let [package (package-by-name package-name)]
    (if-let [main-module (.-mainModule package)]
      (if-let [configs (.-config main-module)]
        (js->clj configs)))))

(defn package-config-doc-row [config]
  (let [config-name (first config)
        {:strs [default type title description]} (into (hash-map) (rest config))]
      (gstring/format "`%s`|%s|__%s__|%s|%s" config-name default type title description)))

(def keybinding-atom (atom {}))
(defn keybinding-doc-row
  ([row] (keybinding-doc-row row "" ""))
  ([row prefix category]
   (let [key (get row 0)
         val (get row 1)]
     (if (not (= key :category))
       (if (nil? (get val :category))
         ;; if no :category key is present, the row is a normal keybinding and doesn't need further processing
         [(str "<kbd>" (clojure.string/trim (str prefix " " (name key))) "</kbd> | " (get val :title))]

         ;; if however a :category key is present, we need to recurse deeper and pull all of the sub cats out
         (let [cat (clojure.string/trim (str category " " (get val :category)))
               kbds (->> val seq
                     (map #(keybinding-doc-row % (str prefix " " (name key)) cat))
                     (filter #(not (nil? %)))
                     (apply concat)
                     (into []))]

           (swap! keybinding-atom assoc (str "\n\n#### " cat "\n") (doall kbds))
           nil))))))

(defn package-configs-doc-body [package-name]
  (if-let [configs (map #(vector (str package-name "." (first %)) (first (rest %))) (package-configs package-name))]
    (when-not (empty? configs)
      (clojure.string/join "\n" (map package-config-doc-row (filter #(not (empty? %)) configs))))))

(defn layer-packages-configs-doc-table [layer-name]
  (if-let [layer-packages (map name (layers/get-packages layer-name))]
    (let [table-body (clojure.string/join "\n" (filter #(not (nil? %)) (map package-configs-doc-body layer-packages)))]
      (str package-configurations-header table-body))))

;; use println to print all the \n line endings
(defn gen-keybindings-doc-table [layer-name]
  (let [kb-tree (layers/get-keybindings layer-name)]
    (reset! keybinding-atom {})
    (doall (map keybinding-doc-row (seq kb-tree)))
    (let [kbds @keybinding-atom]
      (map #(str (get % 0) "\n"
                 "Key Binding | Description \n"
                 "------------|------------ \n"
                 (clojure.string/join "\n" (get % 1)))
           kbds))))
