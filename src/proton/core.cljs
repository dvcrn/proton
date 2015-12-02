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
            [proton.layers.git.core :as git-layer]
            [proton.layers.clojure.core :as clojure-layer]
            [proton.layers.python.core :as python-layer]

            [proton.config.editor :as editor-config]
            [proton.config.proton :as proton-config]

            [cljs.core.async :as async :refer [chan put! pub sub unsub >! <!]]
            [clojure.string]))

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
            all-packages (into [] (distinct (concat (proton/packages-for-layers all-layers) additional-packages)))
            all-keymaps (into [] (distinct (concat keymaps (:keymaps editor-default) (proton/keymaps-for-layers all-layers))))
            all-keybindings (proton/keybindings-for-layers all-layers)]


        (atom-env/insert-process-step! "Initialising layers")
        (proton/init-layers! all-layers all-configuration)
        (atom-env/mark-last-step-as-completed!)

        ;; wipe existing config
        (atom-env/insert-process-step! "Wiping existing configuration")
        (doall (map atom-env/unset-config! (atom-env/get-all-settings)))
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
        (.setTimeout js/window #(atom-env/hide-modal-panel) 3000)))))

(defn on-space []
  (reset! current-chain [])
  (atom-env/update-bottom-panel (helpers/tree->html @command-tree))
  (atom-env/activate-proton-mode!))

(defn ^:export activate [state]
  (.setTimeout js/window #(init) 2000)
  (.onDidMatchBinding keymaps #(if (= "space" (.-keystrokes %)) (on-space)))
  (.add subscriptions (.add commands "atom-text-editor.proton-mode" "proton:chain" chain)))

(defn ^:export deactivate [] (.log js/console "deactivating..."))

;; We need to set module.exports to our core class.
;; Atom is using Proton.activate on this
(defn noop [] nil)
(set! *main-cli-fn* noop)
(aset js/module "exports" proton.core)
