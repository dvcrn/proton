(ns proton.layers.tools.pomodoro.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod get-initial-config :tools/pomodoro
  []
  ["pomodoro.playSounds" false])

(defmethod init-layer! :tools/pomodoro
  [_ config]
  (println "init pomodoro"))

(defmethod get-keybindings :tools/pomodoro
  []
  {:t {:category "toggles"
       :p {:category "pomodoro"
           :s {:action "pomodoro:start"
               :title "Start Timer"}
           :a {:action "pomodoro:abort"
               :title "Abort Timer"}}}})

(defmethod get-packages :tools/pomodoro
  []
  [:pomodoro])

(defmethod get-keymaps :tools/pomodoro
  []
  [])
