(ns proton.layers.lang.json.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]])
  (:use [proton.layers.base :only [init-layer! get-packages describe-mode init-package register-layer-dependencies]]))

(defmethod get-packages :lang/json []
  [:json-path-finder])

(defmethod init-layer! :lang/json []
  (console! "init" :lang/json)
  (register-layer-dependencies :tools/linter [:linter-jsonlint]))

(defmethod describe-mode :lang/json []
  {:mode-name :json-major-mode
   :atom-grammars "JSON"
   :atom-scope "source.json"})

(defmethod init-package [:lang/json :json-path-finder] []
  (mode/define-package-mode :json-path-finder
    {:mode-keybindings
      {:f {:action "json-path-finder:find" :title "find path"}
       :F {:action "json-path-finder:find-flatten" :title "find path flatten"}}})
  (mode/link-modes :json-major-mode (mode/package-mode-name :json-path-finder)))
