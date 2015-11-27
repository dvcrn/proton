(ns proton.lib.proton
  (:require [cljs.reader :as reader]
            [cljs.nodejs :as node]
            [proton.lib.helpers :as helpers]
            [proton.layers.base :as layerbase]))

(def config-path (str (.. js/process -env -HOME) "/.proton"))

(defn has-config? []
  (helpers/is-file? config-path))


(defn load-config []
  (if (has-config?)
    (reader/read-string (helpers/read-file config-path))
    {:additional-packages []
     :layers []
     :configuration []
     :keybindings {}}))

(defn packages-for-layers [layers]
  (into [] (distinct (reduce concat (map #(layerbase/get-packages (keyword %)) layers)))))

(defn keybindings-for-layers [layers]
  (reduce merge (map #(layerbase/get-keybindings (keyword %)) layers)))
