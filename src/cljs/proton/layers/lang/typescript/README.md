## Typescript configuration layer

Adds support for Typescript, includes:

- Many things provided by [`atom-typescript`](https://github.com/TypeStrong/atom-typescript)
- Linting provided by [`linter-tslint`](https://github.com/AtomLinter/linter-tslint)

### Install

Add `:lang/typescript` to your layers.

### Mode keybindings

| Key Binding          | Description             |
|----------------------|-------------------------|
| <kbd>SPC m g g</kbd> | go to declaration       |
| <kbd>SPC m g q</kbd> | return from declaration |
| <kbd>SPC m g u</kbd> | find references         |
| <kbd>SPC m e f</kbd> | quick fix               |
| <kbd>SPC m c c</kbd> | build                   |
| <kbd>SPC m c i</kbd> | init .tsconfig          |
| <kbd>SPC m f f</kbd> | format code in file     |
| <kbd>SPC m r n</kbd> | refactor rename         |
| <kbd>SPC m s o</kbd> | toggle output           |
| <kbd>SPC m s t</kbd> | show type               |
| <kbd>SPC m s a</kbd> | show ast                |
| <kbd>SPC m s A</kbd> | show full ast           |
| <kbd>SPC m d b</kbd> | toggle breakpoint       |
