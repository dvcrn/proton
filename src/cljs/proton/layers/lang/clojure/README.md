## Clojure configuration layer

This layer adds support for clojure based file editind powered by [parinfer](https://shaunlebron.github.io/parinfer/).

### Install

Add `:lang/clojure` to your layers.

### Configuration

Name                                   | Default | Type       | Description
---------------------------------------|---------|------------|---------------------------------------------------------------------------------------------------------------------
`linter-clojure.javaExecutablePath`    | ""      | __string__ | full path of your java executable. Eg. `/usr/local/bin/java`
`linter-clojure.clojureExecutablePath` | ""      | __string__ | full path of your clojure executable. Eg. `/Users/foobar/.m2/repository/org/clojure/clojure/1.7.0/clojure-1.7.0.jar`

### Key Bindings

| Key Binding          | Description          |
|----------------------|----------------------|
| <kbd>SPC m t p</kbd> | toggle Parinfer mode |
