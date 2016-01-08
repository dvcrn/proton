## Elixir configuration layer

This layer adds support for elixir based files with full autocomplete support powered by [autocomplete-elixir](https://atom.io/packages/autocomplete-elixir).

### Requirements
Make sure that you have elixir (and iex) available on your system.

### Install

Add `:lang/elixir` to your layers.

### Configuration

Name                             | Default          | Type       | Description
---------------------------------|------------------|------------|-----------------------------------
`autocomplete-elixir.erlangHome` | "/usr/local/bin" | __string__ | where is your `erl` located at?
`autocomplete-elixir.elixirPath` | "/usr/local/bin" | __string__ | where is your `elixir` located at?

### Key Bindings

| Key Binding          | Description                         |
|----------------------|-------------------------------------|
| <kbd>SPC m d t</kbd> | Toggle docs                         |
| <kbd>SPC m d f</kbd> | Find selection in elixir docs       |
| <kbd>SPC m i l</kbd> | Create iex shell on the right       |
| <kbd>SPC m i h</kbd> | Create iex shell on the left        |
| <kbd>SPC m i k</kbd> | Create iex shell above              |
| <kbd>SPC m i j</kbd> | Create iex shell below              |
| <kbd>SPC m i n</kbd> | Create iex shell in separate window |
