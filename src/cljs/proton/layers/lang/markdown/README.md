# Markdown Configuration Layer

<!-- MDTOC maxdepth:6 firsth1:1 numbering:0 flatten:0 bullets:1 updateOnSave:1 -->

- [Markdown Configuration Layer](#markdown-configuration-layer)   
   - [Install](#install)   
   - [Key Bindings](#key-bindings)   
      - [Insert](#insert)   
      - [Format](#format)   
      - [Toggle](#toggle)   
      - [Preview](#preview)   

<!-- /MDTOC -->

Adds markdown support to proton.

Includes following atom packages:

-  [md-writer](https://atom.io/packages/markdown-writer)
-  [markdown-preview](https://atom.io/packages/markdown-preview)
-  [markdown-scroll-sync](https://atom.io/packages/markdown-scroll-sync)
-  [linter-markdown](https://atom.io/packages/linter-markdown)
-  [markdown-toc](https://atom.io/packages/markdown-toc)

## Install

Add `:lang/markdown` to your layers.

For linter support add `:tools/linter` to your layers.

## Key Bindings

### Insert

Key Binding          | Description
---------------------|-------------
<kbd>SPC m i l</kbd> | Insert Link
<kbd>SPC m i i</kbd> | Insert Image
<kbd>SPC m i t</kbd> | Insert Table


### Format

Key Binding            | Description
-----------------------|-----------------------------
<kbd>SPC m f t</kbd>   | Format Table
<kbd>SPC m f o</kbd>   | Correct Order List Numbers


### Toggle

Key Binding            | Description
-----------------------|-----------------------------
<kbd>SPC m t c</kbd>   | Toggle Code Text
<kbd>SPC m t b</kbd>   | Toggle Bold Text
<kbd>SPC m t i</kbd>   | Toggle Italic Text
<kbd>SPC m t s</kbd>   | Toggle Strikethrough Text
<kbd>SPC m t k</kbd>   | Toggle Keystrokes
<kbd>SPC m t t</kbd>   | Toggle Task
<kbd>SPC m t d</kbd>   | Toggle Task Done
<kbd>SPC m t o</kbd>   | Toggle Ordered List
<kbd>SPC m t u</kbd>   | Toggle Unordered List
<kbd>SPC m t q</kbd>   | Toggle Blockquote
<kbd>SPC m t h 1</kbd> | Togle H1
<kbd>SPC m t h 2</kbd> | Togle H2
<kbd>SPC m t h 3</kbd> | Togle H3
<kbd>SPC m t h 4</kbd> | Togle H4
<kbd>SPC m t h 5</kbd> | Togle H5

### Preview

Key Binding            | Description
-----------------------|-----------------------------
<kbd>SPC m p t</kbd>   | Toggle Preview
<kbd>SPC m p i</kbd>   | Zoom In in Preview Window
<kbd>SPC m p o</kbd>   | Zoom Out in Preview Window
<kbd>SPC m p r</kbd>   | Reset Zoom in Preview Window
