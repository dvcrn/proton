(ns proton.layers.git.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps]]))

(defmethod init-layer! :git
  [_ config]
  (println "init git"))

(defmethod get-initial-config :git [] [])

(defn get-active-editor [atom]
  (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))

(defmethod get-keybindings :git
  []
  {:g {:category "git"
       :a {:action "git-plus:add"}
       :S {:action "git-plus:status"}
       :s {:action "git-plus:stage-files"}
       :P {:action "git-plus:push"}
       :c {:action "git-plus:commit"}
       :d {:category "git diff"
           :n {:action "git-diff:move-to-next-diff" :target get-active-editor}
           :N {:action "git-diff:move-to-previous-diff" :target get-active-editor}
           :l {:action "git-diff:toggle-diff-list" :target get-active-editor}}}})

(defmethod get-packages :git
  []
  [:git-plus])

(defmethod get-keymaps :git
  []
  [])
