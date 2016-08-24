<!-- MDTOC maxdepth:6 firsth1:1 numbering:0 flatten:0 bullets:1 updateOnSave:1 -->

- [proton manual](#proton-manual)   
   - [.proton](#proton)   
      - [Editor configuration](#editor-configuration)   
      - [Syntax specific configuration](#syntax-specific-configuration)   
      - [Custom SPC keybinding](#custom-spc-keybinding)   
      - [Editor keymaps](#editor-keymaps)   

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

As a limitation you can not specify a `:target` or a `:fx` key that describes where or how your custom command should get executed. If you definitely need this functionality, I'd recommend to create a layer and compile proton with it.

As an example:

```
:keybindings {:z {:category "foo-category"
                  :y {:title "execute hello"
                      :action "hello"}}}
```

This will add a new category that gets triggered by `SPC z` with the title "foo-category" (the title is what is getting displayed inside the key helper). Inside that category is action called "execute hello" that is getting triggered by `y`. So the full sequence would be `SPC z y`.

Because of the limitation mentioned above, proton will _currently_ always execute the command on the current atom view.

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


_More following soon..._
