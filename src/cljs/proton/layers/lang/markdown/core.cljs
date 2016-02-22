(ns proton.layers.lang.markdown.core
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.mode :as mode]
            [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-initial-config get-keybindings get-packages get-keymaps describe-mode register-layer-dependencies init-package]]))

(defn markdown-preview-element []
 (first (array-seq (.getElementsByClassName js/document "markdown-preview"))))

(defmethod get-initial-config :lang/markdown
  []
  [])

(defmethod init-layer! :lang/markdown
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/markdown)
  (register-layer-dependencies :tools/linter [:linter-markdown]))

(defmethod get-keybindings :lang/markdown
  []
  {})

(defmethod get-packages :lang/markdown
  []
  [:markdown-writer
   :markdown-preview
   :markdown-scroll-sync])

(defmethod get-keymaps :lang/markdown
  []
  [{:selector "atom-workspace atom-text-editor.autocomplete-active:not([mini])[data-grammar~='gfm']"
    :keymap [["enter" "autocomplete-plus:confirm"]
             ["tab" "autocomplete-plus:confirm"]]}])

(defmethod init-package [:lang/markdown :markdown-writer] []
  (helpers/console! "init markdown-writer package" :lang/markdown)
  (mode/define-package-mode :markdown-writer
    {:mode-keybindings
      {:i {:category "insert"
            :l {:action "markdown-writer:insert-link" :target actions/get-active-editor :title "Link"}
            :i {:action "markdown-writer:insert-image" :target actions/get-active-editor :title "Image"}
            :t {:action "markdown-writer:insert-table" :target actions/get-active-editor :title "Table"}}
       :f {:category "format"
            :t {:action "markdown-writer:format-table" :target actions/get-active-editor :title "Format Table"}
            :o {:action "markdown-writer:correct-order-list-numbers" :target actions/get-active-editor :title "Correct Order List Numbers"}}
       :t {:category "toggle"
           :c {:action "markdown-writer:toggle-code-text" :target actions/get-active-editor :title "Toggle Code Block"}
           :b {:action "markdown-writer:toggle-bold-text" :target actions/get-active-editor}
           :i {:action "markdown-writer:toggle-italic-text" :title "Toggle Italic Text"}
           :s {:action "markdown-writer:toggle-strikethrough-text" :target actions/get-active-editor :title "Toggle Strikethrough text"}
           :k {:action "markdown-writer:toggle-keystroke-text" :target actions/get-active-editor :title "Toggle Keystrokes"}
           :h {:category "heading"
               :1 {:action "markdown-writer:toggle-h1" :target actions/get-active-editor :title "Toggle H1"}
               :2 {:action "markdown-writer:toggle-h2" :target actions/get-active-editor :title "Toggle H2"}
               :3 {:action "markdown-writer:toggle-h3" :target actions/get-active-editor :title "Toggle H3"}
               :4 {:action "markdown-writer:toggle-h4" :target actions/get-active-editor :title "Toggle H4"}
               :5 {:action "markdown-writer:toggle-h5" :target actions/get-active-editor :title "Toggle H5"}}
           :t {:action "markdown-writer:toggle-task" :target actions/get-active-editor :title "Toggle Task"}
           :d {:action "markdown-writer:toggle-taskdone" :target actions/get-active-editor :title "Toggle Task Done"}
           :o {:action "markdown-writer:toggle-ol" :target actions/get-active-editor :title "Toggle Ordered List"}
           :u {:action "markdown-writer:toggle-ul" :target actions/get-active-editor :title "Toggle Unordered List"}
           :q {:action "markdown-writer:toggle-blockquote" :target actions/get-active-editor :title "Toggle Blackquoute"}}}})
  (mode/link-modes :markdown-major-mode (mode/package-mode-name :markdown-writer)))

(defmethod init-package [:lang/markdown :markdown-preview] []
  (helpers/console! "init markdown-preview" :lang/markdown)
  (mode/define-package-mode :markdown-preview
    {:mode-keybindings
      {:p {:category "preview"
            :t {:action "markdown-preview:toggle" :target actions/get-active-editor :title "Toggle Markdown Preview"}
            :i {:action "markdown-preview:zoom-in" :target markdown-preview-element :title "Preview Zoom In"}
            :o {:action "markdown-preview:zoom-out" :target markdown-preview-element :title "Preview Zoom Out"}
            :r {:action "markdown-preview:reset-zoom" :target markdown-preview-element :title "Preview Reset Zoom"}}}})
  (mode/link-modes :markdown-major-mode (mode/package-mode-name :markdown-preview)))

(defmethod describe-mode :lang/markdown
  []
  {:mode-name :markdown-major-mode
   :atom-scope ["source.gfm"]})
