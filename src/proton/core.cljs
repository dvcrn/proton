(ns proton.core
  (:require [cljs.nodejs :as node]
            [clojure.string :as string :refer [lower-case upper-case]]))

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
(def subscriptions
    (new composite-disposable))

(defn div [text]
  (let [d (.createElement js/document "div")]
    (set! (.-textContent d) text)
    d))

(node/enable-util-print!)

(defn ^:export deactivate []
    (.log js/console "deactivating..."))

(def element (atom (div "test")))
(def modal-panel (atom (.addBottomPanel workspace
                                       (clj->js {:visible false
                                                  :item @element}))))

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

(defn is-action? [tree sequence]
  (println "is action?")
  (println (conj sequence :action))
  (println (get-in tree (conj sequence :action)))
  (not (nil? (get-in tree (conj sequence :action)))))

(def current-chain (atom []))

(defn make-pretty [tree]
  (->>
    (map (fn [element]
          (let [key (nth element 0)
                options (nth element 1)
                value (if (nil? (options :category))
                          (str "action:" (options :action))
                          (str "category:" (options :category)))]

            (str "<li>" key " --> " value "</li>")))
      (seq (dissoc tree :category)))
    (string/join " ")))

(defn extract-keyletter-from-event [event]
  (let [key (.fromCharCode js/String (.. event -originalEvent -keyCode))
        shift-key (.. event -originalEvent -shiftKey)]
      (if shift-key
        (keyword (upper-case key))
        (keyword (lower-case key)))))

(defn extract-keycode-from-event [event]
  (.. event -originalEvent -keyCode))

(defn activate-proton []
  (.log js/console "----> Proton Chain activated!")
  (let [editors (.getTextEditors workspace)]
      (doseq [editor editors]
        (let [view (.getView views editor)
              classList (.-classList view)]
            (.remove classList "vim-mode")
            (.add classList "proton-mode")
            (aset @element "innerHTML" (make-pretty (get-in mock-tree [])))
            (.show @modal-panel)
            (reset! current-chain [])))))

(defn deactivate-proton []
  (.log js/console "----> Proton Chain deactivated!")
  (let [editors (.getTextEditors workspace)]
      (doseq [editor editors]
        (let [view (.getView views editor)
              classList (.-classList view)]
            (.remove classList "proton-mode")
            (.add classList "vim-mode")
            (.hide @modal-panel)))))


(defn eval-action! [tree sequence]
  (println "evalling")
  (let [action (get-in tree (conj sequence :action))]
    (.dispatch commands (.getView views workspace) action)
    (deactivate-proton)))


(defn ^:export chain [e]
  (let [letter (extract-keyletter-from-event e)
        key-code (extract-keycode-from-event e)]
      (if (= key-code 27)
        (deactivate-proton)
        (let [element @element]
          (swap! current-chain conj letter)
          (println "current chain:")
          (println @current-chain)
          (if (is-action? mock-tree @current-chain)
            (eval-action! mock-tree @current-chain)
            (let [extracted-chain (get-in mock-tree @current-chain)]
              (println "not an action")
              (println extracted-chain)
              (if (nil? extracted-chain)
                (deactivate-proton)
                (aset element "innerHTML" (make-pretty extracted-chain)))))))))

(defn ^:export activate [state]
  (.onDidMatchBinding keymaps #(if (= "space" (.-keystrokes %)) (activate-proton)))
  (.add subscriptions
        (.add commands "atom-text-editor.proton-mode" "proton:chain" chain)))

(defn noop [] nil)
(set! *main-cli-fn* noop)

;; We need to set module.exports to our core class.
;; Atom is using Proton.activate on this
(aset js/module "exports" proton.core)
