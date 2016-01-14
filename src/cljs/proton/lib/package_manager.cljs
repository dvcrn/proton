(ns proton.lib.package_manager
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.nodejs :as node]
            [cljs.core.async :as async :refer [close! chan put! pub sub unsub >! <!]]
            [proton.lib.mode :as mode]
            [proton.lib.keymap :as keymap]
            [proton.lib.atom :as atom]
            [proton.lib.proton :as proton]
            [proton.lib.helpers :as helpers]))

(def sys (node/require "sys"))
(def child-process (node/require "child_process"))

(defonce packages (atom {}))

(defn cleanup! []
  (reset! packages {}))

(defn register-packages [package-names]
  (let [packages-map (reduce #(assoc-in %1 [%2] {:atom-disabled (atom/is-package-disabled? (name %2))}) {} package-names)]
    (swap! packages #(helpers/deep-merge packages-map %))))

(defn set-installed! [package-name]
  (swap! packages assoc-in [(keyword package-name) :atom-disabled] false))

(defn unset-package! [package-name]
  (swap! packages dissoc (keyword package-name)))

(defn enable-package [package-name]
  (when (atom/is-package-disabled? (name package-name))
    (do
      (atom/enable-package (name package-name))))
  (proton/run-init-package-hook package-name)
  (swap! packages update-in [(keyword package-name)] assoc :atom-disabled false :proton-disabled false))

(defn disable-package [package-name]
  (when-not (atom/is-package-disabled? (name package-name))
    (atom/disable-package (name package-name)))
  (mode/unset-mode! (mode/package-mode-name package-name))
  (keymap/unset-keymap-for-mode! (mode/package-mode-name package-name))
  (swap! packages update-in [(keyword package-name)] assoc :atom-disabled true :proton-disabled true))

(defn get-to-remove [all-packages]
  (let [pkgs (set all-packages)]
    (filter #(if (not (contains? pkgs %)) %) (into [] (map keyword (atom/get-all-packages))))))

(defn get-to-install [all-packages]
  (let [pkgs (set (into [] (map keyword (atom/get-all-packages))))]
    (filter #(if (not (contains? pkgs %)) %) all-packages)))

(defn activate-packages! []
  (let [enabled-packages (filter #(not ((val %) :proton-disabled)) @packages)
        disabled-packages (filter #(= true ((val %) :proton-disabled)) @packages)]
    (doall (map #(enable-package (key %)) enabled-packages))
    (doall (map #(disable-package (key %)) disabled-packages))))

(defn install-package [package-name]
  (helpers/console! (str "Installing: " package-name))
  (let [c (chan)]
    (go
      (if (atom/is-package-installed? package-name)
        (do
          (>! c true)
          (close! c))
        (.exec child-process (str (atom/get-apm-path) " install " package-name " --no-colors")
          (fn [err stdout stderr]
            (if (nil? err)
              ;; Disable and enable here to force Atom to reload the packages
              ;; We are doing this with a 1s delay to give Atom some time to find the new package
              (.setTimeout js/window #(do
                                        (atom/force-reload-package package-name)
                                        (set-installed! package-name)
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
      (if (not (atom/is-package-installed? package-name))
        (do
          (>! c true)
          (close! c))
        (do
          ;; disable the package first to make sure no errors pop up on the way
          (atom/disable-package package-name)
          (.exec child-process (str (atom/get-apm-path) " uninstall " package-name " --hard --no-colors")
            (fn [err stdout stderr]
              (if (nil? err)
                (do
                  (unset-package! package-name)
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

(defn on-package-deactivated [package]
  (let [package-name (.-name package)]
    (helpers/console! (str "atom disabled package " package-name) :package_manager/on-package-deactivated)
    (disable-package (keyword package-name))))

(defn on-package-activated [package]
  (let [package-name (.-name package)]
    (helpers/console! (str "atom activated package " package-name) :package_manager/on-package-activated)
    (enable-package (keyword package-name))))

(defn init-subscriptions! []
  (.add atom/subscriptions (.onDidDeactivatePackage atom/packages on-package-deactivated))
  (.add atom/subscriptions (.onDidActivatePackage atom/packages on-package-activated)))
