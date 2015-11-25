(defproject proton "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]]

  :source-paths ["src"]

  :plugins [[lein-cljsbuild "1.1.1"]]

  :profiles {:dev {:source-paths ["src/dev"]
                   :dependencies [[thheller/shadow-devtools "0.1.31"]
                                  [thheller/shadow-build "1.0.161"]]}}

  :clean-targets ^{:protect false} ["plugin/lib"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                :figwheel {:on-jsload "proton.core/on-js-reload"}

                :compiler {:main proton.core
                           :target :nodejs
                           :optimizations :simple
                           :asset-path "out"
                           :output-to "plugin/lib/proton.js"
                           :output-dir "plugin/lib/out"
                           :source-map-timestamp true}}
               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "plugin/lib/proton.js"
                           :target :nodejs
                           :main proton.core
                           :optimizations :advanced
                           :pretty-print false}}]})
