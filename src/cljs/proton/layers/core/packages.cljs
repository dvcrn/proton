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
     
     :recent-files-fuzzy-finder
     :autoupdate-packages

     :hyperclick
     :nuclide-file-watcher

     ;; other
     :advanced-open-file
     :project-manager

     ;; current default theme
     :atom-material-ui
     :atom-material-syntax

     :file-icons]))
