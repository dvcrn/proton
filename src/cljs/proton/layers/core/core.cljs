(ns proton.layers.core.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode init-package]])
  (:require [proton.lib.proton :as proton]
            [proton.lib.package_manager :as package]
            [clojure.string :as string :refer [join]]
            [proton.layers.core.keybindings :refer [keybindings]]
            [proton.layers.core.actions :as actions :refer [state]]
            [proton.layers.core.packages :refer [packages]]
            [proton.lib.atom :as atom-env :refer [get-config set-config!]]
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
   ["editor.fontFamily" "Hack"]
   ["theme-switch.profiles" ["atom-material-ui atom-material-syntax"
                             "atom-dark-ui atom-dark-syntax"
                             "atom-light-ui atom-light-syntax"
                             "one-dark-ui one-dark-syntax"
                             "one-light-ui one-light-syntax"]]

   ["autoupdate-packages.handling" "Update automatically and silently"]

   ["zentabs.neverCloseUnsaved" true]
   ["zentabs.maximumOpenedTabs" 10]

   ;; Nuclide things we need
   ["nuclide.use.nuclide-fuzzy-filename-provider" true]
   ["nuclide.use.nuclide-quick-open" true]
   ["nuclide.use.nuclide-open-filenames-provider" true]
   ["nuclide.use.nuclide-recent-files-provider" true]
   ["nuclide.use.nuclide-recent-files-service" true]

   ;; Nuclide things we don't need nor want
   ["nuclide.use.hyperclick" false]
   ["nuclide.use.nuclide-arcanist" false]
   ["nuclide.use.nuclide-blame" false]
   ["nuclide.use.nuclide-blame-provider-hg" false]
   ["nuclide.use.nuclide-blame-ui" false]
   ["nuclide.use.nuclide-buck-files" false]
   ["nuclide.use.nuclide-busy-signal" false]
   ["nuclide.use.nuclide-clang-atom" false]
   ["nuclide.use.nuclide-clipboard-path" false]
   ["nuclide.use.nuclide-code-format" false]
   ["nuclide.use.nuclide-code-highlight" false]
   ["nuclide.use.nuclide-debugger-atom" false]
   ["nuclide.use.nuclide-debugger-hhvm" false]
   ["nuclide.use.nuclide-debugger-lldb" false]
   ["nuclide.use.nuclide-debugger-lldb-client" false]
   ["nuclide.use.nuclide-debugger-lldb-server" false]
   ["nuclide.use.nuclide-debugger-node" false]
   ["nuclide.use.nuclide-diagnostics-store" false]
   ["nuclide.use.nuclide-diagnostics-ui" false]
   ["nuclide.use.nuclide-diff-view" false]
   ["nuclide.use.nuclide-file-tree" false]
   ["nuclide.use.nuclide-file-watcher" false]
   ["nuclide.use.nuclide-find-references" false]
   ["nuclide.use.nuclide-flow" false]
   ["nuclide.use.nuclide-format-js" false]
   ["nuclide.use.nuclide-gadgets" false]
   ["nuclide.use.nuclide-hack" false]
   ["nuclide.use.nuclide-hack-symbol-provider" false]
   ["nuclide.use.nuclide-health" false]
   ["nuclide.use.nuclide-hg-repository" false]
   ["nuclide.use.nuclide-home" false]
   ["nuclide.use.nuclide-language-hack" false]
   ["nuclide.use.nuclide-move-pane" false]
   ["nuclide.use.nuclide-objc" false]
   ["nuclide.use.nuclide-ocaml" false]
   ["nuclide.use.nuclide-react-native-inspector" false]
   ["nuclide.use.nuclide-related-files" false]
   ["nuclide.use.nuclide-remote-ctags" false]
   ["nuclide.use.nuclide-remote-projects" false]
   ["nuclide.use.nuclide-service-monitor" false]
   ["nuclide.use.nuclide-test-runner" false]
   ["nuclide.use.nuclide-toolbar" false]
   ["nuclide.use.nuclide-type-hint" false]
   ["nuclide.use.nuclide-url-hyperclick" false]])

(defmethod init-layer! :core
  [_ {:keys [config layers]}]
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
        ; (swap! packages #(into [] (concat %
        ;                             [:nuclide-quick-open
        ;                              :nuclide-fuzzy-filename-provider
        ;                              :nuclide-open-filenames-provider
        ;                              :nuclide-recent-files-provider
        ;                              :nuclide-recent-files-service])))
        (swap! keybindings assoc-in [:p :b :action] "nuclide-open-filenames-provider:toggle-provider")
        (swap! keybindings assoc-in [:b :b :action] "nuclide-open-filenames-provider:toggle-provider")
        (swap! keybindings assoc-in [:p :f :action] "nuclide-fuzzy-filename-provider:toggle-provider")
        (swap! keybindings assoc-in [:p :r :action] "nuclide-recent-files-provider:toggle-provider")))))

(defmethod init-package [:core :theme-switch] []
  (let [core-themes (string/join " " (atom-env/get-config "core.themes"))
        theme-switch-profiles (array-seq (atom-env/get-config "theme-switch.profiles"))]
    (when (nil? (some #{core-themes} theme-switch-profiles))
      (atom-env/set-config! "theme-switch.profiles" (concat [core-themes] theme-switch-profiles)))))

(defmethod get-keybindings :core [] @keybindings)
(defmethod get-keymaps :core [] @keymaps)
(defmethod get-packages :core [] @packages)
(defmethod describe-mode :core [] {})
