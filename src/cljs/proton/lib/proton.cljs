(ns proton.lib.proton
  (:require [cljs.reader :as reader]
            [cljs.nodejs :as node]
            [proton.lib.helpers :as helpers]
            [proton.layers.base :as layerbase]))

(def config-path (str (.. js/process -env -HOME) "/.proton"))

(defn get-config-template-path []
  (let [path (node/require "path")]
    (.resolve path (str js/__dirname "/../templates/proton.edn"))))

(defn has-config? []
  (helpers/is-file? config-path))

(defn create-default-config! []
  (let [child-process (node/require "child_process")
        template-path (get-config-template-path)]
    (.execSync child-process (str "cp " template-path " " config-path))))

(defn load-config []
  (if (not (has-config?))
    (create-default-config!))
  (reader/read-string (helpers/read-file config-path)))

(defn packages-for-layers [layers]
  (into [] (distinct (reduce concat (map #(layerbase/get-packages (keyword %)) layers)))))

(defn keybindings-for-layers [layers]
  (reduce merge (map #(layerbase/get-keybindings (keyword %)) layers)))

(defn configs-for-layers [layers]
  (reduce conj (filter #(not (empty? %)) (map #(layerbase/get-initial-config (keyword %)) layers))))

(defn keymaps-for-layers [layers]
  (reduce merge (map #(layerbase/get-keymaps (keyword %)) layers)))

(defn init-layers! [layers config]
  (doall (map #(layerbase/init-layer! (keyword %) config) layers)))
