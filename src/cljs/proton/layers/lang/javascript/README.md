## JavaScript configuration layer

Adds javascript support to proton.

Includes following atom packages:

- [atom-ternjs](https://atom.io/packages/atom-ternjs)
- [javascript-snippets](https://atom.io/packages/javascript-snippets)
- [language-javascript](https://atom.io/packages/language-javascript)
- [autocomplete-modules](https://atom.io/packages/autocomplete-modules)
- [docblockr](https://atom.io/packages/docblockr)
- [react](https://atom.io/packages/react)
- [react-snippets](https://atom.io/packages/react-snippets)

### Install

Add `:lang/javascript` to your layers.
For linters support add `:tools/linters`.

### Layer Configuration

Name                            | Default  | Type       | Description
--------------------------------|----------|------------|-------------------------------
`proton.lang.javascript.linter` | "eslint" | __string__ | Which linter to use by default

### Packages configurations

Name                                        | Default | Type        | Title                                                 | Description
--------------------------------------------|---------|-------------|-------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------
`atom-ternjs.documentation`                 | true    | __boolean__ | Documentation                                         | Whether to include documentation string (if found) in the result data.
`atom-ternjs.urls`                          | true    | __boolean__ | Url                                                   | Whether to include documentation urls (if found) in the result data.
`atom-ternjs.useSnippetsAndFunction`        | false   | __boolean__ | Display both, autocomplete-snippets and function name | Choose to just complete the function name or expand the snippet
`atom-ternjs.lint`                          | true    | __boolean__ | Use tern-lint                                         | Use tern-lint to validate JavaScript files to collect semantic errors. Restart atom after this option has been changed.
`atom-ternjs.sort`                          | true    | __boolean__ | Sort                                                  | Determines whether the result set will be sorted.
`atom-ternjs.displayAboveSnippets`          | false   | __boolean__ | Display above snippets                                | Displays ternjs suggestions above snippet suggestions. Requires a restart.
`atom-ternjs.caseInsensitive`               | true    | __boolean__ | Case-insensitive                                      | Whether to use a case-insensitive compare between the current word and potential completions.
`atom-ternjs.excludeLowerPriorityProviders` | false   | __boolean__ | Exclude lower priority providers                      | Whether to exclude lower priority providers (e.g. autocomplete-paths)
`atom-ternjs.origins`                       | true    | __boolean__ | Origin                                                | Whether to include origins (if found) in the result data.
`atom-ternjs.useSnippets`                   | false   | __boolean__ | Use autocomplete-snippets                             | Adds snippets to autocomplete+ suggestions
`atom-ternjs.guess`                         | true    | __boolean__ | Guess                                                 | When completing a property and no completions are found, Tern will use some heuristics to try and return some properties anyway. Set this to false to turn that off.
`atom-ternjs.inlineFnCompletion`            | true    | __boolean__ | Display inline suggestions for function params        | Displays a inline suggestion located right next to the current cursor
`docblockr.min_spaces_between_columns`      | 1       | __number__  |                                                       |
`docblockr.deep_indent`                     | false   | __boolean__ |                                                       |
`docblockr.newline_after_block`             | false   | __boolean__ |                                                       |
`docblockr.spacer_between_sections`         | false   | __boolean__ |                                                       |
`docblockr.auto_add_method_tag`             | false   | __boolean__ |                                                       |
`docblockr.extra_tags_go_after`             | false   | __boolean__ |                                                       |
`docblockr.extend_double_slash`             | true    | __boolean__ |                                                       |
`docblockr.align_tags`                      | deep    | __string__  |                                                       |
`docblockr.extra_tags`                      | []      | __array__   |                                                       |
`docblockr.notation_map`                    | []      | __array__   |                                                       |
`docblockr.override_js_var`                 | false   | __boolean__ |                                                       |
`docblockr.indentation_spaces_same_para`    | 1       | __number__  |                                                       |
`docblockr.per_section_indent`              | false   | __boolean__ |                                                       |
`docblockr.simple_mode`                     | false   | __boolean__ |                                                       |
`docblockr.param_description`               | true    | __boolean__ |                                                       |
`docblockr.short_primitives`                | false   | __boolean__ |                                                       |
`docblockr.indentation_spaces`              | 1       | __number__  |                                                       |
`docblockr.development_mode`                | false   | __boolean__ |                                                       |
`docblockr.return_description`              | true    | __boolean__ |                                                       |
`docblockr.lower_case_primitives`           | false   | __boolean__ |                                                       |
`docblockr.return_tag`                      | @return | __string__  |                                                       |

### Mode Key Binding

| Key Binding            | Description                    |
|------------------------|--------------------------------|
| <kbd>SPC m g g</kbd>   | Tern jump to definition        |
| <kbd>SPC m g q</kbd>   | Tern jump back from definition |
| <kbd>SPC m g r</kbd>   | Tern list references           |
| <kbd>SPC m s l</kbd>   | Show file symbols              |
| <kbd>SPC m r r v</kbd> | Tern rename variable           |
| <kbd>SPC m L e</kbd>   | Switch to eslint linter        |
| <kbd>SPC m L j</kbd>   | Switch to jshint linter        |
