(ns proton.config.proton
  (:require [cljs.nodejs :as node]))

(def path (node/require "path"))
(def user-home-dir (.normalize path (if (= (.-platform js/process) "win32") (.. js/process -env -USERPROFILE) (.. js/process -env -HOME))))

(def default {:config-path (str user-home-dir "/.proton")
              :layers [:core]
              :debug false})
