## Clojure configuration layer

This layer adds support for clojure based file editing.

Includes following packages:

- [Parinfer](https://atom.io/packages/parinfer)
- [proto-repl](https://atom.io/packages/proto-repl)
- [linter-clojure](https://atom.io/packages/linter-clojure)

### Install

Add `:lang/clojure` to your layers.

For linter support add `:tools/linter` to your layers.

### Configuration

Name                                   | Default | Type       | Description
---------------------------------------|---------|------------|---------------------------------------------------------------------------------------------------------------------
`linter-clojure.javaExecutablePath`    | ""      | __string__ | full path of your java executable. Eg. `/usr/local/bin/java`
`linter-clojure.clojureExecutablePath` | ""      | __string__ | full path of your clojure executable. Eg. `/Users/foobar/.m2/repository/org/clojure/clojure/1.7.0/clojure-1.7.0.jar`

### Key Bindings

#### Parinfer

| Key Binding          | Description          |
|----------------------|----------------------|
| <kbd>SPC m t p</kbd> | toggle Parinfer mode |

#### Proto repl

| Key Binding               | Description                        |
|---------------------------|------------------------------------|
| <kbd>SPC m s i</kbd>      | toggle repl                        |
| <kbd>SPC m s I</kbd>      | start remote connection            |
| <kbd>SPC m e r</kbd>      | eval selected text                 |
| <kbd>SPC m e b</kbd>      | eval block                         |
| <kbd>SPC m e B</kbd>      | eval top block                     |
| <kbd>SPC m h d</kbd>      | print var doc                      |
| <kbd>SPC m h c</kbd>      | print var code                     |
| <kbd>SPC m h s</kbd>      | print var/ns code                  |
| <kbd>SPC m s b</kbd>      | load current file to repl          |
| <kbd>SPC m s c</kbd>      | clear repl                         |
| <kbd>SPC m s ctrl-c</kbd> | interrupt repl                     |
| <kbd>SPC m s p</kbd>      | pretty print                       |
| <kbd>SPC m s q</kbd>      | quit repl                          |
| <kbd>SPC m s x</kbd>      | refresh namespaces                 |
| <kbd>SPC m s X</kbd>      | super refresh namespaces           |
| <kbd>SPC m t a</kbd>      | run all tests                      |
| <kbd>SPC m t c</kbd>      | run all tests in current namespace |
