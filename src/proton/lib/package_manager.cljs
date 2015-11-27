(ns proton.lib.package_manager
  (:require [cljs.nodejs :as node]))

(def sys (node/require "sys"))
(def child-process (node/require "child_process"))

(def packages (.-packages js/atom))

(defn get-apm-path []
  (.getApmPath packages))

(defn get-all-packages []
  (.getAvailablePackageNames packages))

(defn is-installed? [package-name]
  (let [pkgs (get-all-packages)]
    (not (= -1 (.indexOf pkgs package-name)))))

(defn get-removed [all-packages]
  (let [pkgs (set all-packages)]
    (filter #(if (not (contains? pkgs %)) %) (into [] (map keyword (get-all-packages))))))

(defn install-package [package-name]
  (if (is-installed? package-name)
    true
    (try
      (do
        (.execSync child-process (str (get-apm-path) " install " package-name " --no-colors"))
        true)
      (catch js/Error e
        false))))

(defn remove-package [package-name]
  (if (not (is-installed? package-name))
    true
    (try
      (do
        (.execSync child-process (str (get-apm-path) " uninstall " package-name " --no-colors"))
        true)
      (catch js/Error e
        false))))
