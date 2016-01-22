(ns proton.layers.lang.html.core
  (:require [proton.lib.mode :as mode]
            [proton.lib.helpers :as helpers :refer [console!]]
            [proton.lib.atom :as atom-env]
            [proton.layers.core.actions :as actions])
  (:use [proton.layers.base :only [init-layer! get-packages register-layer-dependencies init-package]]))

(defmethod get-packages :lang/html []
  [:language-less
   ; templates
   :language-html
   :language-slim
   :language-haml
   :atom-handlebars
   :autoclose-html
   :autocomplete-html-entities
   :emmet])

(def style-modes-list
 {:less-major-mode {:atom-scope "source.css.less" :atom-grammars "Less"}})

(def html-like-modes
  {:html-major-mode {:atom-scope "text.html.basic"}
   :handlebars-major-mode {:atom-scope "text.html.handlebars"}
   :mustache-major-mode {:atom-scope "text.html.mustache"}})

(defmethod init-layer! :lang/html []
  (console! "init" :lang/html)
  ; define style modess
  (doall (map #(mode/define-mode (key %) (val %)) (merge style-modes-list html-like-modes)))

  (register-layer-dependencies :tools/linter
    [:linter-less

     :linter-bootlint
     :linter-haml
     :linter-slim
     :linter-xmllint]))

(defmethod init-package [:lang/html :autoclose-html] []
  (let [additionalGrammars (array-seq (atom-env/get-config "autoclose-html.additionalGrammars"))]
    (atom-env/set-config! "autoclose-html.additionalGrammars" (distinct (concat additionalGrammars ["XSL" "XML"])))))

(defmethod init-package [:lang/html :emmet] []
  (mode/define-package-mode :emmet
    {:mode-keybindings
      {:i {:category "image"
           :s {:action "emmet:update-image-size" :target actions/get-active-editor :title "update image size"}
           :e {:action "emmet:encode-decode-data-url" :target actions/get-active-editor :title "encode/decode image"}}
       :e {:action "emmet:next-edit-point" :target actions/get-active-editor :title "next edit point"}
       :tab {:action "emmet:expand-abbreviation" :target actions/get-active-editor :title "expand abbreviation"}
       :space {:action "emmet:interactive-expand-abbreviation" :target actions/get-active-editor :title "interactive expand"}
       (keyword "%") {:action "emmet:matching-pair" :target actions/get-active-editor :title "matching pair"}
       :n {:action "emmet:select-next-item" :target actions/get-active-editor :title "select next item"}
       :N {:action "emmet:select-previous-item" :target actions/get-active-editor :title "select previous item"}
       :j {:action "emmet:split-join-tag" :target actions/get-active-editor :title "split/join tag"}
       :d {:action "emmet:remove-tag" :target actions/get-active-editor :title "delete tag"}
       :c {:action "emmet:update-tag" :target actions/get-active-editor :title "change tag"}
       :v {:action "emmet:balance-outward" :target actions/get-active-editor :title "balance outward"}
       :V {:action "emmet:balance-inward" :target actions/get-active-editor :title "balance inward"}}})
  (doall (map #(mode/link-modes (key %) (mode/package-mode-name :emmet)) html-like-modes)))
