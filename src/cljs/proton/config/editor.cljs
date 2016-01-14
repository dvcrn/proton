(ns proton.config.editor)

(def default {:settings
              [;; Get rid of the startup message
               ["welcome.showOnStartup" false]

               ;; Better default font ;)
               ["editor.fontFamily" "Hack"]]

              :keymaps
               []

              ;; Packages that are part of atom core
              :core-packages
              [:tree-view
               :atom-beautify
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
               :language-yaml]})
