## Core configuration layer

This layer contains the base functionality of proton.

### Install

The core layer should get included by default. No installation needed.

### Configuration

| Name                              | Default        | Type        | Description                                                                                |
|-----------------------------------|----------------|-------------|--------------------------------------------------------------------------------------------|
| `proton.core.showTabBar`          | false          | __boolean__ | whether the tab bar should be visible by default                                           |
| `proton.core.relativeLineNumbers` | false          | __boolean__ | whether to use relative line numbers instead of absolute ones                              |
| `proton.core.quickOpenProvider`   | :normal        | __keyword__ | which quickOpen to use. Possible options are `:normal` and `:nuclide`                      |
| `proton.core.vim-provider`        | :vim-mode-plus | __keyword__ | which vim emulation provider to use. Possible options are `:vim-mode-plus` and `:vim-mode` |

### Key Bindings

#### Root

Key Binding        | Description
-------------------|------------------------
<kbd>SPC 0-9</kbd> | focus on N pane
<kbd>SPC tab</kbd> | visit last used buffer
<kbd>SPC SPC</kbd> | easy motion by letter
<kbd>SPC v</kbd>   | expand region
<kbd>SPC ;</kbd>   | toggle line comments
<kbd>SPC .</kbd>   | repeat last proton command


#### Files

Key Binding            | Description
-----------------------|----------------------
<kbd> SPC f = </kbd>   | format file with atom-beautify
<kbd> SPC f f </kbd>   | open / create file
<kbd> SPC f e d </kbd> | open proton dotfile
<kbd> SPC f e s </kbd> | open atom stylesheet
<kbd> SPC f e i </kbd> | open atom init script
<kbd> SPC f e p </kbd> | open atom snippets

#### Window

Key Binding          | Description
---------------------|------------------------------------------------
<kbd> SPC w d </kbd> | Destroy current window / pane
<kbd> SPC w j </kbd> | Go to pane below
<kbd> SPC w k </kbd> | Go to pane above
<kbd> SPC w l </kbd> | Go to pane on the left
<kbd> SPC w h </kbd> | Go to pane on the right
<kbd> SPC w v </kbd> | Split vertically
<kbd> SPC w s </kbd> | Split horizontally
<kbd> SPC w V </kbd> | Split vertically and focus left
<kbd> SPC w S </kbd> | Split horizontally and focus up
<kbd> SPC w o </kbd> | Destroy all other panes (maximise current pane)
<kbd> SPC w m </kbd> | Maximize active pane
<kbd> SPC w c </kbd> | Close active pane

#### Buffer

Key Binding          | Description
---------------------|--------------------------------
<kbd> SPC b b </kbd> | Browse buffers
<kbd> SPC b K </kbd> | Kill other buffers
<kbd> SPC b d </kbd> | Delete / destroy current buffer

#### Search

Key Binding          | Description
---------------------|-------------------------------------------------------------
<kbd> SPC s e </kbd> | Expand selection (multi-select all matching words in buffer)
<kbd> SPC s n </kbd> | Multi-select next word
<kbd> SPC s s </kbd> | Skip next word for multi-select
<kbd> SPC s u </kbd> | Undo last select

#### Project

Key Binding          | Description
---------------------|--------------------------
<kbd> SPC p / </kbd> | Find in project files
<kbd> SPC p f </kbd> | Find file in project
<kbd> SPC p r </kbd> | Find recently opened file
<kbd> SPC p b </kbd> | List open buffers
<kbd> SPC p t </kbd> | Toggle tree view
<kbd> SPC p p </kbd> | Switch project
<kbd> SPC p s </kbd> | Save project
<kbd> SPC p e </kbd> | Edit project

#### Toggles

Key Binding          | Description
---------------------|-----------------------------
<kbd> SPC t t </kbd> | Toggle tab bar
<kbd> SPC t n </kbd> | Toggle line numbers
<kbd> SPC t r </kbd> | Toggle relative line numbers
<kbd> SPC t s </kbd> | Toggle status bar
<kbd> SPC t w </kbd> | Toggle whitespace
<kbd> SPC t i </kbd> | Toggle indent guide
<kbd> SPC t I </kbd> | Toggle auto indent
<kbd> SPC t g </kbd> | Toggle golden ratio



#### UI toggles/themes

Key Binding          | Description
---------------------|-----------------------------
<kbd> SPC T F </kbd> | Toggle full screen
<kbd> SPC T M </kbd> | Toggle maximize
<kbd> SPC T n </kbd> | Cycle themes
<kbd> SPC T m </kbd> | Toggle menu bar

#### Meta

Key Binding          | Description
---------------------|--------------------
<kbd> SPC _ d </kbd> | Find proton dotfile
<kbd> SPC _ R </kbd> | Reload atom
<kbd> SPC _ c </kbd> | Toggle dev tools
<kbd> SPC _ r </kbd> | Reload proton-mode package
