## LaTeX configuration layer

Adds LaTeX support to proton.

Includes the following atom packages:

- [language-latex](https://github.com/area/language-latex)
- [latexer](https://atom.io/packages/latexer): adds latex to autocomplete-plus
- [autocomplete-bibtex](https://atom.io/packages/autocomplete-bibtex): autocomplete-plus provider for bibtex entries
- [pdf-view](https://atom.io/packages/pdf-view): View pdf produced by building the tex file
- [latex-plus](https://atom.io/packages/latex-plus) _or_ [latex](https://github.com/thomasjo/atom-latex) (see config section): `latexmk` wrapper

### Install

Add `:lang/latex` to your layers.

### Requirements

- A modern tex distribution with at least `latexmk`

### Configuration

| Name                            | Default | Type        | Description                                                         |
|---------------------------------|---------|-------------|---------------------------------------------------------------------|
| `proton.latex.latexmk-provider` | :latex  | __keyword__ | which latex provider to pick? Options are `:latex` or `:latex-plus` |
