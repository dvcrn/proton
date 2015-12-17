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

Key Binding          | Description
---------------------|------------------------
<kbd>SPC m s</kbd>   | Start a Julia client
<kbd>SPC m r</kbd>   | Open a REPL
<kbd>SPC m R</kbd>   | Start a REPL client
<kbd>SPC m l</kbd>   | Reset loading indicator
<kbd>SPC m k</kbd>   | Kill Julia client
<kbd>SPC m c c</kbd> | Clear Julia console
<kbd>SPC m c i</kbd> | Clear inline results
