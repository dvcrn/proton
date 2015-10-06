ProtonView = require './proton-view'
{CompositeDisposable} = require 'atom'

module.exports = Proton =
  protonView: null
  modalPanel: null
  subscriptions: null

  activate: (state) ->
    @protonView = new ProtonView(state.protonViewState)
    @modalPanel = atom.workspace.addModalPanel(item: @protonView.getElement(), visible: false)

    # Events subscribed to in atom's system can be easily cleaned up with a CompositeDisposable
    @subscriptions = new CompositeDisposable

    # Register command that toggles this view
    @subscriptions.add atom.commands.add 'atom-workspace', 'proton:toggle': => @toggle()

  deactivate: ->
    @modalPanel.destroy()
    @subscriptions.dispose()
    @protonView.destroy()

  serialize: ->
    protonViewState: @protonView.serialize()

  toggle: ->
    console.log 'Proton was toggled!'
    km = atom.keymaps
    ws = atom.workspace
    cr = atom.commands

    workspaceView = atom.views.getView(atom.workspace);
    cr.dispatch(workspaceView, "tree-view:toggle")

    if @modalPanel.isVisible()
      @modalPanel.hide()
    else
      @modalPanel.show()
