## Linter layer

This layer adds base support for linting, and introduces the error category under `<spc> e`.

### Install

Add `:tools/linter` to your layers.


### Configuration

Currently, `tools/linter` allows you to switch between [atom linter](https://github.com/atom-community/linter) and [nuclide diagnostics](https://github.com/facebooknuclideapm/nuclide-diagnostics-ui).

By default, atom linter is being used. If you wish to use nuclide instead, set the following inside your config:


Name                     | Default | Type        | Description
-------------------------|---------|-------------|--------------------------------------------------------
`proton.linter.provider` | :atom   | __keyword__ | which linter to pick? Options are `:atom` or `:nuclide`

### Key Bindings

Key Binding          | Description
---------------------|------------
<kbd> SPC e s </kbd> | Show errors

#### :atom mode

Key Binding          | Description
---------------------|------------------
<kbd> SPC e t </kbd> | Toggle linter
<kbd> SPC e n </kbd> | Next error
<kbd> SPC e p </kbd> | Previous error
<kbd> SPC e l </kbd> | Lint current file
