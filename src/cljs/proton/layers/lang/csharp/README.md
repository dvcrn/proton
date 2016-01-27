## C# configuration layer

Adds C# autocomplete, formatting, linting, and refactoring to Atom powered by
[OmniSharp](http://www.omnisharp.net/).

Includes following atom packages:

* [atom-yeoman](https://atom.io/packages/atom-yeoman)
* [json-schema](https://atom.io/packages/json-schema)
* [omnisharp-atom](https://atom.io/packages/omnisharp-atom)

### Install

Add `:lang/csharp` to your layers.

### Requirements

- [ASP.NET 5](https://github.com/aspnet/Home)

For installation of ASP.NET 5, you can view the guides on [docs.asp.net](docs.asp.net)

* [Guide: Installing ASP.NET 5 on Linux](http://docs.asp.net/en/latest/getting-started/installing-on-mac.html)
* [Guide: Installing ASP.NET 5 on Mac OS X](http://docs.asp.net/en/latest/getting-started/installing-on-linux.html)
* [Guide: Installing ASP.NET 5 on Windows](http://docs.asp.net/en/latest/getting-started/installing-on-windows.html)

### Configuration
- `linter-pep8.ignoreErrorCodes`: a array of pep8 error codes to ignore
- `linter-pep8.pep8ExecutablePath`: which pep8 executable to use
- `linter-pep8.maxLineLength`: your max line length

### Mode Key Binding

| Key Binding            | Description       |
|------------------------|-------------------|
| <kbd>SPC m =</kbd>     | format using yapf |
| <kbd>SPC m S</kbd>     | select string     |
| <kbd>SPC m g g</kbd>   | go to definition  |
| <kbd>SPC m g u</kbd>   | show usages       |
| <kbd>SPC m r i s</kbd> | sort imports      |
