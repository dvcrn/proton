(ns proton.core
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.lib.package_manager :as pm]
            [proton.lib.proton :as proton]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]
            [proton.layers.base :as layerbase]
            [proton.layers.core.core :as core-layer]
            [proton.layers.git.core :as git-layer]
            [proton.config.editor :as editor-config]
            [proton.config.proton :as proton-config]))

(node/enable-util-print!)

;; reference to atom shell API
(def ashell (node/require "atom"))

;; js/atom is not the same as require 'atom'.
(def commands (.-commands js/atom))
(def workspace (.-workspace js/atom))
(def keymaps (.-keymaps js/atom))
(def views (.-views js/atom))

;; get atom.CompositeDisposable so we can work with it
(def composite-disposable (.-CompositeDisposable ashell))

;; Initialise new composite-disposable so we can add stuff to it later
(def subscriptions (new composite-disposable))

(def command-tree (atom {}))
(def current-chain (atom []))

(defn ^:export chain [e]
  (let [letter (helpers/extract-keyletter-from-event e)
        key-code (helpers/extract-keycode-from-event e)]
      ;; check for ESC key
      (if (= key-code 27)
        (atom-env/deactivate-proton-mode!)
        (do
          ;; append new key to chain
          (swap! current-chain conj letter)
          ;; check if the current character sequence is a action
          (if (helpers/is-action? @command-tree @current-chain)
            (atom-env/eval-action! @command-tree @current-chain)
            ;; if not, continue chaining
            (let [extracted-chain (get-in @command-tree @current-chain)]
              (if (nil? extracted-chain)
                (atom-env/deactivate-proton-mode!)
                (atom-env/update-bottom-panel (helpers/tree->html extracted-chain)))))))))

(defn init []
  (let [{:keys [additional-packages layers configuration keybindings]} (proton/load-config)
        editor-default editor-config/default
        proton-default proton-config/default]

    (let [all-layers (into [] (distinct (concat (:layers proton-default) layers)))
          all-configuration (into [] (distinct (concat editor-default configuration)))
          all-packages (into [] (distinct (concat (proton/packages-for-layers all-layers) additional-packages)))
          all-keybindings (proton/keybindings-for-layers all-layers)]

      ;; wipe existing config
      (doall (map atom-env/unset-config! (atom-env/get-all-settings)))
      ;; set the user config
      (doall (map #(atom-env/set-config! (get % 0) (get % 1)) all-configuration))

      ;; save commands into command tree
      (reset! command-tree all-keybindings)

      ;; Remove deleted packages
      (println "removing:")
      (println (pm/get-removed all-packages))
      (doall (map #(pm/remove-package (name %)) (pm/get-removed all-packages)))
      ;; Install all necessary packages
      (println "installing:")
      (println all-packages)
      (doall (map #(pm/install-package (name %)) all-packages)))))

(defn on-space []
  (reset! current-chain [])
  (atom-env/update-bottom-panel (helpers/tree->html @command-tree))
  (atom-env/activate-proton-mode!))

(defn ^:export activate [state]
  (init)
  (.onDidMatchBinding keymaps #(if (= "space" (.-keystrokes %)) (on-space)))
  (.add subscriptions (.add commands "atom-text-editor.proton-mode" "proton:chain" chain)))

(defn ^:export deactivate [] (.log js/console "deactivating..."))

;; We need to set module.exports to our core class.
;; Atom is using Proton.activate on this
(defn noop [] nil)
(set! *main-cli-fn* noop)
(aset js/module "exports" proton.core)
