## Haskell configuration layer

This layer adds support for Haskell based file editing.

Includes following packages:

- [autocomplete-haskell](https://github.com/atom-haskell/autocomplete-haskell)
- [haskell-ghc-mod](https://github.com/atom-haskell/haskell-ghc-mod)
- [haskell-hoogle](https://github.com/kaeluka/atom-haskell-hoogle)
- [ide-haskell](https://github.com/atom-haskell/ide-haskell)
- [ide-haskell-cabal](https://github.com/atom-haskell/ide-haskell-cabal)
- [language-haskell](https://github.com/atom-haskell/language-haskell)

First time Haskell users, checkout [Haskell Platform](https://www.haskell.org/platform/)
and [Haskell Stack](http://haskellstack.org/) to get started with Haskell quickly.

### Install

Add `:lang/haskell` to your layers.

You can use cabal to install the following required dependencies:
- [ghc-mod](https://hackage.haskell.org/package/ghc-mod)
- [hoogle](https://hackage.haskell.org/package/hoogle)
- [hspec](https://hackage.haskell.org/package/hspec)
- [QuickCheck](https://hackage.haskell.org/package/QuickCheck)

Also, make sure your cabal binary directory is in your path (e.g. ~/.cabal/bin).

### Setup

To enable linting, make sure add `:tools/linter` to your layers.

Also, when you open your first Haskell file two panels will open at
the bottom of your screen: autocomplete-haskell's completion hint panel and
ide-haskell's output panel. See the below keybindings to toggle them; just
remember that when you use ide-haskell's cabal build commands, the output will
be in the ide-haskell panel and you must reopen it to see it.

### Key Bindings

| Key Binding          | Description                  |
|----------------------|------------------------------|
| <kbd>SPC f =</kbd>   | beautify file                |
| <kbd>SPC e t</kbd>   | toggle linter output         |
| <kbd>SPC e n</kbd>   | next error                   |
| <kbd>SPC e p</kbd>   | previous error               |
| <kbd>SPC m t o</kbd> | toggle ide-haskell panel     |
| <kbd>SPC m h c</kbd> | toggle completion hint panel |
| <kbd>SPC m i t</kbd> | insert type                  |
| <kbd>SPC m i i</kbd> | insert import                |
| <kbd>SPC m h t</kbd> | show type                    |
| <kbd>SPC m h i</kbd> | show info                    |
| <kbd>SPC m h d</kbd> | go to declaration            |
| <kbd>SPC m c s</kbd> | select target                |
| <kbd>SPC m c b</kbd> | build target                 |
| <kbd>SPC m c c</kbd> | clean target                 |
| <kbd>SPC m c t</kbd> | run target's tests           |
