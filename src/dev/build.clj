(ns build
  (:require [shadow.cljs.build :as cljs]
            [shadow.cljs.umd :as umd]
            [shadow.devtools.server :as devtools]
            [clojure.java.io :as io]))

(defn- plugin-setup []
  (-> (cljs/init-state)
      (cljs/set-build-options
        {:node-global-prefix "global.PROTON"})
      (cljs/find-resources-in-classpath)
      (umd/create-module
        {:activate 'proton.core/activate
         :serialize 'proton.core/serialize
         :deactivate 'proton.core/deactivate
         :toggle 'proton.core/toggle
         :toggleMode 'proton.core/toggle-mode
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
              (umd/flush-unoptimized-module))))))

(defn dev-once []
  (-> (plugin-setup)
      (cljs/compile-modules)
      (umd/flush-unoptimized-module))
  :done)

(defn dev-repl []
  (-> (plugin-setup)
      (devtools/start-loop
        {:before-load 'proton.core/stop
         :after-load 'proton.core/start
         :reload-with-state true
         :console-support true
         :node-eval true}
        (fn [state modified]
          (-> state
              (cljs/compile-modules)
              (umd/flush-unoptimized-module)))))

  :done)
