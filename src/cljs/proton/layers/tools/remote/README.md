## Remote configuration layer

This layer introduces tools for working with remote interfaces.

Includes the following atom packages:

* [http-post](https://github.com/mverhaagen/http-post)
* [api-workbench](https://github.com/mulesoft/api-workbench)
* [atomic-rest](https://github.com/lzrski/atomic-rest)
* [rest-client](https://github.com/ddavison/rest-client)
* [elasticsearch-client](https://github.com/KunihikoKido/atom-elasticsearch-client)
* [sftp-deployment](https://github.com/amoussard/sftp-deployment)

## Setup

TODO

### Install

Add `:tools/remote` to your layers.

### Key Bindings

#### Graphical user interfaces

| Key Binding                 | Description                          |
|-----------------------------|--------------------------------------|
| <kbd> SPC a r </kbd>        | open rest-client application         |

#### Common commands

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
