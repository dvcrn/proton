(ns proton.layers.lang.elixir.core
  (:require [proton.lib.helpers :as helpers]
            [proton.lib.mode :as mode :refer [define-mode]]
            [proton.lib.atom :as atom-env :refer [set-grammar]])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode register-layer-dependencies init-package]]))

(defmethod init-layer! :lang/elixir
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/elixir)
  (register-layer-dependencies :tools/linter [:linter-elixirc]))

(defn- elixir-mode-init []
 (atom-env/set-grammar "Elixir"))

(defmethod get-initial-config :lang/elixir [] [])

(defmethod get-packages :lang/elixir []
  [:language-elixir
   :atom-elixir
   :iex])

(defmethod init-package [:lang/elixir :atom-elixir] []
  (helpers/console! "init atom-elixir package" :lang/elixir)
  (mode/define-package-mode :atom-elixir
    {:mode-keybindings
      {:g {:category "go to"
           :g {:action "atom-elixir:goto-definition"
               :target "atom-text-editor:not(mini)"
               :title "definition"}
           :q {:action "atom-elixir:return-from-definition"
               :target "atom-text-editor:not(mini)"
               :title "return from definition"}}
       :h {:category "help"
           :h {:action "atom-elixir:show-elixir-docs"
               :target "atom-text-editor:not(mini)"
               :title "search at point"}}
       :e {:category "eval/expand"
           :m {:action "atom-elixir:expand-selected-text"
               :target "atom-text-editor:not(mini)"
               :title "expand macro"}
           :q {:action "atom-elixir:quote-selected-text"
               :target "atom-text-editor:not(mini)"
               :title "quoted view"}}}})
  (mode/link-modes :elixir-major-mode (mode/package-mode-name :atom-elixir)))

(defmethod init-package [:lang/elixir :iex] []
  (helpers/console! "init package iex" :lang/elixir)
  (mode/define-package-mode :iex
   {:mode-keybindings
     {:s {:category "iex"
          :l {:action "iex:open-split-right"
              :title "create right"}
          :h {:action "iex:open-split-left"
              :title "create left"}
          :k {:action "iex:open-split-up"
              :title "create above"}
          :j {:action "iex:open-split-down"
              :title "create below"}
          :n {:action "iex:open"
              :title "new iex"}
          :e {:action "iex:pipe"
              :target "atom-text-editor:not(mini)"
              :title "eval selection"}
          :g {:action "iex:gotoDefinition"
              :target "atom-text-editor"
              :title "go to definition"}
          :t {:action "iex:run-test"
              :target "atom-text-editor"
              :title "run test"}
          :T {:action "iex:run-tests"
              :target "atom-text-editor"
              :title "run tests"}}
      :quote {:action "iex:open-split-down"
              :title "run iex"}}})
  (mode/link-modes :elixir-major-mode (mode/package-mode-name :iex)))

(defmethod describe-mode :lang/elixir []
  {:mode-name :elixir-major-mode
   :atom-grammars ["Elixir"]
   :init elixir-mode-init})

(defmethod get-keymaps :lang/elixir []
  [{:selector "body atom-workspace .iex .terminal" :keymap [["space" "native!"]]}])

(defmethod get-keybindings :lang/elixir [] {})
