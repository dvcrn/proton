## Build layer

This layer adds base support for building projects, and introduces the compile category under `<spc> c`.

This uses the [build](https://github.com/noseglid/atom-build) package. For more configuration options and usage examples, see the corresponding documentation

### Install

Add `:tools/build` to your layers.

### Key Bindings

| Key Binding          | Description                          |
|----------------------|--------------------------------------|
| <kbd> SPC c b </kbd> | trigger build                        |
| <kbd> SPC c v </kbd> | toggle build panel                   |
| <kbd> SPC c g </kbd> | cycles through causes of build error |
| <kbd> SPC c h </kbd> | goes to the first build error        |
| <kbd> SPC c t </kbd> | displays the available build targets |
