(ns proton.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.lib.package_manager :as pm]
            [proton.lib.proton :as proton]
            [proton.lib.mode :as mode-manager]
            [proton.lib.keymap :as keymap-manager]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [join lower-case upper-case]]

            [proton.layers.base :as layerbase]
            [proton.layers.core.core :as core-layer]

            ;; tools
            [proton.layers.tools.minimap.core]
            [proton.layers.tools.git.core]
            [proton.layers.tools.linter.core]
            [proton.layers.tools.build.core]

            ;; etc
            [proton.layers.fun.power_mode.core]

            ;; langs
            [proton.layers.lang.clojure.core]
            [proton.layers.lang.python.core]
            [proton.layers.lang.julia.core]
            [proton.layers.lang.latex.core]
            [proton.layers.lang.markdown.core]
            [proton.layers.lang.elixir.core]
            [proton.layers.lang.javascript.core]
            [proton.layers.lang.rust.core]

            [proton.config.editor :as editor-config]
            [proton.config.proton :as proton-config]

            [cljs.core.async :as async :refer [chan put! pub sub unsub >! <!]]
            [clojure.string]))

;; reference to atom shell API
(def ashell (node/require "atom"))

;; js/atom is not the same as require 'atom'.
(def commands (.-commands js/atom))
(def workspace (.-workspace js/atom))
(def keymaps (.-keymaps js/atom))
(def views (.-views js/atom))

;; Atom for holding all disposables objects
(def disposables (atom []))

;; get atom.CompositeDisposable so we can work with it
(def composite-disposable (.-CompositeDisposable ashell))

;; Initialise new composite-disposable so we can add stuff to it later
(def subscriptions (new composite-disposable))
(swap! disposables conj subscriptions)

(defonce current-chain (atom []))

(def mode-keys [:m (keyword ",")])
(defn is-mode-key? [chain-key]
  (not (nil? (some #{(first chain-key)} mode-keys))))

(defn chain [e]
  (let [keystroke (helpers/extract-keystroke-from-event e)]
      ;; check for ESC key
      (if (= keystroke "escape")
        (atom-env/deactivate-proton-mode!)
        (do
          ;; append new key to chain
          (swap! current-chain conj (keyword (helpers/normalize-keystroke keystroke)))
          ;; check if the current character sequence is a action
          (let [keymaps (keymap-manager/find-keybindings @current-chain)]
            (if (or (nil? keymaps) (empty? keymaps))
              (atom-env/deactivate-proton-mode!)
              (if (keymap-manager/is-action? keymaps)
                (do
                  (atom-env/deactivate-proton-mode!)
                  (reset! current-chain [])
                  (keymap-manager/exec-binding keymaps))
                (atom-env/update-bottom-panel (helpers/tree->html keymaps)))))))))

(defn init []
  (go
    (proton/init-proton-mode-keymaps!)
    (atom-env/show-modal-panel)
    (atom-env/insert-process-step! "Initialising proton... Just a moment!" "")
    (let [{:keys [additional-packages layers configuration keybindings keymaps]} (proton/load-config)
          editor-default editor-config/default
          proton-default proton-config/default]
      (let [all-layers (into [] (distinct (concat (:layers proton-default) layers)))
            all-configuration (into [] (into (hash-map) (distinct (concat (:settings editor-default) (proton/configs-for-layers all-layers) configuration))))]

        (atom-env/insert-process-step! "Initialising layers")
        (proton/init-layers! all-layers all-configuration)
        (atom-env/mark-last-step-as-completed!)

        (let [layer-packages (into [] (distinct (concat (proton/packages-for-layers all-layers) additional-packages)))
              all-packages (into [] (distinct (concat layer-packages (:core-packages editor-default))))
              all-keymaps (into [] (distinct (concat keymaps (:keymaps editor-default) (proton/keymaps-for-layers all-layers))))
              all-keybindings (helpers/deep-merge (proton/keybindings-for-layers all-layers) keybindings)]

          ;; wipe existing config
          (atom-env/insert-process-step! "Wiping existing configuration")
          (doall (map atom-env/unset-config! (filter #(not (or (= "core.themes" %)
                                                               (= "core.disabledPackages" %)))
                                                      (atom-env/get-all-settings))))
          (atom-env/mark-last-step-as-completed!)

          ;; set the user config
          (atom-env/insert-process-step! "Applying user configuration")
          (doall (map #(atom-env/set-config! (get % 0) (get % 1)) all-configuration))
          (atom-env/mark-last-step-as-completed!)

          ;; save commands into command tree
          (atom-env/insert-process-step! "Initialising keybinding tree")
          (atom-env/mark-last-step-as-completed!)

          ;; set all custom keybindings from layers + user config
          (atom-env/insert-process-step! "Applying layer keymaps")
          (doall (map #(atom-env/set-keymap! (:selector %) (:keymap %)) all-keymaps))
          (atom-env/mark-last-step-as-completed!)

          ;; Install all necessary packages
          (let [to-install (pm/get-to-install all-packages)]
            (if (> (count to-install) 0)
              (do
                (atom-env/insert-process-step! (str "Installing " (count to-install) " new packages") "")
                (doseq [package to-install]
                  (atom-env/insert-process-step! (str "Installing " package))
                  (<! (pm/install-package (name package)))
                  (atom-env/mark-last-step-as-completed!)))
              (do
                (atom-env/insert-process-step! (str "Installing new packages: None"))
                (atom-env/mark-last-step-as-completed!))))

          ;; Remove deleted packages
          (let [to-remove (pm/get-to-remove all-packages)]
            (if (> (count to-remove) 0)
              (do
                (atom-env/insert-process-step! (str "Removing " (count to-remove) " orphaned packages") "")
                (doseq [package to-remove]
                  (atom-env/insert-process-step! (str "Removing " package))
                  (<! (pm/remove-package (name package)))
                  (atom-env/mark-last-step-as-completed!)))
              (do
                (atom-env/insert-process-step! (str "Removing orphaned packages: None"))
                (atom-env/mark-last-step-as-completed!))))

          ;; Make sure all collected packages are definitely enabled
          (doall (map #(pm/enable-package %) (filter pm/is-package? layer-packages)))

          (atom-env/insert-process-step! "All done!" "")
          (proton/init-modes-for-layers all-layers)
          (mode-manager/activate-mode (atom-env/get-active-editor))
          (keymap-manager/set-proton-leader-keys all-keybindings)
          (proton/init-proton-leader-keys! all-configuration)
          (let [config-map (into (hash-map) all-configuration)]
            (.setTimeout js/window #(atom-env/hide-modal-panel) (config-map "proton.core.post-init-timeout"))))))))

(defn on-space []
  (reset! current-chain [])
  (atom-env/update-bottom-panel (helpers/tree->html (keymap-manager/find-keybindings [])))
  (atom-env/activate-proton-mode!))

(defn on-mode-key []
  (reset! current-chain [])
  (if-let [mode-keymap (keymap-manager/get-mode-keybindings (keymap-manager/get-current-editor-mode))]
   (let [core-mode-key (first mode-keys)]
    (swap! current-chain conj core-mode-key)
    (atom-env/update-bottom-panel (helpers/tree->html (keymap-manager/find-keybindings @current-chain)))
    (atom-env/activate-proton-mode!))))

(defn toggle [e]
  (if (helpers/is-proton-target? e)
    (if (atom-env/is-proton-mode-active?)
      (atom-env/deactivate-proton-mode!)
      (on-space))
    (.abortKeyBinding e)))

(defn toggle-mode [e]
  (if (helpers/is-proton-target? e)
    (if (atom-env/is-proton-mode-active?)
      (atom-env/deactivate-proton-mode!)
      (on-mode-key))
    (.abortKeyBinding e)))

(defn activate [state]
  (.setTimeout js/window #(init) 2000)
  (swap! disposables conj (proton/panel-item-subscription))
  (.add subscriptions (.add commands "atom-workspace.proton-mode" "proton:chain" chain))
  (.add subscriptions (.add commands "atom-workspace" "proton:toggle" toggle))
  (.add subscriptions (.add commands "atom-workspace" "proton:toggleMode" toggle-mode)))

(defn deactivate []
  (.log js/console "deactivating...")
  (doseq [disposable @disposables]
    (.log js/console disposable)
    (.dispose disposable))
  (keymap-manager/cleanup!)
  (mode-manager/cleanup!)
  ;; wipe custom keybindings
  (atom-env/clear-keymap!)
  (atom-env/reset-process-steps!))

(defn serialize [] nil)

;; live-reload
;; calls stop before hotswapping code
;; then start after all code is loaded
;; the return value of stop will be the argument to start

(defn stop []
  (let [state (serialize)]
    (deactivate)
    state))

(defn start [state]
  (activate state))
