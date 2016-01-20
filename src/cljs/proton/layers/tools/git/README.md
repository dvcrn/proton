## Git configuration layer

This layer adds git keybindings powered by git-plus and introduces the git category under `<spc> g`.

Includes following atom packages:

- [git-plus](https://atom.io/packages/git-plus)
- [language-diff](https://atom.io/packages/language-diff)
- [atomatigit](https://atom.io/packages/atomatigit)
- [blame](https://atom.io/packages/blame)
- [merge-conflicts](https://atom.io/packages/merge-conflicts)
- [git-history](https://atom.io/packages/git-history)

### Install

Add `:tools/git` to your layers.

### Key Bindings

#### git

Key Binding         | Description
--------------------|-----------------
<kbd> SPC g L</kbd> | log current file
<kbd> SPC g r</kbd> | rebase
<kbd> SPC g A</kbd> | add all files
<kbd> SPC g m</kbd> | merge
<kbd> SPC g P</kbd> | push
<kbd> SPC g B</kbd> | blame
<kbd> SPC g s</kbd> | status
<kbd> SPC g l</kbd> | log project
<kbd> SPC g h</kbd> | file history
<kbd> SPC g f</kbd> | fetch
<kbd> SPC g p</kbd> | pull
<kbd> SPC g a</kbd> | add files

#### git stash

Key Binding           | Description
----------------------|------------
<kbd> SPC g S s</kbd> | stash
<kbd> SPC g S a</kbd> | apply
<kbd> SPC g S p</kbd> | pop
<kbd> SPC g S d</kbd> | drop

#### git diff

Key Binding           | Description
----------------------|--------------
<kbd> SPC g d n</kbd> | next diff
<kbd> SPC g d N</kbd> | previous diff
<kbd> SPC g d l</kbd> | list diffs

#### git branch

Key Binding           | Description
----------------------|-----------------------
<kbd> SPC g b c</kbd> | checkout branch
<kbd> SPC g b C</kbd> | create branch
<kbd> SPC g b r</kbd> | checkout remote branch
<kbd> SPC g b d</kbd> | delete local branch
<kbd> SPC g b D</kbd> | delete remote branch

#### git commit

Key Binding           | Description
----------------------|-------------
<kbd> SPC g c c</kbd> | commit
<kbd> SPC g c C</kbd> | commit all
<kbd> SPC g c a</kbd> | amend commit

### Configuration

Name                             | Default                  | Type        | Description
---------------------------------|--------------------------|-------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------
`git-plus.includeStagedDiff`     | true                     | __boolean__ | Include staged diffs?
`git-plus.openInPane`            | true                     | __boolean__ | Allow commands to open new panes
`git-plus.splitPane`             | 'right'                  | __string__  | Where should new panes go? (Defaults to right)
`git-plus.wordDiff`              | true                     | __boolean__ | Should word diffs be highlighted in diffs?
`git-plus.amountOfCommitsToShow` | 25                       | __integer__ | Amount Of Commits To Show
`git-plus.gitPath`               | 'git'                    | __string__  | Where is your git?
`git-plus.messageTimeout`        | 5                        | __integer__ | How long should success/error messages be shown?
`merge-conflicts.gitPath`        | ''                       | __string__  | Absolute path to your git executable.
`blame.gutterFormat`             | '{hash} {date} {author}' | __string__  | Placeholders: `{hash}`, `{date}` and `{author}.`
`blame.dateFormat`               | YYYY-MM-DD               | __string__  | Placeholders: `YYYY` (year), `MM` (month), `DD` (day), `HH` (hours), `mm` (minutes).See [momentjs documentation](http://momentjs.com/docs/) for more information.
`blame.defaultWith`              | 250                      | __integer__ | Default width (px)
`git-history.showDiff`           | false                    | __boolean__ | Show diff for selected item
`git-history.maxCommits`         | 100                      | __integer__ | Maximum visible items
