## Remote configuration layer

This layer introduces tools for working with remote interfaces.

Includes the following atom packages:

* [rest-client](https://github.com/ddavison/rest-client)
* [elasticsearch-client](https://github.com/KunihikoKido/atom-elasticsearch-client)

## Setup

TODO

### Install

Add `:tools/remote` to your layers.

### Key Bindings

#### Graphical user interfaces

| Key Binding                 | Description                          |
|-----------------------------|--------------------------------------|
| <kbd> SPC a r </kbd>        | open rest-client application         |

#### Common to all clients

| Key Binding                 | Description                          |
|-----------------------------|--------------------------------------|
| <kbd> SPC r x </kbd>        | change mode                          |
| <kbd> SPC r s </kbd>        | change server                        |
| <kbd> SPC r u </kbd>        | update document on remote client     |
| <kbd> SPC r g </kbd>        | get document from remote client      |

#### Elasticsearch client

| Key Binding                 | Description                          |
|-----------------------------|--------------------------------------|
| <kbd> SPC r d </kbd>        | document                             |
| <kbd> SPC r c </kbd>        | cluster                              |
| <kbd> SPC r i </kbd>        | indices                              |
| <kbd> SPC r n </kbd>        | nodes                                |
