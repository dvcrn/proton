(ns proton.lib.package_manager
  (:require [cljs.nodejs :as node]))

(def sys (node/require "sys"))
(def child-process (node/require "child_process"))

(def packages (.-packages js/atom))

(defn get-apm-path []
  (.getApmPath packages))

(defn install-package [package-name]
  (try
    (do
      (.execSync child-process (str (get-apm-path) " install " package-name " --no-colors"))
      true)
    (catch js/Error e
      false)))

(defn is-installed? [package-name]
  (let [pkgs (.getAvailablePackageNames packages)]
    (not (= -1 (.indexOf pkgs package-name)))))
