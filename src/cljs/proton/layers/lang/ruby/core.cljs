(ns proton.layers.lang.ruby.core
  (:require [proton.lib.helpers :as helpers])
  (:use [proton.layers.base :only [init-layer! get-packages describe-mode register-layer-dependencies]]))

(defmethod init-layer! :lang/ruby
  [_ {:keys [config layers]}]
  (helpers/console! "init" :lang/ruby)
  (register-layer-dependencies :tools/linter [:linter-ruby]))

(defmethod get-packages :lang/ruby []
    [:ruby-test
     :ruby-block
     :ruby-block-converter
     :autocomplete-ruby])

(defmethod describe-mode :lang/ruby
 []
 {:mode-name :ruby
  :atom-scope ["source.ruby" "source.ruby.rails.rjs" "source.ruby.rails" "source.erb"]
  :mode-keybindings
    {:c {:category "convert block"
         :d {:action "ruby-block-converter:to-curly-brackets"
             :title "do..end -> {}"
             :target "atom-text-editor:not([mini])"}
         :D {:action "ruby-block-converter:to-curly-brackets-without-collapse"
             :title "do..end -> {} (no-collapse)"
             :target "atom-text-editor:not([mini])"}
         :b {:action "ruby-block-converter:to-do-end"
             :title "{} -> do..end"
             :target "atom-text-editor:not([mini])"}
         :B {:action "ruby-block-converter:to-do-end-without-join"
             :title "{} -> do..end (no-join)"
             :target "atom-text-editor:not([mini])"}}
     :t {:category "test"
         :t {:action "ruby-test:toggle"
             :title "toggle panel"}
         :a {:action "ruby-test:test-all"
             :title "test all"}
         :f {:action "ruby-test:test-file"
             :title "test file"}
         :l {:action "ruby-test:test-single"
             :title "test at line"}
         (keyword ".") {:action "ruby-test:test-previous"
                        :title "re-run previous test"}
         :c {:action "ruby-test:cancel"
             :title "cancel tests"}}}})
