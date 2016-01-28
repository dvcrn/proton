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

For installation of ASP.NET 5, you can view the guides on [docs.asp.net](https://docs.asp.net)

* [Guide: Installing ASP.NET 5 on Linux](http://docs.asp.net/en/latest/getting-started/installing-on-linux.html)
* [Guide: Installing ASP.NET 5 on Mac OS X](http://docs.asp.net/en/latest/getting-started/installing-on-mac.html)
* [Guide: Installing ASP.NET 5 on Windows](http://docs.asp.net/en/latest/getting-started/installing-on-windows.html)

### Configuration
Name                                   | Default | Type          | Description
---------------------------------------|---------|---------------|------------------------------------------------
`omnisharp-atom.codeFormat`            | true      | __boolean__ | auto-format code
`omnisharp-atom.enhancedHighlighting`  | false     | __boolean__ | use OmniSharp's advanced C# syntax highlighting
`omnisharp-atom.featureButtons`        | true      | __boolean__ | display features in OmniSharp menu
`omnisharp-atom.intellisense`          | true      | __boolean__ | use OmniSharp's improved auto-complete
`omnisharp-atom.topMenu`               | true      | __boolean__ | display OmniSharp menu in menu bar
`omnisharp-atom.useIcons`              | false      | __boolean__ | use Visual Studio style auto-complete icons


### Mode Key Binding

| Key Binding            | Description       |
|------------------------|-------------------|
| <kbd>SPC m g g</kbd>   | go to definition  |
| <kbd>SPC m g u</kbd>   | show usages       |
| <kbd>SPC m g t </kbd>  | type lookup       |
| <kbd>SPC m r u </kbd>  | fix usings        |
| <kbd>SPC m r r </kbd>  | rename            |
