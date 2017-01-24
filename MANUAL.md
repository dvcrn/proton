<!-- MDTOC maxdepth:6 firsth1:1 numbering:0 flatten:0 bullets:1 updateOnSave:1 -->

- [proton manual](#proton-manual)   
   - [.proton](#proton)   
      - [Editor configuration](#editor-configuration)   
      - [Configuring proton core itself](#configuring-proton-core-itself)
      - [Syntax specific configuration](#syntax-specific-configuration)   
      - [Custom SPC keybinding](#custom-spc-keybinding)   
      - [Editor keymaps](#editor-keymaps)   
   - [Editor keybindings](#editor-keybindings)   

<!-- /MDTOC -->

# proton manual

This document goes a bit into detail how to use proton and how to deal configure it to your liking. This is not a manual for creating layers.

## .proton

### Editor configuration

Proton tries to be your dominant configuration system and as such wipes all other configurations that are not done through proton. Because of this behavior you have to change atom configuration inside `.proton` rather than inside atom itself.

To add / change a configuration, you have to add it inside `.proton` under the `:configuration` key. The format is a vector in the form of `["namespace.key" "value"]`.

To retrieve the namespace key, the easiest way is to go inside Atom, change the key through the UI, then hit Atom -> config and copy the key. As an example,

```
editor:
  fontFamily: "Hack"
  softWrap: true
```

would result in 2 vectors:

```
["editor.fontFamily" "Hack"]
["editor.softWrap" true]
```

### Configuring proton core itself

There are several settings that allow users to customize proton's behaviour. These are under the `proton.core` namespace and can be seen [README for the core layer](/src/cljs/proton/layers/core/README.md). To set them to values other than the defaults add a row to your `.proton` file as above. For example:

```
;; Prefer to skip the welcome screen if there is nothing important being shown
["proton.core.alwaysShowWelcomeScreen" false]
```

### Syntax specific configuration

proton also supports configuration of `.xxx.source` kind of settings. Due to the leading dot, configuration for this is slightly different than with the previous settings. Use the `:scopeSelector` key inside the third vector element (options) like so:
```
["editor.tabLength" 2 {:scopeSelector [".source.python" ".source.ruby"]}]
```

This would result in the following atom configuration:
```
".source.python":
  editor:
    tabLength: 2
".source.ruby":
  editor:
    tabLength: 2
```

### Custom SPC keybinding

To add a custom keymap to the proton space-menu, you have to use the `:keybindings` key inside `.proton`. The format is the same as inside layers with the exception that you can't define functions inside your `.proton` file. This is because the file is written in edn and proton itself in clojurescript.

As an example:

```
:keybindings {:z {:category "foo-category"
                  :y {:title "execute hello"
                      :action "hello"}}}
```

This will add a new category that gets triggered by `SPC z` with the title "foo-category" (the title is what is getting displayed inside the key helper). Inside that category is action called "execute hello" that is getting triggered by `y`. So the full sequence would be `SPC z y`.

Proton will _currently_, by default, always execute the command on the current atom view. Note that this is not the current editor usually, and you will generally only be able to bind to system wide actions this way.

You can specify a :target key with a CSS selector which will be used to find the target view/DOM element for the action. This is useful to specify that you want the action to run on the current editor (i.e. use '.editor.is-focused').

For example:

```
:keybindings {:p {:category "project"
                  :y {:title "copy project path"
                      :action "editor:copy-project-path",
                      :target ".editor.is-focused"}}}
```

As a limitation you can not specify a `:fx` key to provide a function that describes where or how your custom command should get executed. If you definitely need this functionality, I'd recommend to create a layer and compile proton with it.

### Editor keymaps

`.proton` also supports editor keymaps. These are bindings outside the `SPC` menu and normal shortcuts that are triggered through keypresses or key combinations.

The default example inside `.proton` is to leave the insert mode:

```
{:selector "atom-text-editor.vim-mode-plus.insert-mode" :keymap [["f d" "vim-mode-plus:activate-normal-mode"]]}
```

As you can see, this is a very similar format that atom uses to describe it's keymaps.

- `:selector` is a css selector. The keymap is only available if the selector matches
- `:keymap` are the keys and the action needed to execute the binding. Note that this is a __vector of vectors__. This is because in one selector multiple keybindings can be active.

So in the example above, we add a new keymap that is only available in insert mode and is getting triggered by typing `f` followed by `d`. Executing the keymap will result in vim mode jumping back to normal mode.

## Editor keybindings

Since v0.12.0, proton allows you to switch between a handful of keybindings. By default vim-centric keybindings are used for everything but you can easily change that inside your `~/.proton` with the `proton.core.inputProvider` setting as documented [here](https://github.com/dvcrn/proton/tree/master/src/cljs/proton/layers/core#configuration).

Available modes are:
- `vim-mode`: proton keys are on `SPC`, uses Atoms core [vim-mode](https://github.com/atom/vim-mode)
- `vim-mode-plus` __(default)__: proton keys are on `SPC`, uses vim-mode-plus [vim-mode](https://github.com/t9md/atom-vim-mode-plus)
- `default`: no special keybindings. proton keys are on `ALT-m`, mode keys on `CTR-ALT-m`
- `emacs`: mirrors emacs keybindings through [atomic emacs](https://github.com/avendael/atomic-emacs). proton keys are on `ALT-m`, mode keys on `CTR-ALT-m`. (You could compare this to `holy-mode` in spacemacs, maybe.)


_More following soon..._
