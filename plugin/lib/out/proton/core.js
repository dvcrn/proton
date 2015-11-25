// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.core');
goog.require('cljs.core');
goog.require('cljs.nodejs');
proton.core.ashell = cljs.nodejs.require.call(null,"atom");
proton.core.commands = atom.commands;
proton.core.workspace = atom.workspace;
proton.core.composite_disposable = proton.core.ashell.CompositeDisposable;
proton.core.subscriptions = (new proton.core.composite_disposable());
proton.core.div = (function proton$core$div(text){
var d = document.createElement("div");
d.textContent = text;

return d;
});
cljs.nodejs.enable_util_print_BANG_.call(null);
proton.core.deactivate = (function proton$core$deactivate(){
return console.log("deactivating...");
});
goog.exportSymbol('proton.core.deactivate', proton.core.deactivate);
proton.core.modal_panel = cljs.core.atom.call(null,proton.core.workspace.addModalPanel(cljs.core.clj__GT_js.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"visible","visible",-1024216805),false,new cljs.core.Keyword(null,"item","item",249373802),proton.core.div.call(null,"test")], null))));
proton.core.toggle = (function proton$core$toggle(){
console.log("I got toggled!");

return cljs.core.deref.call(null,proton.core.modal_panel).show();
});
goog.exportSymbol('proton.core.toggle', proton.core.toggle);
proton.core.activate = (function proton$core$activate(state){
console.log("Hello World");

proton.core.subscriptions.add(proton.core.commands.add("atom-text-editor.vim-mode:not(.insert-mode)","proton:toggle",proton.core.toggle));

return proton.core.subscriptions.add(proton.core.commands.add("atom-workspace","proton:toggle",proton.core.toggle));
});
goog.exportSymbol('proton.core.activate', proton.core.activate);
proton.core.noop = (function proton$core$noop(){
return null;
});
cljs.core._STAR_main_cli_fn_STAR_ = proton.core.noop;
(module["exports"] = proton.core);
