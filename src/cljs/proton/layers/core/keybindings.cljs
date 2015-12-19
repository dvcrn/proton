(ns proton.layers.core.keybindings
  (:require [proton.layers.core.actions :as actions :refer [state]]
            [proton.lib.proton :as proton]))

(def keybindings
  (atom { :0 {:action "tree-view:toggle-focus"
              :title "focus tree-view"}
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
          :tab {:action "tab-switcher:next"
                :title "previous buffer"}
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
                   :action "advanced-open-file:toggle"}}
          :w {:category "window"
              :d {:action "pane:close"
                  :target actions/get-active-pane
                  :title "destroy pane"}
              :v {:action "pane:split-right"
                  :target actions/get-active-pane
                  :title "split vertically"}
              :h {:action "pane:split-down"
                  :target actions/get-active-pane
                  :title "split horizontally"}
              :V {:action "pane:split-left"
                  :target actions/get-active-pane
                  :title "split vertically and focus left"}
              :H {:action "pane:split-up"
                  :target actions/get-active-pane
                  :title "split horizontally and focus up"}}
         :b {:category "buffer"
              :b {:action "fuzzy-finder:toggle-buffer-finder"
                  :title "browse buffers"}
              :K {:action "pane:close-other-items"
                  :target actions/get-active-pane
                  :title "kill other buffers"}
              :d {:action "pane:close"
                  :target actions/get-active-pane
                  :title "destroy current buffer"}}
         :p {:category "project"
             :b {:action "fuzzy-finder:toggle-buffer-finder"
                 :title "browse buffers"}
             :t {:action "tree-view:toggle"
                 :title "tree-view"}
             :f {:action "fuzzy-finder:toggle-file-finder"
                 :title "find in project"}
             :p {:action "project-manager:list-projects"
                 :title "switch projects"}
             :s {:action "project-manager:save-project"
                 :title "save project"}
             :e {:action "project-manager:edit-projects"
                 :title "edit projects"}
             :r {:action "recent-files-fuzzy-finder:toggle-finder"
                 :title "recent files"}}
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
             :g {:title "gutter"
                 :target (fn [atom] (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))
                 :action "editor:toggle-line-numbers"}
             :f {:title "full screen"
                 :action "window:toggle-full-screen"}
             :n {:title "relative numbers"
                 :fx (fn []
                       (if (get @state :relative-numbers)
                        (do
                          (actions/toggle-relative-lines false)
                          (swap! state assoc-in [:relative-numbers] false))
                        (do
                          (actions/toggle-relative-lines true)
                          (swap! state assoc-in [:relative-numbers] true))))}}
          :m {:category "mode"}
          :_ {:category "meta"
              :d {:title "find-dotfile"
                  :fx (fn []
                       (.open (.-workspace js/atom) proton/config-path))}
              :R {:title "reload-editor"
                  :action "window:reload" :dom-target "body"}
              :c {:title "dev-tools"
                  :action "window:toggle-dev-tools"}}}))
