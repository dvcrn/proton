(ns proton.layers.lang.javascript.core
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.package_manager :as package]
            [proton.lib.helpers :as helpers]
            [proton.lib.mode :as mode]
            [proton.lib.keymap :as keymap])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-packages describe-mode register-layer-dependencies init-package]]))

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
  (register-layer-dependencies :tools/linter [:linter-eslint :linter-jshint :fixmyjs]))

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

(defmethod init-package [:lang/javascript :linter] []
  (helpers/console! "init linter package" :lang/javascript)
  (keymap/set-proton-keys-for-mode :javascript-major-mode
    {:L {:category "linters"
         :e {:fx (fn [] (toggle-linter :eslint)) :title "use eslint"}
         :j {:fx (fn [] (toggle-linter :jshint)) :title "use jshint"}}}))

(defmethod init-package [:lang/javascript :linter-jshint] []
  (helpers/console! "init linter-jshint package" :lang/javascript)
  (mode/define-package-mode :linter-jshint
    {:mode-keybindings
      {:L {:f {:action "FixMyJS" :title "fix jshint errors"}}}})
  (mode/link-modes :javascript-major-mode (mode/package-mode-name :linter-jshint)))

(defmethod init-package [:lang/javascript :linter-eslint] []
  (helpers/console! "init linter-eslint package" :lang/javascript)
  (mode/define-package-mode :linter-eslint
    {:mode-keybindings
      {:L {:f {:action "linter-eslint:fix-file" :target actions/get-active-editor :title "fix eslint errors"}}}})
  (mode/link-modes :javascript-major-mode (mode/package-mode-name :linter-eslint)))

(defmethod init-package [:lang/javascript :atom-ternjs] []
  (helpers/console! "init atom-ternjs package" :lang/javascript)
  (mode/define-package-mode :atom-ternjs
    {:mode-keybindings
      {:g {:category "go to"
           :g {:action "tern:definition" :target actions/get-active-editor :title "defintion"}
           :q {:action "tern:markerCheckpointBack" :target actions/get-active-editor :title "back from definition"}
           :r {:action "tern:references" :target actions/get-active-editor :title "references"}}
       :r {:category "refactor"
           :r {:category "rename"
               :v {:action "tern:rename" :target actions/get-active-editor :title "tern rename variable"}}}}})
  (mode/link-modes :javascript-major-mode (mode/package-mode-name :atom-ternjs)))

(defmethod describe-mode :lang/javascript
  []
  {:mode-name :javascript-major-mode
   :atom-scope ["source.js"]
   :mode-keybindings
   {:s {:category "symbols/show"
        :l {:action "symbols-view:toggle-file-symbols" :target actions/get-active-editor :title "file symbols"}}}})
