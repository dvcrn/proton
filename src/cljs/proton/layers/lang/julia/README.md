## Julia configuration layer

Adds Julia support to proton.

Includes the following atom packages:

- [language-julia](https://github.com/JuliaLang/atom-language-julia)
- [ink](https://github.com/JunoLab/atom-ink): IDE toolkit for atom
- [julia-client](https://github.com/JunoLab/atom-julia-client):
- [latex-completions](https://github.com/JunoLab/atom-latex-completions): LaTeX syntax unicode autocomplte-plus provider (generated from Julia source)

### Install

Add `:lang/julia` to your layers.

### Requirements

- [julia](http://julialang.org/) and a few Julia packages (it is recommended to do `Pkg.add("PkgName"); Pkg.checkout("PkgName")` for each of the packages below):
  - [Atom](https://github.com/JunoLab/Atom.jl)
  - [JuliaParser](https://github.com/jakebolewski/JuliaParser.jl)

### Key Bindings

Key Binding   | Description
--------------|-----------------------------
`SPC m s`     | Start a Julia client
`SPC m r`     | Open a REPL
`SPC m R`     | Start a REPL client
`SPC m l`     | Reset loading indicator
`SPC m k`     | Kill Julia client
`SPC m c c`   | Clear Julia console
`SPC m c i`   | Clear inline results
