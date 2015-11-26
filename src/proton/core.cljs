(ns proton.core
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.lib.package_manager :as pm]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]
            [proton.layers.base :as layerbase]
            [proton.layers.core.core :as core-layer]))

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
(def required-packages (atom []))

(def enabled-layers [:core])
(doseq [layer enabled-layers]
  (println (layerbase/get-packages (keyword layer)))
  (swap! required-packages concat (layerbase/get-packages (keyword layer)))
  (swap! command-tree merge (layerbase/get-keybindings (keyword layer))))

(println @command-tree)
(println @required-packages)

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
                (atom-env/insert-html (helpers/tree->html extracted-chain)))))))))

(defn on-space []
  (reset! current-chain [])
  (atom-env/insert-html (helpers/tree->html @command-tree))
  (atom-env/activate-proton-mode!))

(defn ^:export activate [state]
  (.onDidMatchBinding keymaps #(if (= "space" (.-keystrokes %)) (on-space)))
  (.add subscriptions (.add commands "atom-text-editor.proton-mode" "proton:chain" chain)))

(defn ^:export deactivate [] (.log js/console "deactivating..."))

(defn noop [] nil)
(set! *main-cli-fn* noop)

;; We need to set module.exports to our core class.
;; Atom is using Proton.activate on this
(aset js/module "exports" proton.core)
