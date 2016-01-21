## Go configuration layer

Adds golang support to proton.

Includes following packages:

- [go-plus](https;//atom.io/packages/go-plus)
- [go-get](https;//atom.io/packages/go-get)
- [go-config](https;//atom.io/packages/go-config)
- [go-debug](https;//atom.io/packages/go-debug)
- [go-rename](https;//atom.io/packages/go-rename)
- [gometalinter-linter](https;//atom.io/packages/gometalinter-linter)

#### Requirements:

- [delve](https://github.com/derekparker/delve)
- [gorename](https://godoc.org/golang.org/x/tools/cmd/gorename)
- [gometalinter](https://github.com/alecthomas/gometalinter)

Also check messages from `go-plus` info panel.

## Install

Add `:lang/go` to your layers.
For linters support add `:tools/linter` to your layers.

## Mode keybindings

| Key Binding               | Description             |
|---------------------------|-------------------------|
| <kbd>SPC m =</kbd>        | format file             |
| <kbd>SPC m g g</kbd>      | go to definition        |
| <kbd>SPC m g q</kbd>      | go back from definition |
| <kbd>SPC m c s</kbd>      | check syntax            |
| <kbd>SPC m c v</kbd>      | check with go vet       |
| <kbd>SPC m c l</kbd>      | lint current file       |
| <kbd>SPC m t c</kbd>      | show test coverage      |
| <kbd>SPC m t ctrl-c</kbd> | clear test coverage     |
| <kbd>SPC m d s</kbd>      | debug run               |
| <kbd>SPC m d c</kbd>      | debug continue          |
| <kbd>SPC m d n</kbd>      | debug next              |
| <kbd>SPC m d N</kbd>      | debug step              |
| <kbd>SPC m d R</kbd>      | debug restart           |
| <kbd>SPC m i u</kbd>      | download/update package |
