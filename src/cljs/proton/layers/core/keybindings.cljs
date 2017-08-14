(ns proton.layers.core.keybindings
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.pane_manager :as panes]
            [proton.lib.atom :as atom-env]
            [proton.lib.proton :as proton]))

(defn select-pane-fn [n]
  (fn []
    (panes/focus-on-item n)))

(def keybindings
  (atom { :. {:fx #(atom-env/eval-last-action!)
              :title "repeat last command"}
          :0-9 {:title "select pane N"}
          :0 {:fx (select-pane-fn 0)
              :hidden true
              :title "pane 0"}
          :1 {:fx (select-pane-fn 1)
              :hidden true
              :title "pane 1"}
          :2 {:fx (select-pane-fn 2)
              :hidden true
              :title "pane 2"}
          :3 {:fx (select-pane-fn 3)
              :hidden true
              :title "pane 3"}
          :5 {:fx (select-pane-fn 4)
              :hidden true
              :title "pane 4"}
          :6 {:fx (select-pane-fn 6)
              :hidden true
              :title "pane 6"}
          :7 {:fx (select-pane-fn 7)
              :hidden true
              :title "pane 7"}
          :8 {:fx (select-pane-fn 8)
              :hidden true
              :title "pane 8"}
          :9 {:fx (select-pane-fn 9)
              :hidden true
              :title "pane 9"}
          :tab {:action "tab-switcher:next"
                :title "previous buffer"}
          :space {:action "easy-motion-redux:letter"
                  :target actions/get-active-editor
                  :title "easy motion"}
          :j {:action "window:focus-pane-below"
              :target actions/get-active-pane
              :title "focus below pane"}
          :k {:action "window:focus-pane-above"
              :target actions/get-active-pane
              :title "focus above pane"}
          :l {:action "window:focus-pane-on-right"
              :target actions/get-active-pane
              :title "focus right pane"}
          :h {:action "window:focus-pane-on-left"
              :target actions/get-active-pane
              :title "focus left pane"}
          :colon {:action "command-palette:toggle"
                  :title "run command"}
          :f {:category "files"
               :e {:category "editor(atom)"
                   :d {:title "find-dotfile"
                       :fx (fn []
                             (.open (.-workspace js/atom) proton/config-path))}
                   :s {:title "find-stylesheet"
                       :action "application:open-your-stylesheet"}
                   :i {:title "find-init script"
                       :action "application:open-your-init-script"}
                   :p {:title "find-snippets"
                       :action "application:open-your-snippets"}}
               :f {:title "advanced-open-file"
                   :action "advanced-open-file:toggle"}
               :s {:title "save file"
                   :action "core:save"}
               :S {:title "save all files"
                   :action "window:save-all"}
               := {:action "atom-beautify:beautify-editor" :title "format file"}}
          :s {:category "selection"
              :e {:action "find-and-replace:select-all"
                  :target actions/get-active-editor
                  :title "expand selection"}
              :n {:action "find-and-replace:select-next"
                  :target actions/get-active-editor
                  :title "select next"}
              :s {:action "find-and-replace:select-skip"
                  :target actions/get-active-editor
                  :title "skip next"}
              :u {:action "find-and-replace:select-undo"
                  :target actions/get-active-editor
                  :title "undo last select"}
              :l {:category "lines"
                  :h {:action "lines:shuffle"
                      :target (fn [atom] (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))
                      :title "shuffle lines"}
                  :r {:action "lines:reverse"
                      :target (fn [atom] (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))
                      :title "reverse lines"}
                  :s {:action "lines:sort"
                      :target (fn [atom] (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))
                      :title "sort lines"}
                  :u {:action "lines:unique"
                      :target (fn [atom] (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))
                      :title "uniquify lines"}}}
          :w {:category "window"
              :- {:action "pane:split-down"
                  :target actions/get-active-pane
                  :title "split horizontally"}
              :slash {:action "pane:split-right"
                      :target actions/get-active-pane
                      :title "split vertically"}
              :H {:action "move-panes:move-left"
                  :title "move pane left"}
              :J {:action "move-panes:move-down"
                  :title "move pane down"}
              :K {:action "move-panes:move-up"
                  :title "move pane up"}
              :L {:action "move-panes:move-right"
                  :title "move pane right"}
              :V {:action "pane:split-left"
                  :target actions/get-active-pane
                  :title "split vertically and focus left"}
              :S {:action "pane:split-up"
                  :target actions/get-active-pane
                  :title "split horizontally and focus up"}
              :c {:action "pane:close"
                  :title "close pane"}
              :d {:action "pane:close"
                  :target actions/get-active-pane
                  :title "destroy pane"}
              :h {:action "window:focus-pane-on-left"
                  :target actions/get-active-pane
                  :title "focus left pane"}
              :j {:action "window:focus-pane-below"
                  :target actions/get-active-pane
                  :title "focus below pane"}
              :k {:action "window:focus-pane-above"
                  :target actions/get-active-pane
                  :title "focus above pane"}
              :l {:action "window:focus-pane-on-right"
                  :target actions/get-active-pane
                  :title "focus right pane"}
              :m {:action "maximize-panes:maximize"
                  :title "maximize pane"}
              :n {:action "application:new-window"
                  :title "new window"}
              :o {:title "close others"
                  :fx panes/close-other-panes}
              :s {:action "pane:split-down"
                  :target actions/get-active-pane
                  :title "split horizontally"}
              :v {:action "pane:split-right"
                  :target actions/get-active-pane
                  :title "split vertically"}}
          :b {:category "buffer"
              :b {:action "fuzzy-finder:toggle-buffer-finder"
                  :title "browse buffers"}
              :K {:action "tabs:close-other-tabs"
                  :target actions/get-active-pane
                  :title "kill other buffers"}
              :d {:action "core:close"
                  :target actions/get-active-pane
                  :title "destroy current buffer"}
              :n {:action "pane:show-next-item"
                  :title "next buffer"}
              :p {:action "pane:show-previous-item"
                  :title "previous buffer"}
              :u {:action "pane:reopen-closed-item"
                  :title "undo buffer destroy"}}
         :p {:category "project"
             :b {:action "fuzzy-finder:toggle-buffer-finder"
                 :title "browse buffers"}
             :t {:action "tree-view:toggle"
                 :title "tree-view"}
             :f {:action "fuzzy-finder:toggle-file-finder"
                 :title "find in project"}
             :p {:action "project-plus:toggle-project-finder"
                 :title "switch projects"}
             :s {:action "project-plus:save"
                 :title "save project"}
             :c {:action "project-plus:close"
                 :title "close project"}
             :l {:action "project-plus:open"
                 :title "open folder as project"}
             :r {:action "recent-files-fuzzy-finder:toggle-finder"
                 :title "recent files"}
             :slash {:action "project-find:show"
                     :title "search in files"}}
         :t {:category "toggles"
             :t {:title "tab-bar"
                 :fx (fn []
                       (if (get @state :tabs)
                        (do
                          (actions/toggle-tabs false)
                          (swap! state assoc-in [:tabs] false))
                        (do
                          (actions/toggle-tabs true)
                          (swap! state assoc-in [:tabs] true))))}
             :n {:title "line numbers"
                 :target (fn [atom] (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))
                 :action "editor:toggle-line-numbers"}
             :h {:category "highlight"
                 :l {:title "highlight current line"
                     :action "highlight-line:toggle-background"}}
             :i {:action "editor:toggle-indent-guide"
                 :target actions/get-active-editor
                 :title "indent guide"}
             :I {:action "window:toggle-auto-indent"
                 :title "auto indent"}
             :s {:title "status bar"
                 :action "status-bar:toggle"}
             :S {:title "spell checking"
                 :action "spell-check:toggle"}
             :g {:title "golden ratio"
                 :action "golden-ratio:toggle"}
             :r {:title "relative numbers"
                 :fx (fn []
                       (if (get @state :relative-numbers)
                        (do
                          (actions/toggle-relative-lines false)
                          (swap! state assoc-in [:relative-numbers] false))
                        (do
                          (actions/toggle-relative-lines true)
                          (swap! state assoc-in [:relative-numbers] true))))}
              :w {:title "whitespace"
                  :fx (fn [] (atom-env/set-config! "editor.showInvisibles" (not (atom-env/get-config "editor.showInvisibles"))))}}
          :T {:category "UI toggles/themes"
              :F {:action "window:toggle-full-screen" :title "toggle full screen"}
              :M {:fx actions/toggle-maximize :title "toggle maximize"}
              :n {:action "theme-switch:next" :title "cycle themes"}
              :m {:action "window:toggle-menu-bar" :title "toggle menu bar"}}
          :m {:category "mode"}
          :semicolon {:action "editor:toggle-line-comments"
                      :target actions/get-active-editor
                      :title "comment lines"}
          :v {:action "expand-region:expand"
              :target actions/get-active-editor
              :title "expand region"}
          :_ {:category "meta"
              :d {:title "find-dotfile"
                  :fx (fn []
                       (.open (.-workspace js/atom) proton/config-path))}
              :R {:title "reload-editor"
                  :action "window:reload" :dom-target "body"}
              :c {:title "dev-tools"
                  :action "window:toggle-dev-tools"}
              :r {:title "reload-proton"
                  :fx (fn [] (atom-env/force-reload-package "proton-mode"))}}}))
