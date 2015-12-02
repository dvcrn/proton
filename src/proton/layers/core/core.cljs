(ns proton.layers.core.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]])
  (:require [proton.lib.proton :as proton]))

(defn get-active-pane [atom]
  (.getView (.-views atom) (.getActivePane (.-workspace atom))))

(defmethod get-initial-config :core []
  [["proton.core.showTabBar" false]])

(defmethod init-layer! :core
  [_ config]
  (let [config-map (into (hash-map) config)]
    ;; hide tab bar if showTabBar is false
    (if (not (config-map "proton.core.showTabBar"))
     (let [tab-bar (aget (.getElementsByClassName js/document "tab-bar") 0)]
        (.setAttribute tab-bar "style" "display:none")))))

(defmethod get-keybindings :core
  []
  { :0 {:action "tree-view:toggle-focus"
        :title "focus tree-view"}
    :j {:action "window:focus-pane-below"
        :target get-active-pane
        :title "focus below pane"}
    :k {:action "window:focus-pane-above"
        :target get-active-pane
        :title "focus above pane"}
    :l {:action "window:focus-pane-on-right"
        :target get-active-pane
        :title "focus right pane"}
    :h {:action "window:focus-pane-on-left"
        :target get-active-pane
        :title "focus left pane"}
    :w {:category "window"
        :d {:action "pane:close"
            :target get-active-pane
            :title "destroy pane"}
        :v {:action "pane:split-right"
            :target get-active-pane
            :title "split vertically"}
        :h {:action "pane:split-down"
            :target get-active-pane
            :title "split horizontally"}
        :V {:action "pane:split-left"
            :target get-active-pane
            :title "split vertically and focus left"}
        :H {:action "pane:split-up"
            :target get-active-pane
            :title "split horizontally and focus up"}}
   :b {:category "buffer"
        :b {:action "fuzzy-finder:toggle-buffer-finder"
            :title "browse buffers"}
        :K {:action "pane:close-other-items"
            :target get-active-pane
            :title "kill other buffers"}
        :d {:action "pane:close"
            :target get-active-pane
            :title "destroy current buffer"}}
   :p {:category "project"
       :t {:action "tree-view:toggle"
           :title "tree-view"}
       :f {:action "fuzzy-finder:toggle-file-finder"
           :title "find in project"}}
   :t {:category "toggles"
       :t {:title "tab-bar"
           :fx (fn []
                 (let [tab-bar (aget (.getElementsByClassName js/document "tab-bar") 0)]
                  (if (nil? (.getAttribute tab-bar "style"))
                    (.setAttribute tab-bar "style" "display:none")
                    (.removeAttribute tab-bar "style"))))}}
    :_ {:category "meta"
        :d {:title "find-dotfile"
            :fx (fn []
                 (.open (.-workspace js/atom) proton/config-path))}
        :R {:title "reload-editor"
            :action "window:reload" :dom-target "body"}
        :c {:title "dev-tools"
            :action "window:toggle-dev-tools"}}})



(defmethod get-keymaps :core
  []
  [{:selector "body" :keymap [["ctrl-j" "core:move-down"]
                              ["ctrl-k" "core:move-up"]]}
   {:selector "atom-text-editor" :keymap [["ctrl-k" "core:move-up"]
                                          ["ctrl-j" "core:move-down"]]}])

(defmethod get-packages :core
  []
  ;; most of these packages are atom defaults
  ;; adding them here so proton doesn't remove them by accident
  [:proton
   :vim-mode
   :ex-mode

   :atom-dark-syntax
   :atom-dark-ui
   :atom-light-syntax
   :atom-light-ui
   :base16-tomorrow-dark-theme
   :base16-tomorrow-light-theme
   :one-dark-ui
   :one-dark-syntax
   :one-light-syntax
   :one-light-ui
   :solarized-dark-syntax
   :solarized-light-syntax
   :about
   :archive-view
   :autocomplete-atom-api
   :autocomplete-css
   :autocomplete-html
   :autocomplete-plus
   :autocomplete-snippets
   :autoflow
   :autosave
   :background-tips
   :bookmarks
   :bracket-matcher
   :command-palette
   :deprecation-cop
   :dev-live-reload
   :encoding-selector
   :exception-reporting
   :find-and-replace
   :fuzzy-finder
   :git-diff
   :go-to-line
   :grammar-selector
   :image-view
   :incompatible-packages
   :keybinding-resolver
   :line-ending-selector
   :link
   :markdown-preview
   :metrics
   :notifications
   :open-on-github
   :package-generator
   :release-notes
   :settings-view
   :snippets
   :spell-check
   :status-bar
   :styleguide
   :symbols-view
   :tabs
   :timecop
   :tree-view
   :update-package-dependencies
   :welcome
   :whitespace
   :wrap-guide
   :language-c
   :language-clojure
   :language-coffee-script
   :language-csharp
   :language-css
   :language-gfm
   :language-git
   :language-go
   :language-html
   :language-hyperlink
   :language-java
   :language-javascript
   :language-json
   :language-less
   :language-make
   :language-mustache
   :language-objective-c
   :language-perl
   :language-php
   :language-property-list
   :language-python
   :language-ruby
   :language-ruby-on-rails
   :language-sass
   :language-shellscript
   :language-source
   :language-sql
   :language-text
   :language-todo
   :language-toml
   :language-xml
   :language-yaml])
