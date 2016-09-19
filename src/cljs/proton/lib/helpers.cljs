(ns proton.lib.helpers
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.string :as string :refer [split upper-case lower-case join]]
            [cljs.nodejs :as node]
            [clojure.set :as cljset]
            [cljs.core.async :as async :refer [close! chan >! <!]]
            [proton.config.proton :as config]))

(def fs (node/require "fs"))
(def path (node/require "path"))
(def child-process (node/require "child_process"))

;; seperate map with overrides. 189 (underscore) kept getting resolved as '½' which we don't want.
(def char-code-override {189 "_"
                         192 "/"
                         9 "tab"})

(def keystroke->keybinding-map
   {"/" :slash
    ":" :colon
    ";" :semicolon
    "'" :quote})

(defn keystroke->keybinding
  "Remove 'shift-' prefix from keystroke. For uppercase letter atom keystroke will be
  'shift-<capital_letter>' e.g. :S equal to 'shift-S'."
  [keystroke]
  (if (zero? (.indexOf keystroke "shift-"))
    (last keystroke)
    (if-let [keybinding (get keystroke->keybinding-map keystroke)]
      keybinding
      keystroke)))

(defn keybinding->keystroke [keybinding]
  (let [keystroke (filter (comp #{(keyword keybinding)} keystroke->keybinding-map) (keys keystroke->keybinding-map))]
    (if-not (empty? keystroke)
      (first keystroke)
      keybinding)))

(defn generate-div [text class-name]
  (let [d (.createElement js/document "div")]
    (set! (.-textContent d) text)
    (.add (.-classList d) class-name)
    d))

(defn read-file [path]
  (.readFileSync fs path #js {:encoding "utf8"}))

(defn is-file? [path]
  (try
    (.isFile (.statSync fs path))
    (catch js/Error e
      false)))

(defn extract-keyletter-from-event [event]
  (let [key-code (.. event -originalEvent -keyCode)
        key (if (nil? (char-code-override key-code))
                (.fromCharCode js/String key-code)
                (char-code-override key-code))
        shift-key (.. event -originalEvent -shiftKey)]

      (if shift-key
        (keyword (upper-case key))
        (keyword (lower-case key)))))

(defn extract-keycode-from-event [event]
  (.. event -originalEvent -keyCode))

(defn extract-keystroke-from-event [event]
  (.keystrokeForKeyboardEvent js/atom.keymaps (.-originalEvent event)))

(defn console!
  ([s] (console! s nil))
  ([s key] (console! s key true))
  ([s key js-print]
   (let [k (if (nil? key) "proton" (str "proton:" key))
         s (str "["k"] " s)]
    (if (config/default :debug)
      (if js-print
        (.log js/console s)
        (println s))))))

(defn keybinding-row-html [keybinding]
  (let [options (nth keybinding 1)
        {:keys [category action title]} options
        is-category? ((comp not nil?) category)
        class-name (if is-category? "proton-key-category" "proton-key-action")
        value (if is-category? (str "+" category) (or title action))
        keystroke (-> keybinding first str rest join keybinding->keystroke)]
      (str "<li class='proton-which-key-item'><span class='proton-key'>[" keystroke "]</span> ➜ <span class='" class-name "'>" value "</span></li>")))

(defn show-item? [[_ options]]
  (nil? (options :hidden)))

(defn- panel-footer [category current-chain]
  (let [chain-keys (if (empty? current-chain) "SPC" (string/join " " (concat ["SPC"] (map name current-chain))))
        category-name (or category "proton root")]
    (str "<div class='proton-which-key-footer clearfix'><span class='pull-left proton-chain-key'>" chain-keys "</span><span class='pull-right proton-chain-category'>" category-name "</span></div>")))

(defn tree->html [tree current-chain]
  (->>
    (sort (seq (dissoc tree :category)))
    (filter show-item?)
    (map keybinding-row-html)
    (string/join " ")
    (conj [])
    (apply #(str "<ul class='proton-which-key-container'>" % "</ul>" (panel-footer (tree :category) current-chain)))))

(defn process->html [steps]
  (let [steps-html (map #(str "<tr><td class='process-step'>" (get % 0) "</td><td class='process-status'>" (get % 1) "</td></tr>") steps)]
    (str "<h2>Welcome to proton</h2><table>" (string/join " " steps-html) "</table>")))

(defn deep-merge
  "Deeply merges maps so that nested maps are combined rather than replaced.
  For example:
  (deep-merge {:foo {:bar :baz}} {:foo {:fuzz :buzz}})
  ;;=> {:foo {:bar :baz, :fuzz :buzz}}
  ;; contrast with clojure.core/merge
  (merge {:foo {:bar :baz}} {:foo {:fuzz :buzz}})
  ;;=> {:foo {:fuzz :quzz}} ; note how last value for :foo wins"
  [& vs]
  (if (every? map? vs)
    (apply merge-with deep-merge vs)
    (last vs)))

(defn deep-merge-with
  "Deeply merges like `deep-merge`, but uses `f` to produce a value from the
  conflicting values for a key in multiple maps."
  [f & vs]
  (if (every? map? vs)
    (apply merge-with (partial deep-merge-with f) vs)
    (apply f vs)))


(defn is-proton-target? [e]
  (let [target (.-target e)
        tag-name (string/lower-case (.-tagName target))
        class-list (set (array-seq (.-classList target)))
        ignored-tags #{"input" "textarea"}
        tag-ok? (nil? (some #{tag-name} ignored-tags))
        ignored-attrs ["mini"]
        ignored-attrs-ok? (empty? (filter (comp not nil?) (map #(.getNamedItem (.-attributes target) %) ignored-attrs)))
        ignored-editor-classes #{"insert-mode" "mini"}
        ignored-editor-class-ok? (empty? (cljset/intersection class-list ignored-editor-classes))
        required-editor-classes #{"vim-mode" "vim-mode-plus"}
        vim-mode? (some #{(js/atom.config.get "proton.core.inputProvider")} ["vim-mode" "vim-mode-plus"])
        required-editor-class-ok? (or (not (empty? (cljset/intersection class-list required-editor-classes))) (not vim-mode?))]
    (if tag-ok?
      (if (= tag-name "atom-text-editor")
        (and required-editor-class-ok? ignored-attrs-ok? ignored-editor-class-ok?)
        true)
      false)))

(defn- parse-env [env]
  (into (hash-map) (filter #(> (count %) 1) (map #(string/split % #"=") (string/split env #"\n")))))

(defn sync-env-path! []
  (when-not (= js/process.platform "win32")
    (let [path-chan (chan)
          shell-path (or (.-SHELL js/process.env ) "/bin/sh")]
      (go
        (let [shell (.spawn child-process shell-path (js/Array "-c", "env; exit") (js/Object :encoding "UTF-8"))]
          (.on (.-stdout shell) "data"
            (fn [stdout]
              (go (>! path-chan (.toString stdout)))))
         (.on shell "exit"
          (fn [code]
            (when-not (zero? code)
              (go (>! path-chan false)))))))
      ;; on out
      (go
        (if-let [out-val (<! path-chan)]
          (do
            (let [env-hash (parse-env out-val)]
              (if-let [path-val (env-hash "PATH")]
                (do
                  (console! (str "Old $PATH: " js/process.env.PATH) :helpers/sync-env-path)
                  (set! js/process.env.PATH path-val)
                  (console! (str "New $PATH: " js/process.env.PATH) :helpers/sync-env-path))))
            (close! path-chan))
          (close! path-chan))))))

(defn config-reducer
   "Push config to collection if no exists or it has options value.
    Options is 3-rd element in vector. e.g. ['editor.tabLength' 2 {:scopeSelector ['.source.ruby']}]
    Update config value when no options passed."
   [coll config]
  (let [config-name (first config)
        matched (filter #(= (key %) config-name) coll)
        matched-no-opts (first (filter #(= 2 (count %)) matched))]
    (if (empty? matched)
      (conj coll config)
      ;; check for options
      (if (nth config 2 false)
        (conj coll config)
        (conj (remove #{matched-no-opts} coll) config)))))
