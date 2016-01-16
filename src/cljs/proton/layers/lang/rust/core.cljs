(ns proton.layers.lang.rust.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-packages describe-mode register-layer-dependencies]]))

(defmethod init-layer! :lang/rust
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/rust)
  (register-layer-dependencies :tools/linter [:linter-rust])
  (register-layer-dependencies :tools/build [:build-cargo]))

(defmethod get-packages :lang/rust []
    [:language-rust
     :racer])

(defmethod describe-mode :lang/rust
 []
 {:mode-name :rust
  :atom-scope ["source.rust"]
  :mode-keybindings
    {:d {:action "racer:find-definition"
         :title "Find definition"}
     :c {:category "cargo"
         :e {:action "build:trigger"
             :title "Execute"}
         :v {:action "build:toggle-panel"
             :title "View results"}
         :s {:action "build:select-active-target"
             :title "Select target"}}}})
