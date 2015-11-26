// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.core');
goog.require('cljs.core');
goog.require('proton.layers.base');
goog.require('proton.lib.helpers');
goog.require('proton.layers.git.core');
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
return proton.lib.atom.update_bottom_panel.call(null,proton.lib.helpers.tree__GT_html.call(null,extracted_chain));
}
}
}
});
goog.exportSymbol('proton.core.chain', proton.core.chain);
proton.core.enabled_layers = new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"core","core",-86019209),new cljs.core.Keyword(null,"git","git",-163493751)], null);
proton.core.init_layers = (function proton$core$init_layers(){
proton.lib.atom.insert_process_step.call(null,[cljs.core.str("Initialising layers: "),cljs.core.str(proton.core.enabled_layers)].join(''));

cljs.core.println.call(null,[cljs.core.str("Initialising layers: "),cljs.core.str(proton.core.enabled_layers)].join(''));

var seq__7573_7581 = cljs.core.seq.call(null,proton.core.enabled_layers);
var chunk__7574_7582 = null;
var count__7575_7583 = (0);
var i__7576_7584 = (0);
while(true){
if((i__7576_7584 < count__7575_7583)){
var layer_7585 = cljs.core._nth.call(null,chunk__7574_7582,i__7576_7584);
cljs.core.println.call(null,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_7585)));

cljs.core.swap_BANG_.call(null,proton.core.required_packages,((function (seq__7573_7581,chunk__7574_7582,count__7575_7583,i__7576_7584,layer_7585){
return (function (p1__7564_SHARP_){
return cljs.core.into.call(null,cljs.core.PersistentVector.EMPTY,cljs.core.concat.call(null,p1__7564_SHARP_,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_7585))));
});})(seq__7573_7581,chunk__7574_7582,count__7575_7583,i__7576_7584,layer_7585))
);

cljs.core.swap_BANG_.call(null,proton.core.command_tree,cljs.core.merge,proton.layers.base.get_keybindings.call(null,cljs.core.keyword.call(null,layer_7585)));

var G__7586 = seq__7573_7581;
var G__7587 = chunk__7574_7582;
var G__7588 = count__7575_7583;
var G__7589 = (i__7576_7584 + (1));
seq__7573_7581 = G__7586;
chunk__7574_7582 = G__7587;
count__7575_7583 = G__7588;
i__7576_7584 = G__7589;
continue;
} else {
var temp__4425__auto___7590 = cljs.core.seq.call(null,seq__7573_7581);
if(temp__4425__auto___7590){
var seq__7573_7591__$1 = temp__4425__auto___7590;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7573_7591__$1)){
var c__5471__auto___7592 = cljs.core.chunk_first.call(null,seq__7573_7591__$1);
var G__7593 = cljs.core.chunk_rest.call(null,seq__7573_7591__$1);
var G__7594 = c__5471__auto___7592;
var G__7595 = cljs.core.count.call(null,c__5471__auto___7592);
var G__7596 = (0);
seq__7573_7581 = G__7593;
chunk__7574_7582 = G__7594;
count__7575_7583 = G__7595;
i__7576_7584 = G__7596;
continue;
} else {
var layer_7597 = cljs.core.first.call(null,seq__7573_7591__$1);
cljs.core.println.call(null,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_7597)));

cljs.core.swap_BANG_.call(null,proton.core.required_packages,((function (seq__7573_7581,chunk__7574_7582,count__7575_7583,i__7576_7584,layer_7597,seq__7573_7591__$1,temp__4425__auto___7590){
return (function (p1__7564_SHARP_){
return cljs.core.into.call(null,cljs.core.PersistentVector.EMPTY,cljs.core.concat.call(null,p1__7564_SHARP_,proton.layers.base.get_packages.call(null,cljs.core.keyword.call(null,layer_7597))));
});})(seq__7573_7581,chunk__7574_7582,count__7575_7583,i__7576_7584,layer_7597,seq__7573_7591__$1,temp__4425__auto___7590))
);

cljs.core.swap_BANG_.call(null,proton.core.command_tree,cljs.core.merge,proton.layers.base.get_keybindings.call(null,cljs.core.keyword.call(null,layer_7597)));

var G__7598 = cljs.core.next.call(null,seq__7573_7591__$1);
var G__7599 = null;
var G__7600 = (0);
var G__7601 = (0);
seq__7573_7581 = G__7598;
chunk__7574_7582 = G__7599;
count__7575_7583 = G__7600;
i__7576_7584 = G__7601;
continue;
}
} else {
}
}
break;
}

cljs.core.println.call(null,[cljs.core.str("Collected packages: "),cljs.core.str(cljs.core.deref.call(null,proton.core.required_packages))].join(''));

var seq__7577_7602 = cljs.core.seq.call(null,cljs.core.deref.call(null,proton.core.required_packages));
var chunk__7578_7603 = null;
var count__7579_7604 = (0);
var i__7580_7605 = (0);
while(true){
if((i__7580_7605 < count__7579_7604)){
var package_7606 = cljs.core._nth.call(null,chunk__7578_7603,i__7580_7605);
var package_7607__$1 = cljs.core.name.call(null,package_7606);
if(cljs.core.not.call(null,proton.lib.package_manager.is_installed_QMARK_.call(null,package_7607__$1))){
proton.lib.atom.insert_process_step.call(null,[cljs.core.str("Installing "),cljs.core.str(package_7607__$1)].join(''));

cljs.core.println.call(null,[cljs.core.str("Installing "),cljs.core.str(package_7607__$1)].join(''));

proton.lib.package_manager.install_package.call(null,package_7607__$1);
} else {
}

var G__7608 = seq__7577_7602;
var G__7609 = chunk__7578_7603;
var G__7610 = count__7579_7604;
var G__7611 = (i__7580_7605 + (1));
seq__7577_7602 = G__7608;
chunk__7578_7603 = G__7609;
count__7579_7604 = G__7610;
i__7580_7605 = G__7611;
continue;
} else {
var temp__4425__auto___7612 = cljs.core.seq.call(null,seq__7577_7602);
if(temp__4425__auto___7612){
var seq__7577_7613__$1 = temp__4425__auto___7612;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7577_7613__$1)){
var c__5471__auto___7614 = cljs.core.chunk_first.call(null,seq__7577_7613__$1);
var G__7615 = cljs.core.chunk_rest.call(null,seq__7577_7613__$1);
var G__7616 = c__5471__auto___7614;
var G__7617 = cljs.core.count.call(null,c__5471__auto___7614);
var G__7618 = (0);
seq__7577_7602 = G__7615;
chunk__7578_7603 = G__7616;
count__7579_7604 = G__7617;
i__7580_7605 = G__7618;
continue;
} else {
var package_7619 = cljs.core.first.call(null,seq__7577_7613__$1);
var package_7620__$1 = cljs.core.name.call(null,package_7619);
if(cljs.core.not.call(null,proton.lib.package_manager.is_installed_QMARK_.call(null,package_7620__$1))){
proton.lib.atom.insert_process_step.call(null,[cljs.core.str("Installing "),cljs.core.str(package_7620__$1)].join(''));

cljs.core.println.call(null,[cljs.core.str("Installing "),cljs.core.str(package_7620__$1)].join(''));

proton.lib.package_manager.install_package.call(null,package_7620__$1);
} else {
}

var G__7621 = cljs.core.next.call(null,seq__7577_7613__$1);
var G__7622 = null;
var G__7623 = (0);
var G__7624 = (0);
seq__7577_7602 = G__7621;
chunk__7578_7603 = G__7622;
count__7579_7604 = G__7623;
i__7580_7605 = G__7624;
continue;
}
} else {
}
}
break;
}

return true;
});
proton.core.on_space = (function proton$core$on_space(){
cljs.core.reset_BANG_.call(null,proton.core.current_chain,cljs.core.PersistentVector.EMPTY);

proton.lib.atom.update_bottom_panel.call(null,proton.lib.helpers.tree__GT_html.call(null,cljs.core.deref.call(null,proton.core.command_tree)));

return proton.lib.atom.activate_proton_mode_BANG_.call(null);
});
proton.core.activate = (function proton$core$activate(state){
window.setTimeout((function (){
return proton.core.init_layers.call(null);
}),(10000));

proton.core.keymaps.onDidMatchBinding((function (p1__7625_SHARP_){
if(cljs.core._EQ_.call(null,"space",p1__7625_SHARP_.keystrokes)){
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
