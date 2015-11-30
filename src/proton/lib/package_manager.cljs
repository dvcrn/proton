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

(defn enable-package [package-name]
  (.enablePackage packages package-name))

(defn disable-package [package-name]
  (.disablePackage packages package-name))

(defn reload-package [package-name]
  (disable-package package-name)
  (enable-package package-name))

(defn install-package [package-name]
  (if (is-installed? package-name)
    true
    (try
      (do
        (.execSync child-process (str (get-apm-path) " install " package-name " --no-colors"))
        ;; Disable and enable here to force Atom to reload the packages
        ;; We are doing this with a 2s delay to give Atom some time to find the new package
        (.setTimeout js/window #(reload-package package-name) 2000)
        true)
      (catch js/Error e
        false))))

(defn remove-package [package-name]
  (if (not (is-installed? package-name))
    true
    (try
      (do
        (disable-package package-name)
        (.execSync child-process (str (get-apm-path) " uninstall " package-name " --no-colors"))
        true)
      (catch js/Error e
        false))))
