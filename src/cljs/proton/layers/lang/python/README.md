## Python configuration layer

Adds better python autocomplete and formatting to Atom powered by [jedi](https://github.com/davidhalter/jedi). Linting is done through flake8.

Includes following atom packages:

- [autocomplete-python](https://atom.io/packages/autocomplete-python)
- [python-tools](https://atom.io/packages/python-tools)
- [linter-flake8](https://atom.io/packages/linter-flake8)
- [python-isort](https://atom.io/packages/python-isort)

### Install

Add `:lang/python` to your layers.
For django support include `:frameworks/django` to your layers.

### Requirements

- [yapf](https://github.com/google/yapf)
- [jedi](https://github.com/davidhalter/jedi)
- [flake8](http://flake8.readthedocs.org/en/latest/intro.html)
- [isort](https://github.com/timothycrosley/isort)

### Configuration
- `linter-flake8.ignoreErrorCodes`: a array of flake8 error codes to ignore
- `linter-flake8.executablePath`: which flake8 executable to use
- `linter-flake8.maxLineLength`: your max line length

### Mode Key Binding

| Key Binding            | Description       |
|------------------------|-------------------|
| <kbd>SPC m =</kbd>     | format using yapf |
| <kbd>SPC m S</kbd>     | select string     |
| <kbd>SPC m g g</kbd>   | go to definition  |
| <kbd>SPC m g u</kbd>   | show usages       |
| <kbd>SPC m r i s</kbd> | sort imports      |
