(ns proton.lib.package_manager
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.nodejs :as node]
            [cljs.core.async :as async :refer [close! chan put! pub sub unsub >! <!]]
            [proton.lib.atom :as atom]
            [proton.lib.helpers :as helpers]))

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

(defn get-to-remove [all-packages]
  (let [pkgs (set all-packages)]
    (filter #(if (not (contains? pkgs %)) %) (into [] (map keyword (get-all-packages))))))

(defn get-to-install [all-packages]
  (let [pkgs (set (into [] (map keyword (get-all-packages))))]
    (filter #(if (not (contains? pkgs %)) %) all-packages)))

(defn is-activated? [package-name]
  (let [package-names (->> (.getActivePackages packages) js->clj (map #(.-name %)))
        filtered-packages (filter #(= package-name %) package-names)]
    (> (count filtered-packages) 0)))

(defn enable-package [package-name]
  (helpers/console! (str "enabling package " package-name))
  (.enablePackage packages package-name))

(defn disable-package
  ([package-name]
   (disable-package package-name false))

  ([package-name force]
   (if (or (is-activated? package-name) force)
       (do
         (helpers/console! (str "disabling package " package-name))
         (.disablePackage packages package-name)))))

(defn reload-package [package-name]
  (disable-package package-name)
  (enable-package package-name))

(defn force-reload-package [package-name]
  (disable-package package-name true)
  (enable-package package-name))

(defn install-package [package-name]
  (helpers/console! (str "Installing: " package-name))
  (let [c (chan)]
    (go
      (if (is-installed? package-name)
        (do
          (>! c true)
          (close! c))
        (.exec child-process (str (get-apm-path) " install " package-name " --no-colors")
          (fn [err stdout stderr]
            (if (nil? err)
              ;; Disable and enable here to force Atom to reload the packages
              ;; We are doing this with a 1s delay to give Atom some time to find the new package
              (.setTimeout js/window #(do
                                        (force-reload-package package-name)
                                        (helpers/console! (str "done: " package-name))
                                        (go (>! c true))
                                        (close! c))
                1000)

              ;; On error, just close the channel
              (do
                (go (>! c false))
                (close! c)))))))
    c))

(defn remove-package [package-name]
  (helpers/console! (str "Removing: " package-name))
  (let [c (chan)]
    (go
      (if (not (is-installed? package-name))
        (do
          (>! c true)
          (close! c))
        (do
          ;; disable the package first to make sure no errors pop up on the way
          (disable-package package-name)
          (.exec child-process (str (get-apm-path) " uninstall " package-name " --no-colors")
            (fn [err stdout stderr]
              (if (nil? err)
                (do
                  (go (>! c true))
                  (close! c))
                (do
                  (go (>! c false))
                  (close! c))))))))
    c))


(defn remove-packages [packages]
  (let [chans (map remove-package packages)
        merged-chan (async/merge chans)
        reduced-chan (async/reduce #(and %1 %2) true merged-chan)]
    reduced-chan))

(defn install-packages [packages]
  (let [chans (map install-package packages)
        merged-chan (async/merge chans)
        reduced-chan (async/reduce #(and %1 %2) true merged-chan)]
    reduced-chan))
