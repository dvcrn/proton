## Python configuration layer

Adds better python autocomplete and formatting to Atom powered by [jedi](https://github.com/davidhalter/jedi). Linting is done through pep8.

### Install

Add `:lang/python` to your layers.

### Requirements

- [yapf](https://github.com/google/yapf)
- [jedi](https://github.com/davidhalter/jedi)
- [pep8](http://pep8.readthedocs.org/en/latest/intro.html)

### Configuration
- `linter-pep8.ignoreErrorCodes`: a array of pep8 error codes to ignore
- `linter-pep8.pep8ExecutablePath`: which pep8 executable to use
- `linter-pep8.maxLineLength`: your max line length
