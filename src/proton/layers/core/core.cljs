(ns proton.layers.core.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]])
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
   ["vim-mode.useClipboardAsDefaultRegister" true]
   ["vim-mode.useSmartcaseForSearch" true]
   ["core.themes" ["atom-material-ui" "atom-material-syntax"]]
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
