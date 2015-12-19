(ns proton.layers.lang.latex.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(def layer-state (atom {}))

(defmethod get-initial-config :lang/latex []
  [["proton.latex.latexmk-provider" :latex]])

(defmethod init-layer! :lang/latex
  [_ config]
  (println "init latex")
  (let [config-map (into (hash-map) config)]
    (swap! layer-state assoc :provider (config-map "proton.latex.latexmk-provider"))))

(defmethod get-packages :lang/latex []
  (case (@layer-state :provider)
    :latex [:latexer :language-latex :autocomplete-bibtex :pdf-view :latex]
    :latex-plus [:latexer :language-latex :autocomplete-bibtex :pdf-view :latex-plus]))

(defmethod get-keybindings :lang/latex [] {})
(defmethod get-keymaps :lang/latex [] [])
(defmethod describe-mode :lang/latex [] {})
