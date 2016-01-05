## Rust configuration layer

Adds support for the Rust language:

- Intelligent autocompletion provided by the [`racer`](https://github.com/edubkendo/atom-racer) package (needs additional config -- see below)
- Linting support via `cargo` or `rustc` (requires `tools/linter` layer to be activated)
- Project build support via `cargo` (requires `tools/build` layer to be activated)

### Install

Add `:lang/rust` to your layers.

### Configuration

The following config variables can be set in `~/.proton`

| Variable                            | type    | default                    | possible values                     | Description                                                   |
|-------------------------------------|---------|----------------------------|-------------------------------------|---------------------------------------------------------------|
| `linter-rust.useCargo`              | boolean | true                       | -                                   | Use Cargo if it's possible                                    |
| `linter-rust.rustcPath`             | string  | 'rustc'                    | -                                   | Path to Rust's compiler `rustc`                               |
| `linter-rust.rustcBuildTest`        | boolean | false                      | -                                   | Lint test code, when using `rustc`                            |
| `linter-rust.cargoPath`             | string  | 'cargo'                    | -                                   | Path to Rust's package manager `cargo`                        |
| `linter-rust.cargoCommand`          | string  | 'build'                    | ['build', 'check', 'test', 'rustc'] | See Below                                                     |
| `linter-rust.cargoManifestFilename` | string  | 'Cargo.toml'               | -                                   | Cargo manifest filename                                       |
| `linter-rust.jobsNumber`            | integer | 2                          | [1, 2, 4, 6, 8, 10]                 | Number of jobs to run Cargo in parallel                       |
| `racer.racerBinPath`                | string  | `/usr/local/bin/racer`     | -                                   | Path to the Racer executable                                  |
| `racer.rustSrcPath`                 | string  | `/usr/local/src/rust/src/` | -                                   | Path to the Rust source code directory                        |
| `racer.autocompleteBlacklist`       | string  | `.source.go, .comment`     | -                                   | Suggestions not shown in scope(s)                             |
| `racer.show`                        | string  | New                        | ['Right', 'New']                    | Show definition of item at cursor in new pane or to the right |

Set `linter-rust.cargoCommand`  to `check` for fast linting (you need to install
`cargo-check` package). Use `test` to lint test code, too. Use `rustc` for fast
linting (note: does not build the project).

#### Racer

The autocomplete features provided by `racer` require additional configuration. See the [package README](https://github.com/edubkendo/atom-racer) for more information
