(ns proton.layers.lang.clojure.core
  (:require [proton.lib.mode :as mode :refer [define-mode]]
            [proton.lib.atom :as atom-env :refer [set-grammar]]
            [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode register-layer-dependencies init-package]]
        [proton.lib.keymap :only [set-proton-keys-for-mode]]))

(defn- clojure-mode-init []
 (atom-env/set-grammar "Clojure"))

(defmethod init-layer! :lang/clojure
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/clojure)
  (register-layer-dependencies :tools/linter [:linter-clojure]))

(defmethod init-package [:lang/clojure :Parinfer] []
  (helpers/console! "init Parinfer package" :lang/clojure)
  (mode/define-package-mode :Parinfer
    {:mode-keybindings
     {:T {:category "toggles"
          :p {:action "parinfer:toggleMode" :title "Toggle Parinfer Mode"}}}})
  (mode/link-modes :clojure-major-mode (mode/package-mode-name :Parinfer)))

(defmethod init-package [:lang/clojure :proto-repl] []
  (helpers/console! "init proto-repl package" :lang/clojure)
  (mode/define-package-mode :proto-repl
    {:mode-keybindings
      {:e {:category "eval"
           :r {:action "proto-repl:execute-selected-text" :title "selected text"}
           :b {:action "proto-repl:execute-block" :title "block"}
           :B {:action "proto-repl:execute-top-block" :title "top block"}}
       :h {:category "help/docs"
           :d {:action "proto-repl:print-var-documentation" :title "var doc"}
           :c {:action "proto-repl:print-var-code" :title "print var code"}
           :s {:action "proto-repl:open-file-containing-var" :title "print var/ns code"}}
       :s {:category "repl"
           :b {:action "proto-repl:load-current-file" :title "load current file"}
           :c {:action "proto-repl:clear-repl" :title "clear"}
           :i {:action "proto-repl:toggle" :title "start"}
           :ctrl-c {:action "proto-repl:interrupt" :title "interrupt"}
           :p {:action "proto-repl:pretty-print" :title "pretty print"}
           :q {:action "proto-repl:exit-repl" :title "quit"}
           :x {:action "proto-repl:refresh-namespaces" :title "refresh"}
           :X {:action "proto-repl:super-refresh-namespaces" :title "super refresh"}}
       :t {:category "tests"
           :a {:action "proto-repl:run-all-tests" :title "run all"}
           :t {:action "proto-repl:run-selected-test" :title "run selected"}
           :c {:action "proto-repl:run-tests-in-namespace" :title "run in ns"}}}})
  (mode/link-modes :clojure-major-mode (mode/package-mode-name :proto-repl)))

(defmethod get-packages :lang/clojure []
  [:Parinfer
   :proto-repl])

(defmethod describe-mode :lang/clojure []
  {:mode-name :clojure-major-mode
   :atom-grammars ["Clojure"]
   :file-extensions [#"proton$"]
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
