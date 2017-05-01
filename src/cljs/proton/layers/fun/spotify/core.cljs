(ns proton.layers.spotify.core
  (:use [proton.layers.base :only [init-layer! get-initial-config et-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :fun/spotify
  [_ {:keys [config layers]}])

(defmethod get-keybindings :fun/spotify
  []
  {:a {:category "applications"
        :m {:category "music"
            :s {:category "spotify"
                :p {:action "spotify-remote:toggle"
                    :title "play/pause"}
                :n {:action "spotify-remote:next"
                    :title "Plat next song"}
                :b {:action "spotify-remote:previous"
                    :title "Play previous song"}}}}})

(defmethod get-packages :fun/spotify
  []
  [:spotify-remote])

(defmethod get-keymaps :fun/spotify [] [])
(defmethod get-initial-config :fun/spotify [] [])
(defmethod describe-mode :fun/spotify [] {})
