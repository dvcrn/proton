(ns proton.lib.proton
  (:require [cljs.reader :as reader]
            [cljs.nodejs :as node]
            [proton.lib.mode :as mode-manager]
            [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
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
  (reduce helpers/deep-merge (map #(layerbase/get-keybindings (keyword %)) layers)))

(defn configs-for-layers [layers]
  (reduce conj (filter #(not (empty? %)) (map #(layerbase/get-initial-config (keyword %)) layers))))

(defn keymaps-for-layers [layers]
  (reduce concat (map #(layerbase/get-keymaps (keyword %)) layers)))

(defn init-layers! [layers config]
  (doall (map #(layerbase/init-layer! (keyword %) config) layers)))

(defn init-modes-for-layers [layers]
  (doall
    (map #(mode-manager/define-mode (get % :mode-name) (dissoc % :mode-name))
     (filter #(not (nil? (get % :mode-name))) (map #(layerbase/describe-mode %) layers)))))

(defn- on-active-pane-item [item]
  (if-let [editor (atom-env/get-active-editor)]
    (when (= (.-id editor) (.-id item))
     (mode-manager/activate-mode editor))))

(defn panel-item-subscription [] (.onDidChangeActivePaneItem atom-env/workspace on-active-pane-item))
