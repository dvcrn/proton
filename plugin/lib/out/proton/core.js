// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.core');
goog.require('cljs.core');
goog.require('proton.lib.helpers');
goog.require('proton.lib.atom');
goog.require('cljs.nodejs');
goog.require('clojure.string');
cljs.nodejs.enable_util_print_BANG_.call(null);
proton.core.ashell = cljs.nodejs.require.call(null,"atom");
proton.core.commands = atom.commands;
proton.core.workspace = atom.workspace;
proton.core.keymaps = atom.keymaps;
proton.core.views = atom.views;
proton.core.composite_disposable = proton.core.ashell.CompositeDisposable;
proton.core.subscriptions = (new proton.core.composite_disposable());
proton.core.mock_tree = new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"g","g",1738089905),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"category","category",-593092832),"git",new cljs.core.Keyword(null,"s","s",1705939918),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_status"], null),new cljs.core.Keyword(null,"c","c",-1763192079),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_commit"], null),new cljs.core.Keyword(null,"p","p",151049309),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_push"], null),new cljs.core.Keyword(null,"P","P",1668913291),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_pull"], null)], null),new cljs.core.Keyword(null,"w","w",354169001),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"window",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null),new cljs.core.Keyword(null,"b","b",1482224470),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"buffer",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null),new cljs.core.Keyword(null,"p","p",151049309),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"project",new cljs.core.Keyword(null,"t","t",-1397832519),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"tree-view:toggle"], null)], null)], null);
proton.core.current_chain = cljs.core.atom.call(null,cljs.core.PersistentVector.EMPTY);
proton.core.chain = (function proton$core$chain(e){
var letter = proton.lib.helpers.extract_keyletter_from_event.call(null,e);
var key_code = proton.lib.helpers.extract_keycode_from_event.call(null,e);
if(cljs.core._EQ_.call(null,key_code,(27))){
return proton.lib.atom.deactivate_proton_mode_BANG_.call(null);
} else {
cljs.core.swap_BANG_.call(null,proton.core.current_chain,cljs.core.conj,letter);

if(cljs.core.truth_(proton.lib.helpers.is_action_QMARK_.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain)))){
return proton.lib.atom.eval_action_BANG_.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain));
} else {
var extracted_chain = cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain));
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

proton.lib.atom.insert_html.call(null,proton.lib.helpers.tree__GT_html.call(null,proton.core.mock_tree));

return proton.lib.atom.activate_proton_mode_BANG_.call(null);
});
proton.core.activate = (function proton$core$activate(state){
proton.core.keymaps.onDidMatchBinding((function (p1__16122_SHARP_){
if(cljs.core._EQ_.call(null,"space",p1__16122_SHARP_.keystrokes)){
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
