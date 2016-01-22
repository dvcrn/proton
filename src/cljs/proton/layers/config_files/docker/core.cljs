(ns proton.layers.config-files.docker.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages register-layer-dependencies describe-mode]]))

(defmethod get-packages :config-files/docker []
  [:language-docker
   :dockerletion])

(defmethod init-layer! :config-files/docker []
  (console! "init" :config-files/docker)
  (register-layer-dependencies :tools/linter [:linter-docker]))

(defmethod describe-mode :config-files/docker []
 {:mode-name :docker-major-mode
  :atom-grammars "Dockerfile"
  :atom-scope "source.dockerfile"})
