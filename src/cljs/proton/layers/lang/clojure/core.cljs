(ns proton.layers.lang.clojure.core
  (:require [proton.lib.mode :as mode :refer [define-mode define-keybindings]]
            [proton.lib.atom :as atom-env :refer [set-grammar]])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(def packages (atom [:Parinfer]))

(defn- clojure-mode-init []
 (atom-env/set-grammar "Clojure"))

(defmethod init-layer! :lang/clojure
  [_ {:keys [config layers]}]
  (println "init clojure")
  (if (contains? (set layers) :tools/linter)
    (swap! packages conj :linter-clojure)))

(defmethod get-packages :lang/clojure [] @packages)

(defmethod describe-mode :lang/clojure []
  {:mode-name :clojure
   :atom-grammars ["Clojure"]
   :file-extensions [#"\.proton$"]
   :mode-keybindings
   {:t {:category "toggles"
        :p {:action "parinfer:toggleMode" :title "Toggle Parinfer Mode"}}}
   :init clojure-mode-init})


(defmethod get-keymaps :lang/clojure [] [])
(defmethod get-initial-config :lang/clojure [] [])
(defmethod get-keybindings :lang/clojure [] {})

;; Downwards compatibility. Don't use these.
(defmethod get-packages :clojure [] (get-packages :lang/clojure))
(defmethod get-keymaps :clojure [] (get-keymaps :lang/clojure))
(defmethod get-keybindings :clojure [] (get-keybindings :lang/clojure))
(defmethod get-initial-config :clojure [] (get-initial-config :lang/clojure))
(defmethod init-layer! :clojure [] (init-layer! :lang/clojure))
