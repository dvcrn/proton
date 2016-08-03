(ns proton.lib.proton
  #_(:require [proton.core])
  (:require [cljs.reader :as reader]
            [cljs.nodejs :as node]
            [clojure.string :as string :refer [upper-case join starts-with?]]
            [proton.lib.mode :as mode-manager]
            [proton.lib.helpers :as helpers]
            [proton.lib.atom :as atom-env]
            [proton.layers.base :as layerbase]
            [proton.config.proton :as config]))

(def path (node/require "path"))
(def fs (node/require "fs"))
(def config-path (config/default :config-path))

(defn get-config-template-path []
  (.resolve path (str js/__dirname "/../templates/proton.edn")))

(defn has-config? []
  (helpers/is-file? config-path))

(defn create-default-config! []
  (let [template-read-stream (.createReadStream fs (get-config-template-path))]
    (helpers/console! (str "Copying template from " (get-config-template-path) " to " config-path))
    (.pipe template-read-stream (.createWriteStream fs config-path))))

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

(defn run-init-package-hook [package-name]
  (if-let [selected-layers (atom-env/get-config "proton.core.selectedLayers")]
    (let [layers (map keyword (array-seq selected-layers))]
      (doall (map #(layerbase/init-package [% package-name]) layers)))))

(defn- on-active-pane-item [item]
  (if-let [editor (atom-env/get-active-editor)]
    (when (= (.-id editor) (.-id item))
     (mode-manager/activate-mode editor))))

(defn panel-item-subscription [] (.onDidChangeActivePaneItem atom-env/workspace on-active-pane-item))

(defn init-proton-mode-keymaps! []
  (let [selectors ["body atom-workspace.proton-mode atom-text-editor:not([mini])"
                   "body atom-workspace.proton-mode atom-panel-container.right"
                   "body atom-workspace.proton-mode atom-panel-container.right atom-panel.right.tool-panel"
                   "body atom-workspace.proton-mode atom-panel-container.left"
                   "body atom-workspace.proton-mode atom-panel-container.left atom-panel.left.tool-panel"
                   "body atom-workspace.proton-mode .atomatigit .file-list-view"
                   "body atom-workspace.proton-mode .atomatigit .branch-list-view"
                   "body atom-workspace.proton-mode .atomatigit .atomatigit .commit-list-view"
                   "body atom-workspace.proton-mode"]
        selector (string/join ", " selectors)
        command "proton:chain"
        make-char-fn (comp (partial map char) range)
        letters (make-char-fn 97 123)
        numbers (map str (range 0 10))
        punctuation (flatten (map #(apply make-char-fn %) [[33 48] [58 65] [91 97] [123 127]]))
        special-chars ["escape" "tab" "backspace" "delete" "space"]
        all-chars (into [] (concat [] letters (map string/upper-case letters) numbers special-chars punctuation))
        combo ["ctrl-" "ctrl-alt-" "ctrl-shift-" "ctrl-shift-alt-" "alt-" "alt-shift-" "cmd-" "cmd-shift-" "cmd-alt-" "cmd-alt-shift-" "cmd-ctrl-" "cmd-ctrl-shift-"]
        all-combos (for [x all-chars y combo] (str y x))
        ret (flatten (concat [] all-chars all-combos))
        ret-map (reduce #(assoc %1 %2 command) {} (map identity ret))]
      (atom-env/set-keymap! selector ret-map 100)))

(defn- vim-input? [provider] (some #{provider} [:vim-mode :vim-mode-plus]))

(defn- configure-leader-keys [config-map]
  (let [vim-provider? (vim-input? (config-map "proton.core.inputProvider"))
        current-keys [(config-map "proton.core.leaderKey") (config-map "proton.core.modeKey")]
        vim-keys ["space" ","]
        non-vim-keys ["alt-m" "ctrl-alt-m"]]
      (cond
        (and vim-provider? (= non-vim-keys current-keys)) vim-keys
        (and (not vim-provider?) (= vim-keys current-keys)) non-vim-keys
        :else current-keys)))

(defn- clear-conflicted-keystrokes! [keystrokes]
  (let [reg (re-pattern (str "^(" (join "|" keystrokes) ")"))]
    (set! (.-keyBindings atom-env/keymaps)
      (clj->js
        (filter
          #(not (and (re-find reg (.-keystrokes %)) (not (re-find (re-pattern "^proton:") (.-command %)))))
           (array-seq atom-env/keymaps.keyBindings))))))

(defn init-proton-leader-keys! [configs]
  (let [config-map (into (hash-map) configs)
        vim-provider? (vim-input? (config-map "proton.core.inputProvider"))
        selectors (conj
                    ["body atom-workspace:not(.proton-mode) atom-panel-container.left"
                     "body atom-workspace:not(.proton-mode) atom-panel-container.right"
                     "body atom-workspace:not(.proton-mode) atom-panel-container.bottom"
                     "atom-workspace:not(.proton-mode)"]
                    (if vim-provider?
                       "body atom-workspace:not(.proton-mode) atom-text-editor:not([mini]):not(.insert-mode)"
                       "body atom-workspace:not(.proton-mode) atom-text-editor:not([mini])"))
        selector (string/join ", " selectors)
        leader-command "proton:toggle"
        proton-keys (configure-leader-keys config-map)
        proton-leader-key (proton-keys 0)
        proton-mode-key (proton-keys 1)
        mode-command "proton:toggleMode"
        keymap (hash-map proton-leader-key leader-command proton-mode-key mode-command)]
    (when (not vim-provider?)
      (clear-conflicted-keystrokes! [proton-leader-key proton-mode-key]))
    (atom-env/set-keymap! selector keymap 100)))

(defn show-deprecated-configs [configs]
  (let [config-map (into (hash-map) configs)]
    (when (config-map "proton.core.vim-provider")
      (.addInfo atom-env/notifications "proton-mode: deprecated config <strong>proton.core.vim-provider</strong>"
        (clj->js {:detail "Please use proton.core.inputProvider"
                  :dismissable true})))))
