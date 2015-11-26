// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.core');
goog.require('cljs.core');
goog.require('proton.layers.base');
goog.require('proton.lib.helpers');
goog.require('proton.lib.atom');
goog.require('proton.layers.core.core');
goog.require('cljs.nodejs');
goog.require('clojure.string');
goog.require('proton.lib.package_manager');
cljs.nodejs.enable_util_print_BANG_.call(null);
proton.core.ashell = cljs.nodejs.require.call(null,"atom");
proton.core.commands = atom.commands;
proton.core.workspace = atom.workspace;
proton.core.keymaps = atom.keymaps;
proton.core.views = atom.views;
proton.core.composite_disposable = proton.core.ashell.CompositeDisposable;
proton.core.subscriptions = (new proton.core.composite_disposable());
proton.core.command_tree = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);
proton.core.required_packages = cljs.core.atom.call(null,cljs.core.PersistentVector.EMPTY);
proton.core.enabled_layers = new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"core","core",-86019209)], null);
var seq__8523_8527 = cljs.core.seq.call(null,proton.core.enabled_layers);
var chunk__8524_8528 = null;
var count__8525_8529 = (0);
var i__8526_8530 = (0);
while(true){
if((i__8526_8530 < count__8525_8529)){
var layer_8531 = cljs.core._nth.call(null,chunk__8524_8528,i__8526_8530);
cljs.core.println.call(null,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_8531)));

cljs.core.swap_BANG_.call(null,proton.core.required_packages,cljs.core.concat,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_8531)));

cljs.core.swap_BANG_.call(null,proton.core.command_tree,cljs.core.merge,proton.layers.base.get_keybindings.call(null,cljs.core.keyword.call(null,layer_8531)));

var G__8532 = seq__8523_8527;
var G__8533 = chunk__8524_8528;
var G__8534 = count__8525_8529;
var G__8535 = (i__8526_8530 + (1));
seq__8523_8527 = G__8532;
chunk__8524_8528 = G__8533;
count__8525_8529 = G__8534;
i__8526_8530 = G__8535;
continue;
} else {
var temp__4425__auto___8536 = cljs.core.seq.call(null,seq__8523_8527);
if(temp__4425__auto___8536){
var seq__8523_8537__$1 = temp__4425__auto___8536;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8523_8537__$1)){
var c__5471__auto___8538 = cljs.core.chunk_first.call(null,seq__8523_8537__$1);
var G__8539 = cljs.core.chunk_rest.call(null,seq__8523_8537__$1);
var G__8540 = c__5471__auto___8538;
var G__8541 = cljs.core.count.call(null,c__5471__auto___8538);
var G__8542 = (0);
seq__8523_8527 = G__8539;
chunk__8524_8528 = G__8540;
count__8525_8529 = G__8541;
i__8526_8530 = G__8542;
continue;
} else {
var layer_8543 = cljs.core.first.call(null,seq__8523_8537__$1);
cljs.core.println.call(null,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_8543)));

cljs.core.swap_BANG_.call(null,proton.core.required_packages,cljs.core.concat,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_8543)));

cljs.core.swap_BANG_.call(null,proton.core.command_tree,cljs.core.merge,proton.layers.base.get_keybindings.call(null,cljs.core.keyword.call(null,layer_8543)));

var G__8544 = cljs.core.next.call(null,seq__8523_8537__$1);
var G__8545 = null;
var G__8546 = (0);
var G__8547 = (0);
seq__8523_8527 = G__8544;
chunk__8524_8528 = G__8545;
count__8525_8529 = G__8546;
i__8526_8530 = G__8547;
continue;
}
} else {
}
}
break;
}
cljs.core.println.call(null,cljs.core.deref.call(null,proton.core.command_tree));
cljs.core.println.call(null,cljs.core.deref.call(null,proton.core.required_packages));
proton.core.current_chain = cljs.core.atom.call(null,cljs.core.PersistentVector.EMPTY);
proton.core.chain = (function proton$core$chain(e){
var letter = proton.lib.helpers.extract_keyletter_from_event.call(null,e);
var key_code = proton.lib.helpers.extract_keycode_from_event.call(null,e);
if(cljs.core._EQ_.call(null,key_code,(27))){
return proton.lib.atom.deactivate_proton_mode_BANG_.call(null);
} else {
cljs.core.swap_BANG_.call(null,proton.core.current_chain,cljs.core.conj,letter);

if(cljs.core.truth_(proton.lib.helpers.is_action_QMARK_.call(null,cljs.core.deref.call(null,proton.core.command_tree),cljs.core.deref.call(null,proton.core.current_chain)))){
return proton.lib.atom.eval_action_BANG_.call(null,cljs.core.deref.call(null,proton.core.command_tree),cljs.core.deref.call(null,proton.core.current_chain));
} else {
var extracted_chain = cljs.core.get_in.call(null,cljs.core.deref.call(null,proton.core.command_tree),cljs.core.deref.call(null,proton.core.current_chain));
if((extracted_chain == null)){
return proton.lib.atom.deactivate_proton_mode_BANG_.call(null);
} else {
return proton.lib.atom.insert_html.call(null,proton.lib.helpers.tree__GT_html.call(null,extracted_chain));
}
}
}
});
goog.exportSymbol('proton.core.chain', proton.core.chain);
proton.core.on_space = (function proton$core$on_space(){
cljs.core.reset_BANG_.call(null,proton.core.current_chain,cljs.core.PersistentVector.EMPTY);

proton.lib.atom.insert_html.call(null,proton.lib.helpers.tree__GT_html.call(null,cljs.core.deref.call(null,proton.core.command_tree)));

return proton.lib.atom.activate_proton_mode_BANG_.call(null);
});
proton.core.activate = (function proton$core$activate(state){
proton.core.keymaps.onDidMatchBinding((function (p1__8548_SHARP_){
if(cljs.core._EQ_.call(null,"space",p1__8548_SHARP_.keystrokes)){
return proton.core.on_space.call(null);
} else {
return null;
}
}));

return proton.core.subscriptions.add(proton.core.commands.add("atom-text-editor.proton-mode","proton:chain",proton.core.chain));
});
goog.exportSymbol('proton.core.activate', proton.core.activate);
proton.core.deactivate = (function proton$core$deactivate(){
return console.log("deactivating...");
});
goog.exportSymbol('proton.core.deactivate', proton.core.deactivate);
proton.core.noop = (function proton$core$noop(){
return null;
});
cljs.core._STAR_main_cli_fn_STAR_ = proton.core.noop;
(module["exports"] = proton.core);
