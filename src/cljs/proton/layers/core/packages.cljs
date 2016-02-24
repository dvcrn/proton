(ns proton.layers.core.packages)

(def packages
  (atom
    ;; most of these packages are atom defaults
    ;; adding them here so proton doesn't remove them by accident
    [:proton-mode
     :ex-mode
     :relative-numbers
     :tab-switcher
     :zentabs
     :theme-switch
     :maximize-panes

     :recent-files-fuzzy-finder
     :autoupdate-packages
     :easy-motion-redux
     :environment

     :expand-region

     ;; other
     :advanced-open-file
     :project-manager
     :golden-ratio
     :autocomplete-paths

     ;; current default theme
     :atom-material-ui
     :atom-material-syntax

     :atom-dark-fusion-syntax
     :nucleus-dark-ui

     :file-icons
     :highlight-line
     :highlight-selected
     :lines
     :move-panes]))
