(ns proton.layers.lang.latex.core
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode]]))

(defmethod get-initial-config :lang/latex []
  ["proton.lang.latex.use-latex-plus" false])

; Define default packages
(def packages
  (atom
    [:latexer
     :language-latex
     :autocomplete-bibtex
     :pdf-view]))

(defmethod init-layer! :lang/latex [_ config]
  (let [config-map (into (hash-map) config)]
    (if (config-map "proton.lang.latex.use-latex-plus")
      (do
        (swap! packages #(into [] (concat % [:latex-plus]))))
      (do
        (swap! packages #(into [] (concat % [:latex])))))
    (println packages)))


(defmethod get-keybindings :lang/latex
  []
  {})

(defmethod get-packages :lang/latex []
  @packages)


(defmethod get-keymaps :lang/latex
  []
  [])

(defmethod describe-mode :lang/latex [] {})
