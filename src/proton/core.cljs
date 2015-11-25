(ns proton.core
  (:require [cljs.nodejs :as node]))

;; reference to atom shell API
(def ashell (node/require "atom"))

;; js/atom is not the same as require 'atom'.
(def commands (.-commands js/atom))
(def workspace (.-workspace js/atom))

;; get atom.CompositeDisposable so we can work with it
(def composite-disposable (.-CompositeDisposable ashell))

;; Initialise new composite-disposable so we can add stuff to it later
(def subscriptions
    (new composite-disposable))

(defn div [text]
  (let [d (.createElement js/document "div")]
    (set! (.-textContent d) text)
    d))

(node/enable-util-print!)

(defn ^:export deactivate []
    (.log js/console "deactivating..."))

(def modal-panel (atom (.addModalPanel workspace
                                       (clj->js {:visible false
                                                  :item (div "test")}))))

(defn ^:export toggle []
  (.log js/console "I got toggled!")
  (.show @modal-panel))

(defn ^:export activate [state]
  (.log js/console "Hello World")
  (.add subscriptions
        (.add commands "atom-text-editor.vim-mode:not(.insert-mode)" "proton:toggle" toggle))

  (.add subscriptions
        (.add commands "atom-workspace" "proton:toggle" toggle)) )

(defn noop [] nil)
(set! *main-cli-fn* noop)

;; We need to set module.exports to our core class.
;; Atom is using Proton.activate on this
(aset js/module "exports" proton.core)
