(ns proton.layers.core.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode init-package]])
  (:require [proton.lib.proton :as proton]
            [proton.lib.package_manager :as package]
            [clojure.string :as string :refer [join]]
            [proton.layers.core.keybindings :refer [keybindings]]
            [proton.layers.core.actions :as actions :refer [state]]
            [proton.layers.core.packages :refer [packages]]
            [proton.lib.atom :as atom-env :refer [get-config set-config! set-keymap!]]
            [cljs.nodejs :as node]))

(def keymaps (atom
              [{:selector "body" :keymap [["ctrl-j" "core:move-down"]
                                          ["ctrl-k" "core:move-up"]]}
               {:selector "atom-text-editor" :keymap [["ctrl-k" "core:move-up"]
                                                      ["ctrl-j" "core:move-down"]]}
               {:selector "atom-workspace atom-text-editor.autocomplete-active" :keymap [["ctrl-k" "core:move-up"]
                                                                                         ["ctrl-j" "core:move-down"]]}
               ;; remove tab switcher keybindings
               {:selector "atom-workspace" :keymap [["alt-]" "native!"]
                                                    ["alt-[" "native!"]]}]))

;; Method definitions
(defmethod get-initial-config :core []
  [["proton.core.showTabBar" false]
   ["proton.core.relativeLineNumbers" false]
   ["proton.core.quickOpenProvider" :normal]
   ["proton.core.post-init-timeout" 3000]
   ["proton.core.vim-provider" :vim-mode-plus]
   ["proton.core.wipeUserConfigs" true]

   ;; vim-mode
   ["vim-mode-plus.useSmartcaseForSearch" true]
   ["vim-mode-plus.incrementalSearch" true]
   ["vim-mode-plus.flashOnUndoRedo" true]
   ["vim-mode-plus.useClipboardAsDefaultRegister" true]
   ["vim-mode.useClipboardAsDefaultRegister" true]
   ["vim-mode.useSmartcaseForSearch" true]

   ;; ui
   ["core.themes" ["nucleus-dark-ui" "atom-dark-fusion-syntax"]]

   ["welcome.showOnStartup" false]
   ["editor.softWrap" true]
   ["editor.fontFamily" "Hack"]
   ["theme-switch.profiles" ["nucleus-dark-ui atom-dark-fusion-syntax"
                             "atom-material-ui atom-material-syntax"
                             "atom-dark-ui atom-dark-syntax"
                             "atom-light-ui atom-light-syntax"
                             "one-dark-ui one-dark-syntax"
                             "one-light-ui one-light-syntax"]]

   ["autoupdate-packages.handling" "Update automatically and silently"]

   ["zentabs.neverCloseUnsaved" true]
   ["zentabs.maximumOpenedTabs" 10]
   ["easy-motion-redux.replaceCharacters" "abcdefghijklmnopqrstuvwxyz"]])

(defmethod init-layer! :core
  [_ {:keys [config layers]}]
  (let [config-map (into (hash-map) config)]
    ;; hide tab bar if showTabBar is false
    (actions/toggle-tabs (config-map "proton.core.showTabBar"))
    (actions/toggle-relative-lines (config-map "proton.core.relativeLineNumbers"))

    (swap! state assoc-in [:relative-numbers] (config-map "proton.core.relativeLineNumbers"))
    (swap! state assoc-in [:tabs] (config-map "proton.core.showTabBar"))

    (case (config-map "proton.core.vim-provider")
      :vim-mode (swap! packages #(into [] (concat % [:vim-mode :vim-surround])))
      :vim-mode-plus (swap! packages #(into [] (concat % [:vim-mode-plus :vim-mode-plus-ex-mode]))))))

(defmethod init-package [:core :theme-switch] []
  (let [core-themes (string/join " " (atom-env/get-config "core.themes"))
        theme-switch-profiles (array-seq (atom-env/get-config "theme-switch.profiles"))]
    (when (nil? (some #{core-themes} theme-switch-profiles))
      (atom-env/set-config! "theme-switch.profiles" (concat [core-themes] theme-switch-profiles)))))

(defmethod init-package [:core :vim-mode-plus] []
  (atom-env/set-keymap! "atom-text-editor.vim-mode-plus.normal-mode"
    {"y s" "vim-mode-plus:surround"
     "d s" "vim-mode-plus:delete-surround"
     "c s" "vim-mode-plus:change-surround"
     ":" "vim-mode-plus-ex-mode:open"
     "!" "vim-mode-plus-ex-mode:toggle-setting"})
  (atom-env/set-keymap! "atom-workspace atom-text-editor.vim-mode-plus.visual-mode"
    {"s" "vim-mode-plus:surround"}))

(defmethod get-keybindings :core [] @keybindings)
(defmethod get-keymaps :core [] @keymaps)
(defmethod get-packages :core [] @packages)
(defmethod describe-mode :core [] {})
