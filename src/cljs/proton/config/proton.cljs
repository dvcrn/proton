(ns proton.config.proton
  (:require [cljs.nodejs :as node]))

(def path (node/require "path"))
(def user-home-dir (.homedir (node/require "os")))

(def default {:config-path (.join path user-home-dir ".proton")
              :layers [:core]
              :debug false})
