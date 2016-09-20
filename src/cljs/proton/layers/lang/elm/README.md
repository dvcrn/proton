# Elm configuration layer

Adds [Elm](http://elm-lang.org/) support to proton

Includes following atom packages:

-   [language-elm](https://atom.io/packages/language-elm)
-   [elmjutsu](https://atom.io/packages/elmjutsu)
-   [elm-format](https://atom.io/packages/elm-format)
-   [linter-elm-make](https://atom.io/packages/linter-elm-make)

## Install

Add `:lang/elm` to your layers.
For linters support add `:tools/linters`.

## Requirements

-   [Elm](https://guide.elm-lang.org/get_started.html#install)
-   [elm-format](https://github.com/avh4/elm-format#installation-)

## Mode Key Bindings

Key Binding        | Description
-------------------|-----------------
<kbd>SPC g g</kbd> | Go to definition
<kbd>SPC g q</kbd> | Go back from definition
<kbd>SPC g r</kbd> | Show usages
<kbd>SPC g s</kbd> | Go to symbol
<kbd>SPC g n</kbd> | Go to next usage
<kbd>SPC g p</kbd> | Go to previous usage
<kbd>SPC L f</kbd> | Linter quick fix
<kbd>SPC L F</kbd> | Linter quick fix all
<kbd>SPC r s</kbd> | Rename symbol
<kbd>SPC t s</kbd> | Toggle sidekick (Type hint)
