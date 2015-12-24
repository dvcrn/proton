(ns proton.lib.doc_helper
  (:require [goog.string :as gstring]
            [goog.string.format :as gformat]
            [proton.layers.base :as layers]))

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

(defn package-configs-doc-body [package-name]
  (if-let [configs (map #(vector (str package-name "." (first %)) (first (rest %))) (package-configs package-name))]
    (when-not (empty? configs)
      (clojure.string/join "\n" (map package-config-doc-row (filter #(not (empty? %)) configs))))))

(defn layer-packages-configs-doc-table [layer-name]
  (if-let [layer-packages (map name (layers/get-packages layer-name))]
    (let [table-body (clojure.string/join "\n" (filter #(not (nil? %)) (map package-configs-doc-body layer-packages)))]
      (str package-configurations-header table-body))))
