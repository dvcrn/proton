## HTML configuration layer

Adds support for HTML language.

Includes following packages:

- [Stylus](https://atom.io/packages/stylus)
- [templates](https://atom.io/packages/templates)
- [language-slim](https://atom.io/packages/language-slim)
- [language-haml](https://atom.io/packages/language-haml)
- [atom-handlebars](https://atom.io/packages/atom-handlebars)
- [autoclose-html](https://atom.io/packages/autoclose-html)
- [autocomplete-html-entities](https://atom.io/packages/autocomplete-html-entities)
- [emmet](https://atom.io/packages/emmet)
- [linter-stylint](https://atom.io/packages/linter-stylint)
- [linter-less](https://atom.io/packages/linter-less)
- [linter-bootlint](https://atom.io/packages/linter-bootlint)
- [linter-haml](https://atom.io/packages/linter-haml)
- [linter-slim](https://atom.io/packages/linterp-slim)
- [linter-xmllint](https://atom.io/packages/linter-xmllint)

### Install

Add `:lang/html` to your layers.

For linters support add `:tools/linters`.

### Mode Key Binding (HTML based syntax)

Key Binding            | Description
-----------------------|--------------------------------------
<kbd> SPC m e </kbd>   | Emmet next edit point
<kbd> SPC m n </kbd>   | Emmet select next item
<kbd> SPC m N </kbd>   | Emmet select previous item
<kbd> SPC m j </kbd>   | Emmet split/join lines
<kbd> SPC m d </kbd>   | Emmet delete tag
<kbd> SPC m c </kbd>   | Emmet change tag
<kbd> SPC m v </kbd>   | Emmet balance-outward
<kbd> SPC m V </kbd>   | Emmet balance-inward
<kbd> SPC m tab </kbd> | Emmet expand-abbreviation
<kbd> SPC m SPC </kbd> | Emmet interactive expand abbreviation
<kbd> SPC m i s </kbd> | Emmet update image size
<kbd> SPC m i e </kbd> | Emmet encode decode image


### Mode Key Binding (Less, Sass, Scss)

Key Binding            | Description
-----------------------|----------------------------------------
<kbd> SPC m f c </kbd>   | Format file/selection with css-comb
