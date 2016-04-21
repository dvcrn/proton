## Proton

[spacemacs][1] and [sublimious][2] style editing in Atom.

![demo][3]

<!-- MDTOC maxdepth:4 firsth1:1 numbering:0 flatten:0 bullets:1 updateOnSave:1 -->

   - [Proton](#proton)
      - [What is it?](#what-is-it)
      - [Features](#features)
      - [Install](#install)
         - [Pre-warning](#pre-warning)
         - [Going full Proton](#going-full-proton)
      - [Compiling](#compiling)
         - [Requirements](#requirements)
         - [Running it](#running-it)
      - [Help](#help)
      - [License](#license)

<!-- /MDTOC -->

### What is it?

Proton *(name subject to change)* brings the modal editing style of Spacemacs and Sublimious with all its superpowers to Atom.

We get rid of the annoying part of Atom — that being the configuration and package management — and __concentrate on the cool bits__: its full customisation capabilities.

### Features
##### :handbag: dotfile friendly configuration
Proton will take care of setting up Atom for you. All you need to do is configure your `~/.proton` file — your central configuration point — and Proton will do the rest.

##### :ok_hand: easy to remember keybindings

No one has time to remember a ton of keybindings and then learn another ton for a new tool. Proton leverages Spacemacs mnemonics in which each keybinding is assigned to a specific category:

- <kbd>SPC g s</kbd> will execute [__g__]it [__s__]tatus
- <kbd>SPC p t</kbd> will toggle the [__p__]rojects [__t__]ree-view

You get the idea!

##### :mag: discoverable

No time to read through the docs? No problem. Proton is very easily discoverable through the keybindings helper. Just hit <kbd>SPC</kbd> and it will pop up:

![keybinding-helper][5]

##### :battery: batteries included
Atom has __a ton__ of packages but only a subset of them are actually useful. Instead of finding all the good bits yourself, rely on a crowd-configured layer system that does it for you! Every bit of functionality of Proton is encapsulated in layers. Enable what you want and don't care about the rest.

Check out [all available layers here][4].

##### :package: layer based configuration
Imagine you want to do some JavaScript in Atom but you have no idea what to install and what to set up. Instead of just installing random packages by yourself, you include the `:javascript` layer and boom! Proton installs all the good packages and configuration for you.



### Install

There are 2 ways of installing Proton: You can compile the latest master (which should be fairly stable) or use the [apm-published](https://atom.io/packages/proton-mode) version.

#### Pre-warning
Proton tries to be your unified configuration system. Please use a fresh Atom installation or backup your existing config as Proton will very likely __wipe your settings and packages__. Alternatively make sure your `~/.proton` file contains your current configuration or add `["proton.core.wipeUserConfigs" false]` to your `~/.proton`. The template is available for download [here](https://github.com/dvcrn/proton/blob/master/plugin/templates/proton.edn).

#### Going full Proton

```
apm install proton-mode
```

or through the package manager here: https://atom.io/packages/proton-mode

### Compiling

#### Requirements
- atom / apm
- [leiningen](http://leiningen.org/)

#### Running it

```
lein run -m build/release

# or if you want auto-compile on change:
lein run -m build/dev-repl
```
will compile the ClojureScript code into JavaScript. Once that's done, go into the `plugin/` folder and run

```
apm install
apm link
```

Proton should now be installed inside Atom under `proton-mode`.

### Help

Join us on the [clojurians slack](http://clojurians.net), channel #proton.

### License

GPLv3

[1]: https://github.com/syl20bnr/spacemacs/
[2]: https://github.com/dvcrn/sublimious
[3]: http://i.imgur.com/UmxjocD.gif
[4]: https://github.com/dvcrn/proton/tree/master/src/cljs/proton/layers
[5]: http://i.imgur.com/npGILXj.png
