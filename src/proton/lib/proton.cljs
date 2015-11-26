(ns proton.lib.proton
  (:require [cljs.reader :as reader]
            [cljs.nodejs :as node]
            [proton.lib.helpers :as helpers]))

(def config-path (str (.. js/process -env -HOME) "/.proton"))

(defn has-config? []
  (helpers/is-file? config-path))

(defn load-config []
  (println config-path)
  (println (reader/read-string (helpers/read-file config-path)))
  (reader/read-string (helpers/read-file config-path)))
