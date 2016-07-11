(ns proton.layers.lang.go.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :refer [console!]]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base :only [init-layer! get-packages register-layer-dependencies init-package get-initial-config]]))

(defmethod get-packages :lang/go []
  [:language-go
   :go-debug
   :go-plus])

(defmethod init-layer! :lang/go []
  (console! "init" :lang/go)
  (mode/define-mode :go-major-mode
    {:atom-scope ["source.go"]
     :atom-grammars ["Go"]}))

(defmethod get-initial-config :lang/go []
  [["go-debug.panelInitialVisible" false]])

(defmethod init-package [:lang/go :go-plus] []
  (mode/define-package-mode :go-plus
    {:mode-keybindings
      {:g {:category "go to"
           :g {:action "golang:godef" :title "definition"}
           :q {:action "golang:godef-return" :title "back from definition"}}
       := {:action "golang:gofmt" :title "format file"}
       :c {:category "check"
           :s {:action "golang:gobuild" :title "check syntax"}
           :v {:action "golang:govet" :title "vet file"}
           :l {:action "golang:golint" :title "lint file"}}
        :t {:category "tests"
            :C {:action "golang:gocover" :title "test coverage"}
            :ctrl-c {:action "golang:cleargocover" :title "clear coverage"}}}})

  (mode/link-modes :go-major-mode (mode/package-mode-name :go-plus)))

(defmethod init-package [:lang/go :go-debug] []
  (mode/define-package-mode :go-debug
    {:mode-keybindings
      {:d {:category "debug"
           :s {:action "go-debug:run" :title "run debug"}
           :c {:action "go-debug:continue" :title "continue"}
           :n {:action "go-debug:next" :title "next"}
           :N {:action "go-debug:step" :title "step"}}}})
  (mode/link-modes :go-major-mode (mode/package-mode-name :go-debug)))


(defmethod init-package [:lang/go :go-get] []
  (mode/define-package-mode :go-get
    {:mode-keybindings
      {:i {:category "imports"
           :u {:action "go-get:get-package" :title "update/fetch package"}}}})
  (mode/link-modes :go-major-mode (mode/package-mode-name :go-get)))

(defmethod init-package [:lang/go :gorename] []
  (mode/define-package-mode :gorename
    {:mode-keybindings
      {:r {:title "go-rename"
           :action "golang:gorename"
           :target actions/get-active-editor}}})
  (mode/link-modes :go-major-mode (mode/package-mode-name :gorename)))
