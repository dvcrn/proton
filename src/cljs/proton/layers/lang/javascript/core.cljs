(ns proton.layers.lang.javascript.core
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.package_manager :as package]
            [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-packages describe-mode register-layer-dependencies]]))

(def fallback-linter :eslint)

(defmethod get-initial-config :lang/javascript
  []
  [["proton.lang.javascript.linter" fallback-linter]])

(defn- toggle-linter [linter]
  (let [linter (if (nil? (some #{linter} [:eslint :jshint])) fallback-linter linter)]
    (case linter
     :eslint (do
              (package/enable-package "linter-eslint")
              (package/disable-package "linter-jshint"))
     :jshint (do
              (package/enable-package "linter-jshint")
              (package/disable-package "linter-eslint")))))

(defmethod init-layer! :lang/javascript
  [_ {:keys [config layers]}]
  (let [config-map (into (hash-map) config)]
    (toggle-linter (config-map "proton.lang.javascript.linter")))

  (helpers/console! "init" :lang/javascript)
  (register-layer-dependencies :tools/linter [:linter-eslint :linter-jshint]))

(defmethod get-packages :lang/javascript
  []
  [:atom-ternjs
   :javascript-snippets
   :language-javascript
   :autocomplete-modules
   :docblockr
   ; TODO move this to separate frameworks layer
   :react
   :react-snippets])

(defmethod describe-mode :lang/javascript
  []
  {:mode-name :javascript
   :atom-scope ["source.js"]
   :mode-keybindings
    {:g {:category "go to"
         :g {:action "tern:definition" :target actions/get-active-editor :title "defintion"}
         :q {:action "tern:markerCheckpointBack" :target actions/get-active-editor :title "back from definition"}
         :r {:action "tern:references" :target actions/get-active-editor :title "references"}}
     :s {:category "symbols/show"
         :l {:action "symbols-view:toggle-file-symbols" :target actions/get-active-editor :title "file symbols"}}
     :r {:category "refactor"
         :r {:category "rename"
             :v {:action "tern:rename" :target actions/get-active-editor :title "tern rename variable"}}}
     :L {:category "linters"
          :e {:fx (fn [] (toggle-linter :eslint)) :title "use eslint"}
          :j {:fx (fn [] (toggle-linter :jshint)) :title "use jshint"}}}})
