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

Key Binding | Description
------------|----------------------------------
`SPC g a`   | Git Add Files
`SPC g S`   | Git Status
`SPC g s`   | Atomatigit Git Status
`SPC g P`   | Git Push
`SPC g c`   | Git Commit
`SPC g b`   | Toggle Git Blame
`SPC g L`   | Git Log
`SPC g l`   | Git Log for Current File
`SPC g h`   | Git History for Current File
`SPC g d n` | Git Diff Next Hunk
`SPC g d N` | Git Diff Previous Hunk
`SPC g d l` | Toggle Diff List for Current File

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
