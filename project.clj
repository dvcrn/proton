(defproject proton "0.1.0-SNAPSHOT"
  :description "Spacemacs helps Atom to become a better editor"
  :url "https://github.com/dvcrn/proton/"
  :license {:name "GPLv3"
            :url "https://github.com/dvcrn/proton/blob/master/LICENSE.md"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]]

  :source-paths ["src/cljs"]
  :profiles {:dev {:source-paths ["src/dev"]
                   :dependencies [[thheller/shadow-build "1.0.186"]]}})
