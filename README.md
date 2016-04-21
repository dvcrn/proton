## Proton

[spacemacs][1] and [sublimious][2] style editing in Atom.

![demo][3]

<!-- MDTOC maxdepth:4 firsth1:1 numbering:0 flatten:0 bullets:1 updateOnSave:1 -->

   - [Proton](#proton)
      - [What is it?](#what-is-it)
      - [Features](#features)
      - [Install](#install)
         - [Pre-warning](#pre-warning)
         - [Going full proton](#going-full-proton)
      - [Compiling](#compiling)
         - [Requirements](#requirements)
         - [Running it](#running-it)
      - [Help](#help)
      - [License](#license)

<!-- /MDTOC -->

### What is it?

Proton *(name subject to change)* brings the modal editing style of spacemacs and sublimious with all it's superpowers to atom.

We get rid of the annoying part of atom - that being the configuration and package management - and __concentrate on the cool bits__ - it's full customisation capabilities.

### Features
##### :handbag: dotfile friendly configuration
proton will take care of setting up atom for you. All you need to do is configure your `~/.proton` file - your central configuration point - and proton will do the rest.

##### :ok_hand: easy to remember keybindings

Noone has time to remember a ton of keybindings and then remember another ton for a new tool. Proton leverages spacemacs mnemonic in which each keybinding is assigned to a specific category:

- <kbd>SPC g s</kbd> will execute [__g__]it [__s__]tatus
- <kbd>SPC p t</kbd> will toggle the [__p__]rojects [__t__]ree-view

you get the idea!

##### :mag: discoverable

No time to read through the docs? No problem. proton is very easily discoverable through the keybindings helper. Just hit <kbd>SPC</kbd> and it will pop up:

![keybinding-helper][5]

##### :battery: Batteries included
Atom has __a ton__ of packages and only a subset of them being actually useful. Instead of finding all the good bits yourself, rely on a crowd-configured layer system that does it for you! Every bit of functionality of proton is encapsulated in layers. Enable what you want and don't care about the rest.

Check out [all available layers here][4].

##### :package: layer based configuration
Imagine you want to do some javascript in atom but you have no idea what to install and what to set up. Instead of just installing random packages by itself, you'll include the `:javascript` layer and boom! proton installs all the good packages and configuration for you.



### Install

There are 2 ways of installing proton: You can compile the latest master (which should be fairly stable) or use the [apm-published](https://atom.io/packages/proton-mode) version.

#### Pre-warning
Proton tries to be your unified configuration system. Please use a fresh atom installation or backup your existing config as proton will very likely __wipe your settings and packages__. Alternatively make sure your `~/.proton` file contains your current configuration or add `["proton.core.wipeUserConfigs" false]` to your `~/.proton`. The template is available for download [here](https://github.com/dvcrn/proton/blob/master/plugin/templates/proton.edn).

#### Going full proton

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
will compile the clojurescript code into javascript. Once that's done, go into the `plugin/` folder and run

```
apm install
apm link
```

proton should now be installed inside Atom under `proton-mode`.

### Help

Join us on the [clojurians slack](http://clojurians.net), channel #proton.

### License

GPLv3

[1]: https://github.com/syl20bnr/spacemacs/
[2]: https://github.com/dvcrn/sublimious
[3]: http://i.imgur.com/UmxjocD.gif
[4]: https://github.com/dvcrn/proton/tree/master/src/cljs/proton/layers
[5]: http://i.imgur.com/npGILXj.png
