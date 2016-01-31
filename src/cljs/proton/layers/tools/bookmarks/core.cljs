(ns proton.layers.tools.bookmarks.core
  (:require [proton.lib.helpers :as helpers]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base
         :only [init-layer! get-initial-config get-keybindings
                get-packages get-keymaps describe-mode]]))

(defmethod init-layer! :tools/bookmarks
  [_ {:keys [config layers]}]
  (helpers/console! "init" :tools/bookmarks))

(defmethod get-keybindings :tools/bookmarks
  []
  {:B {:category "bookmarks"
       :b {:title "toggle bookmark"
           :action "bookmarks:toggle-bookmark"
           :target actions/get-active-editor}
       :n {:title "next bookmark"
           :action "bookmarks:jump-to-next-bookmark"
           :target actions/get-active-editor}
       :p {:title "previous bookmark"
           :action "bookmarks:jump-to-previous-bookmark"
           :target actions/get-active-editor}
       :a {:title "view all"
           :action "bookmarks:view-all"}
       :c {:title "clear all"
           :action "bookmarks:clear-bookmarks"
           :target actions/get-active-editor}}})

(defmethod get-packages :tools/bookmarks
  []
  [:bookmarks])


(defmethod get-keymaps :tools/bookmarks [] [])
(defmethod get-initial-config :tools/bookmarks [] [])
(defmethod describe-mode :tools/bookmarks [] {})
