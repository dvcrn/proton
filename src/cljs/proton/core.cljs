(ns proton.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.lib.package_manager :as pm]
            [proton.lib.proton :as proton]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]

            [proton.layers.base :as layerbase]
            [proton.layers.core.core :as core-layer]

            ;; tools
            [proton.layers.tools.minimap.core :as minimap-layer]
            [proton.layers.tools.git.core :as git-layer]
            [proton.layers.tools.pomodoro.core :as pomodoro-layer]

            ;; langs
            [proton.layers.lang.clojure.core :as clojure-layer]
            [proton.layers.lang.python.core :as python-layer]
            [proton.layers.lang.julia.core :as julia-layer]

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

(def command-tree (atom {}))
(def current-chain (atom []))

(defn chain [e]
  (let [key-code (helpers/extract-keycode-from-event e)
        letter (helpers/extract-keyletter-from-event e)]

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
  (go
    (atom-env/show-modal-panel)
    (atom-env/insert-process-step! "Initialising proton... Just a moment!" "")
    (let [{:keys [additional-packages layers configuration keybindings keymaps]} (proton/load-config)
          editor-default editor-config/default
          proton-default proton-config/default]

      (let [all-layers (into [] (distinct (concat (:layers proton-default) layers)))
            all-configuration (into [] (distinct (concat (:settings editor-default) (proton/configs-for-layers all-layers) configuration)))
            all-disabled (:ensure-disabled editor-default)]

        (doall (map pm/disable-package (map name all-disabled)))

        ;; Init layers
        (atom-env/insert-process-step! "Initialising layers")
        (proton/init-layers! all-layers all-configuration)
        (atom-env/mark-last-step-as-completed!)

        (let [all-packages (into [] (distinct (concat (proton/packages-for-layers all-layers) additional-packages)))
              all-keymaps (into [] (distinct (concat keymaps (:keymaps editor-default) (proton/keymaps-for-layers all-layers))))
              all-keybindings (proton/keybindings-for-layers all-layers)]

          ;; wipe existing config
          (atom-env/insert-process-step! "Wiping existing configuration")
          (doall (map atom-env/unset-config! (filter #(not
                                                        (or (= "core.disabledPackages" %)
                                                            (= "core.themes" %))) (atom-env/get-all-settings))))
          (atom-env/mark-last-step-as-completed!)

          ;; set the user config
          (atom-env/insert-process-step! "Applying user configuration")
          (doall (map #(atom-env/set-config! (get % 0) (get % 1)) all-configuration))
          (atom-env/mark-last-step-as-completed!)

          ;; save commands into command tree
          (atom-env/insert-process-step! "Initialising keybinding tree")
          (reset! command-tree all-keybindings)
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

          (atom-env/insert-process-step! "All done!" "")
          (.setTimeout js/window #(atom-env/hide-modal-panel) 3000))))))

(defn on-space []
  (reset! current-chain [])
  (atom-env/update-bottom-panel (helpers/tree->html @command-tree))
  (atom-env/activate-proton-mode!))

(defn activate [state]
  (.setTimeout js/window #(init) 2000)
  (let [disposable (.onDidMatchBinding keymaps #(if (= "space" (.-keystrokes %)) (on-space)))]
    (swap! disposables conj disposable))
  (.add subscriptions (.add commands "atom-text-editor.proton-mode" "proton:chain" chain)))

(defn deactivate []
  (.log js/console "deactivating...")
  (doseq [disposable @disposables]
    (.log js/console disposable)
    (.dispose disposable))
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
