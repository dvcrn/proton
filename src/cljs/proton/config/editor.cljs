(ns proton.config.editor)

(def default {:settings
              [;; Get rid of the startup message
               ["welcome.showOnStartup" false]

               ;; Better default font ;)
               ["editor.fontFamily" "Hack"]]

              :keymaps
               []

              :ensure-disabled
              []})
