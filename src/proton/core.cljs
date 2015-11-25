(ns proton.core
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.lib.package_manager :as pm]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]))

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

(def mock-tree
  {:g {
        :category "git"
        :s {:action "git_status"}
        :c {:action "git_commit"}
        :p {:action "git_push"}
        :P {:action "git_pull"}}
   :w {:category "window"
        :m {:action "maximise"}}
   :b {:category "buffer"
        :m {:action "maximise"}}
   :p {:category "project"
        :t {:action "tree-view:toggle"}}})

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
          (if (helpers/is-action? mock-tree @current-chain)
            (atom-env/eval-action! mock-tree @current-chain)
            ;; if not, continue chaining
            (let [extracted-chain (get-in mock-tree @current-chain)]
              (if (nil? extracted-chain)
                (atom-env/deactivate-proton-mode!)
                (atom-env/insert-html (helpers/tree->html extracted-chain)))))))))

(defn on-space []
  (reset! current-chain [])
  (atom-env/insert-html (helpers/tree->html mock-tree))
  (atom-env/activate-proton-mode!))

(defn ^:export activate [state]
  (.log js/console (pm/is-installed? "vim-mode"))
  (.log js/console "installing testing:")
  (.log js/console (pm/install-package "vim-mode"))
  (.log js/console (pm/install-package "asdfjasdfjsakdf-mode"))
  (.log js/console (pm/get-apm-path))
  (.onDidMatchBinding keymaps #(if (= "space" (.-keystrokes %)) (on-space)))
  (.add subscriptions (.add commands "atom-text-editor.proton-mode" "proton:chain" chain)))

(defn ^:export deactivate [] (.log js/console "deactivating..."))

(defn noop [] nil)
(set! *main-cli-fn* noop)

;; We need to set module.exports to our core class.
;; Atom is using Proton.activate on this
(aset js/module "exports" proton.core)
