(ns build
  (:require [shadow.cljs.build :as cljs]
            [shadow.cljs.umd :as umd]
            [clojure.java.io :as io]))

(defn- plugin-setup []
  (-> (cljs/init-state)
      (cljs/find-resources-in-classpath)
      (umd/create-module
        {:activate 'proton.core/activate
         :serialize 'proton.core/serialize
         :deactivate 'proton.core/deactivate
         :chain 'proton.core/chain}
        {:output-to "plugin/lib/proton.js"})))


(defn release []
  (-> (plugin-setup)
      (cljs/compile-modules)
      (cljs/closure-optimize :simple)
      (umd/flush-module))
  :done)

(defn dev []
  (-> (plugin-setup)
      (cljs/watch-and-repeat!
        (fn [state modified]
          (-> state
              (cljs/compile-modules)
              (umd/flush-unoptimized-module)))))

  :done)
