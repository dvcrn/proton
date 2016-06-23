## Elixir configuration layer

This layer adds support for elixir based files.

Includes following atom packages:

-  [language-elixir](https://atom.io/packages/language-elixir)
-  [atom-elixir](https://atom.io/packages/atom-elixir)
-  [iex](https://atom.io/packages/iex)

### Requirements
Make sure that you have elixir (and iex) available on your system.

### Install

Add `:lang/elixir` to your layers.

### Key Bindings

| Key Binding          | Description                         |
|----------------------|-------------------------------------|
| <kbd>SPC m '</kbd>   | Run iex                             |
| <kbd>SPC m g g</kbd> | Go to definition                    |
| <kbd>SPC m g q</kbd> | Return from definition              |
| <kbd>SPC m h h</kbd> | Find symbol in elixir docs          |
| <kbd>SPC m s l</kbd> | Create iex shell on the right       |
| <kbd>SPC m s h</kbd> | Create iex shell on the left        |
| <kbd>SPC m s k</kbd> | Create iex shell above              |
| <kbd>SPC m s j</kbd> | Create iex shell below              |
| <kbd>SPC m s n</kbd> | Create iex shell in separate window |
| <kbd>SPC m s g</kbd> | iex go to definition                |
| <kbd>SPC m s e</kbd> | iex eval selection                  |
| <kbd>SPC m s t</kbd> | iex run single test                 |
| <kbd>SPC m s T</kbd> | iex run tests                       |
