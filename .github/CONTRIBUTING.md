# Contribution guidelines

Thank you for your interest in contributing to proton! You rock!

Here are some smaller things to look out for when adding code to this repository

To keep things clean and similar, make sure you read the [how to layer](https://github.com/dvcrn/proton/blob/master/HOW-TO-LAYER.md) document in how to structure your layer correctly.

## Style guide

Most of the contributors use atom+parinfer to develop proton. Make sure that your code doesn't break Parinfer indentation and works even after Parinfer re-formatted your code.

## Commit format

Please use the following format to submit commits to proton
```
feat(ngInclude): add template url parameter to events

The `src` (i.e. the url of the template to load) is now provided to the
`$includeContentRequested`, `$includeContentLoaded` and `$includeContentError`
events.

Closes #8453
Closes #8454
```

```
                      component        commit title
        commit type       /                /      
                \        |                |
                 feat(ngInclude): add template url parameter to events
            
        body ->  The 'src` (i.e. the url of the template to load) is now provided to the
                 `$includeContentRequested`, `$includeContentLoaded` and `$includeContentError`
                 events.

 referenced  ->  Closes #8453
 issues          Closes #8454
```

For the commit type, use the matching type from this list:
- feat
- fix
- docs
- style
- refactor
- test
- chore

### Linking to github

Make sure that you use the __present tense__ for `Closes` and `Fixes` and add `References #XX` before referencing a github issue if you are not closing it. 

## Pull requests
Pull requests are currently submitted to `master`. This might change in the future so please keep an eye on this.
