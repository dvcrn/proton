(ns proton.layers.lang.elixir.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :lang/elixir
  [_ config]
  (println "init elixir"))

(defn- elixir-mode-init []
 (atom-env/set-grammar "Elixir"))

(defmethod get-packages :lang/elixir
  []
  [:language-elixir
   :autocomplete-elixir
   ;;:linter-elixirc
   :elixir-docs
   :iex])

(defmethod describe-mode :lang/elixir []
  {:mode-name :elixir
   :atom-grammars ["Elixir"]
   :mode-keybindings
   {:d {:category "docs"
        :t {:action "elixir-docs:toggle"
            :title "Toggle docs"}
        :f {:action "elixir-docs:finddoc"
            :title "Find in docs"}}
    :i {:category "iex"
        :l {:action "iex:open-split-right"
            :title "create right"}
        :h {:action "iex:open-split-left"
            :title "create left"}
        :k {:action "iex:open-split-up"
            :title "create above"}
        :j {:action "iex:open-split-down"
            :title "create below"}
        :n {:action "iex:open"
            :title "new iex"}}}
   :init elixir-mode-init})

(defmethod get-keymaps :lang/elixir [] [])
(defmethod get-initial-config :lang/elixir [] [])
(defmethod get-keybindings :lang/elixir [] {})
