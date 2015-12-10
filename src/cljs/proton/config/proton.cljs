(ns proton.config.proton)

(def default {:config-path (str (.. js/process -env -HOME) "/.proton")
              :layers [:core]})
