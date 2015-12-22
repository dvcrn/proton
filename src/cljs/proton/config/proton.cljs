(ns proton.config.proton
  (:require [proton.lib.helpers :as helpers]))

(def default {:config-path (str helpers/user-home-dir "/.proton")
              :layers [:core]})
