## How to contribute a Layer

Layers are the core *packages* of proton. Each layer is a bundle of sub-packages, keybindings, keymaps and settings. All together that makes one layer that you put on top of your editor.

_(Settings are not supported yet from within a layer)_

### Creating a layer

Creating a layer is very simple. Here is how your base sceleton could look like (don't worry, I'll go into detail in a bit):

```clj
(ns proton.layers.{{your-layer}}.core
  (:use [proton.layers.base :only [get-keybindings get-packages get-keymaps]]))

(defmethod get-keybindings :{{your-layer}}
  []
  {})

(defmethod get-packages :{{your-layer}}
  []
  [])

(defmethod get-keymaps :{{your-layer}}
  []
  [])
```

#### namespace
Proton is written in clojurescript. Let's walk through the code here:

```clj
(ns proton.layers.{{your-layer}}.core
  (:use [proton.layers.base :only [get-keybindings get-packages get-keymaps]]))
```

This is the namespace declaration. In the case of the git layer, this would be `proton.layers.git.core`.

Inside here, we import `get-keybindings`, `get-packages` and `get-keymaps`. These are [multimethods](http://clojure.org/multimethods) that are present in every layer.

#### packages

your layer can depend on any number of packages. To specify what the layer needs to operate, just return a vector with all the packages you want from `get-packages`. In the following example, we say that the git layer depends on the atom package `git-plus`:

```clj
(defmethod get-packages :git
  []
  [:git-plus])
```

Our method declares that it needs no arguments (`[]`) and returns a vector (`[]`).

#### keybindings

for proton to "see" your keybindings, you'll have to implement the multimethod `get-keybindings`. In the case of the git layer, this could look like this:

```clj
(defmethod get-keybindings :git
  []
  {})
```

Keybindings are proton keybindings, not atom keybindings. They will only work inside a proton command chain and not affect anything else from the editor.

A keybinding tree could for example look like this:

```clj
{:g {:category "git"
       :a {:action "git-plus:add"}
       :S {:action "git-plus:status"}
       :s {:action "git-plus:stage-files"}
       :P {:action "git-plus:push"}
       :c {:action "git-plus:commit"}}}
```

What we did here is, we created a new category called git and mapped it under root g which is getting resolved by using `<spc> g`.

Inside `:g` we define a couple of actions. `<spc> g s` for example will dispatch the command `git-plus:stage-files` inside the atom workspace by default.

If your command needs to get executed somewhere else, you can specify a `:target` key:
```clj
:S {:action "git-plus:status"
    :target (fn [atom] (.-body js/document))}
```

`:target` is a function that is getting executed with the atom environment as parameter. Return a DOM node where you want the command to be executed on.

In the example above, `<spc> g S` would now execute `git-plus:status` on `document.body`.

You can also specify `:title` if you want something else than `:action` to show up in protons key-helper.

Alternatively if you want to execute a certain action that doesn't exist as a plugin action, proton also allows you to specify a full function that is getting executed when ever the user executes your keybinding. This would look something like this:

```
:p {:title "print me"
    :fx (fn [] (.log js/console "hello world!"))}
```

Now `<spc> g p` will execute `(.log js/console "hello world")`! Pretty nifty, huh? Since we don't have a action here, you have to specify `:title`, otherwise this will not work. `:fx` currently does not take parameter.


#### keymaps

keymaps and keybindings are very similar but operate differently:
- __keybindings__ are being used inside a proton command chain and only there
- __keymaps__ are atom keymaps that operate in the entire editor

Here's how you can distinguish them:
- Do you want something to happen when the user hits `<spc> x f`? That's a keybinding
- Do you want something to happen when the user hits `j` inside the tree-view? That's a keymap.

Keymaps are being defined as a vector of maps. Each map has  `:selector` which limits where the keymap will be usable, and `:keymap` which defines the actual keybindings:

```clj
(defmethod get-keymaps :git
  []
  [{:selector ".tree-view" :keymap [["escape" "tree-view:toggle"]]}])
```

Here we return a keymap with `:selector` `.tree-view`, meaning that whatever comes inside `:keymap` will only work when the user is inside the `.tree-view` context.

The keymap specifies that once the user hits `escape` inside that `.tree-view`, dispatch `tree-view:toggle`.

_Custom functions and custom targets are not supported yet_.


### Testing your layer

Once you are done, add your layer inside [proton.core](https://github.com/dvcrn/proton/blob/master/src/proton/core.cljs#L9-L12):

```clj
[proton.layers.base :as layerbase]
[proton.layers.core.core :as core-layer]
[proton.layers.git.core :as git-layer]
[proton.layers.clojure.core :as clojure-layer]
```

Now just include it inside your `~/.proton` config file and on the next start proton should load it.
