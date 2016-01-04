(ns proton.lib.proton
  (:require [cljs.reader :as reader]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [upper-case]]
            [proton.lib.mode :as mode-manager]
            [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.layers.base :as layerbase]
            [proton.config.proton :as config]))

(def path (node/require "path"))
(def config-path (config/default :config-path))

(defn get-config-template-path []
  (.resolve path (str js/__dirname "/../templates/proton.edn")))

(defn has-config? []
  (helpers/is-file? config-path))

(defn create-default-config! []
  (let [child-process (node/require "child_process")
        template-path (get-config-template-path)]
    (.execSync child-process (str "cp " template-path " " config-path))))

(defn load-config []
  (if (not (has-config?))
    (create-default-config!))
  (reader/read-string (helpers/read-file config-path)))

(defn packages-for-layers [layers]
  (let [layer-packages (reduce concat (map #(layerbase/get-packages (keyword %)) layers))
        layer-dependencies (reduce concat (map #(if (contains? @layerbase/layer-dependencies %) (get @layerbase/layer-dependencies %)) layers))]

    (into [] (distinct (concat layer-packages layer-dependencies)))))

(defn keybindings-for-layers [layers]
  (reduce helpers/deep-merge (map #(layerbase/get-keybindings (keyword %)) layers)))

(defn configs-for-layers [layers]
  (apply concat
    (filter #(not (empty? %))
      (map #(layerbase/get-initial-config (keyword %)) layers))))

(defn keymaps-for-layers [layers]
  (reduce concat (map #(layerbase/get-keymaps (keyword %)) layers)))

(defn init-layers! [layers config]
  (doall (map #(layerbase/init-layer! (keyword %) {:config config :layers layers}) layers)))

(defn init-modes-for-layers [layers]
  (doall
    (map #(mode-manager/define-mode (get % :mode-name) (dissoc % :mode-name))
     (filter #(not (nil? (get % :mode-name))) (map #(layerbase/describe-mode %) layers)))))

(defn- on-active-pane-item [item]
  (if-let [editor (atom-env/get-active-editor)]
    (when (= (.-id editor) (.-id item))
     (mode-manager/activate-mode editor))))

(defn panel-item-subscription [] (.onDidChangeActivePaneItem atom-env/workspace on-active-pane-item))

(defn init-proton-mode-keymaps! []
  (let [selector "atom-text-editor.proton-mode"
        command "proton:chain"
        make-char-fn (comp (partial map char) range)
        letters (make-char-fn 97 123)
        numbers (map str (range 0 10))
        punctuation (flatten (map #(apply make-char-fn %) [[33 48] [58 65] [91 97] [123 127]]))
        special-chars ["escape" "tab" "backspace" "delete"]
        all-chars (into [] (concat [] letters (map string/upper-case letters) numbers special-chars punctuation))
        combo ["ctrl-" "ctrl-alt-" "ctrl-shift-" "ctrl-shift-alt-" "alt-" "alt-shift-" "cmd-" "cmd-shift-" "cmd-alt-" "cmd-alt-shift-" "cmd-ctrl-" "cmd-ctrl-shift-"]
        all-combos (for [x all-chars y combo] (str y x))
        ret (flatten (concat [] all-chars all-combos))
        ret-map (reduce #(assoc %1 %2 command) {} (map identity ret))]
      (atom-env/set-keymap! selector ret-map)))
