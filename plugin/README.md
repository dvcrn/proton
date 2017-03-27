## Proton

__this is the binary version of proton. Please submit all issues and pull requests to https://github.com/dvcrn/proton__

[spacemacs][1] and [sublimious][2] style editing in Atom.

![demo][3]

### What is it?

Proton *(name subject to change)* is a very early attempt to mirror the modal editing style of spacemacs and sublimious and bring something equally powerful to atom.

We get rid of the annoying part of atom - that being the configuration and package management - and __concentrate on the cool bits__ - it's full customisation capabilities.

### Features
##### :handbag: dotfile friendly configuration
proton will take care of setting up atom for you. All you need to do is configure your `~/.proton` file - your central configuration point

##### :ok_hand: easy to remember keybindings

Noone has time to remember a ton of keybindings and then remember another ton for a new tool. Proton leverages spacesmacs mnemonic in which each keybinding is assigned to a specific category:

- `<spc> g s` will execute [__g__]it [__s__]tatus
- `<spc> p t` will toggle the [__p__]rojects [__t__]ree-view

you get the idea!

##### :package: layer based configuration
Atom has __a ton__ of packages and only a subset of them being actually useful. Instead of finding all the good bits yourself, rely on a crowd-configured layer system that does it for you! Here is how it works:

Imagine you want to do some javascript in atom but you have no idea what to install and what to set up. Instead of just installing random packages by itself, you'll include the `:javascript` layer and boom! proton installs all the good packages and configuration for you.

Check out [all available layers here][4].


[1]: https://github.com/syl20bnr/spacemacs/
[2]: https://github.com/dvcrn/sublimious
[3]: http://i.imgur.com/UmxjocD.gif
[4]: https://github.com/dvcrn/proton/tree/master/src/cljs/proton/layers
