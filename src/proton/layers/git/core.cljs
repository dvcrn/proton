(ns proton.layers.git.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :git
  [_ config]
  (println "init git"))

(defmethod get-initial-config :git [] [])

(defmethod get-keybindings :git
  []
  {:g {:category "git"
       :a {:action "git-plus:add"}
       :S {:action "git-plus:status"}
       :s {:action "git-plus:stage-files"}
       :P {:action "git-plus:push"}
       :c {:action "git-plus:commit"}}})

(defmethod get-packages :git
  []
  [:git-plus])

(defmethod get-keymaps :git
  []
  [])
