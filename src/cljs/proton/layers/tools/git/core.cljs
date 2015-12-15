(ns proton.layers.tools.git.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/git
  [_ config]
  (println "init git"))

(defmethod describe-mode :tools/git [] {})

(defn get-active-editor [atom]
  (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))

(defmethod get-keybindings :tools/git
  []
  {:g {:category "git"
       :a {:action "git-plus:add" :title "add files"}
       :S {:action "git-plus:status" :title "git-plus status"}
       :s {:action "atomatigit:toggle" :title "status" :target get-active-editor}
       :P {:action "git-plus:push" :title "push"}
       :c {:action "git-plus:commit" :title "commit"}
       :d {:category "git diff"
           :n {:action "git-diff:move-to-next-diff" :target get-active-editor :title "next diff"}
           :N {:action "git-diff:move-to-previous-diff" :target get-active-editor :title "previous diff"}
           :l {:action "git-diff:toggle-diff-list" :target get-active-editor :title "list diffs"}}}})

(defmethod get-packages :tools/git
  []
  [:git-plus
   :language-diff
   :atomatigit])

(defmethod get-keymaps :tools/git []
  [{:selector ".atomatigit .file-list-view" :keymap [["s" "atomatigit:stage"]
                                                     ["d" "atomatigit:toggle-diff"]
                                                     ["u" "atomatigit:unstage"]]}])

(defmethod get-initial-config :tools/git [] [])

;; Downwards compatibility. Don't use these.
(defmethod get-packages :git [] (get-packages :tools/git))
(defmethod get-keymaps :git [] (get-keymaps :tools/git))
(defmethod get-keybindings :git [] (get-keybindings :tools/git))
(defmethod get-initial-config :git [] (get-initial-config :tools/git))
(defmethod init-layer! :git [] (init-layer! :tools/git))
(defmethod describe-mode :git [] (describe-mode :tools/git))
