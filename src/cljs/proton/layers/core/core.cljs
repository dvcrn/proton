(ns proton.layers.core.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]])
  (:require [proton.lib.proton :as proton]
            [proton.lib.package_manager :as package]
            [proton.layers.core.keybindings :refer [keybindings]]
            [proton.layers.core.actions :as actions :refer [state]]
            [proton.layers.core.packages :refer [packages]]
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
   ["proton.core.vim-provider" :vim-mode-plus]
   ;; vim-mode
   ["vim-mode-plus.useSmartcaseForSearch" true]
   ["vim-mode-plus.incrementalSearch" true]
   ["vim-mode-plus.flashOnUndoRedo" true]
   ["vim-mode-plus.useClipboardAsDefaultRegister" true]
   ["vim-mode.useClipboardAsDefaultRegister" true]
   ["vim-mode.useSmartcaseForSearch" true]

   ;; ui
   ["core.themes" ["atom-material-ui" "atom-material-syntax"]]
   ["atom-material-ui.ui.accentColor" "Cyan"]
   ["atom-material-ui.tabs.tabSize" "Small"]
   ["atom-material-ui.tabs.rippleAccentColor" true]
   ["atom-material-ui.tabs.showTabIcons" "Show on active tab"]
   ["atom-material-ui.treeView.compactTreeView" true]
   ["atom-material-ui.panels.panelContrast" true]

   ["editor.softWrap" true]
   ["editor.fontFamily" "Hack"]])

(defmethod init-layer! :core
  [_ config]
  (let [config-map (into (hash-map) config)]
    ;; hide tab bar if showTabBar is false
    (actions/toggle-tabs (config-map "proton.core.showTabBar"))
    (actions/toggle-relative-lines (config-map "proton.core.relativeLineNumbers"))

    (swap! state assoc-in [:relative-numbers] (config-map "proton.core.relativeLineNumbers"))
    (swap! state assoc-in [:tabs] (config-map "proton.core.showTabBar"))

    (case (config-map "proton.core.vim-provider")
      :vim-mode (swap! packages #(into [] (concat % [:vim-mode])))
      :vim-mode-plus (swap! packages #(into [] (concat % [:vim-mode-plus]))))

    (if (= (config-map "proton.core.quickOpenProvider") :nuclide)
      (do
        (swap! packages #(into [] (concat %
                                    [:nuclide-quick-open
                                     :nuclide-fuzzy-filename-provider
                                     :nuclide-open-filenames-provider
                                     :nuclide-recent-files-provider
                                     :nuclide-recent-files-service])))
        (swap! keybindings assoc-in [:p :b :action] "nuclide-open-filenames-provider:toggle-provider")
        (swap! keybindings assoc-in [:b :b :action] "nuclide-open-filenames-provider:toggle-provider")
        (swap! keybindings assoc-in [:p :f :action] "nuclide-fuzzy-filename-provider:toggle-provider")
        (swap! keybindings assoc-in [:p :r :action] "nuclide-recent-files-provider:toggle-provider")))))

(defmethod get-keybindings :core [] @keybindings)
(defmethod get-keymaps :core [] @keymaps)
(defmethod get-packages :core [] @packages)
(defmethod describe-mode :core [] {})
