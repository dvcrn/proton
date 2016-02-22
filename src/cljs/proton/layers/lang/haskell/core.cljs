(ns proton.layers.lang.haskell.core
  (:require [proton.lib.helpers :as helpers]
            [proton.layers.core.actions :as actions :refer [get-active-editor]])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-packages describe-mode register-layer-dependencies]]))

(defmethod get-initial-config :lang/haskell []
  [["haskell-ghc-mod.useLinter" true]])

(defmethod init-layer! :lang/haskell
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/haskell)
  (register-layer-dependencies :tools/linter [:linter-hlint]))

(defmethod get-packages :lang/haskell []
 [:autocomplete-haskell
   :haskell-ghc-mod
   :haskell-hoogle
   :ide-haskell
   :ide-haskell-cabal
   :language-haskell])

(defmethod describe-mode :lang/haskell []
  {:mode-name :haskell-major-mode
   :atom-grammars ["Haskell"]
   :atom-scope ["source.hs" "source.lhs"]
   :mode-keybindings
      {:c {:category "compiler"
           :s {:action "ide-haskell-cabal:set-build-target" :title "select target"}
           :b {:action "ide-haskell-cabal:build" :title "build"}
           :c {:action "ide-haskell-cabal:clean" :title "clean"}
           :t {:action "ide-haskell-cabal:test" :title "test"}}
       :h {:category "help/docs"
           :h {:action "haskell-hoogle:lookup" :title "hoogle selection"}
           :t {:action "haskell-ghc-mod:show-type" :title "type" :target get-active-editor}
           :i {:action "haskell-ghc-mod:show-info" :title "info" :target get-active-editor}
           :d {:action "haskell-ghc-mod:go-to-declaration" :title "declaration" :target get-active-editor}}
       :i {:category "insert"
           :t {:action "haskell-ghc-mod:insert-type" :title "type" :target get-active-editor}
           :i {:action "haskell-ghc-mod:insert-import" :title "import" :target get-active-editor}}
       :t {:category "toggles"
           :o {:action "ide-haskell:toggle-output" :title "output panel" :target get-active-editor}
           :c {:action "autocomplete-haskell:toggle-completion-hint" :title "completion hint" :target get-active-editor}}}})
