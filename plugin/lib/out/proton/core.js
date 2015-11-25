// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.core');
goog.require('cljs.core');
goog.require('cljs.nodejs');
goog.require('clojure.string');
proton.core.ashell = cljs.nodejs.require.call(null,"atom");
proton.core.commands = atom.commands;
proton.core.workspace = atom.workspace;
proton.core.keymaps = atom.keymaps;
proton.core.views = atom.views;
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
proton.core.element = cljs.core.atom.call(null,proton.core.div.call(null,"test"));
proton.core.modal_panel = cljs.core.atom.call(null,proton.core.workspace.addBottomPanel(cljs.core.clj__GT_js.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"visible","visible",-1024216805),false,new cljs.core.Keyword(null,"item","item",249373802),cljs.core.deref.call(null,proton.core.element)], null))));
proton.core.mock_tree = new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"g","g",1738089905),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"category","category",-593092832),"git",new cljs.core.Keyword(null,"s","s",1705939918),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_status"], null),new cljs.core.Keyword(null,"c","c",-1763192079),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_commit"], null),new cljs.core.Keyword(null,"p","p",151049309),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_push"], null),new cljs.core.Keyword(null,"P","P",1668913291),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_pull"], null)], null),new cljs.core.Keyword(null,"w","w",354169001),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"window",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null),new cljs.core.Keyword(null,"b","b",1482224470),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"buffer",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null),new cljs.core.Keyword(null,"p","p",151049309),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"project",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null)], null);
proton.core.current_chain = cljs.core.atom.call(null,cljs.core.PersistentVector.EMPTY);
proton.core.make_pretty = (function proton$core$make_pretty(tree){
console.log("Making pretty:");

cljs.core.println.call(null,tree);

return clojure.string.join.call(null," ",cljs.core.map.call(null,(function (element){
var key = cljs.core.nth.call(null,element,(0));
var options = cljs.core.nth.call(null,element,(1));
var value = (((options.call(null,new cljs.core.Keyword(null,"category","category",-593092832)) == null))?[cljs.core.str("action:"),cljs.core.str(options.call(null,new cljs.core.Keyword(null,"action","action",-811238024)))].join(''):[cljs.core.str("category:"),cljs.core.str(options.call(null,new cljs.core.Keyword(null,"category","category",-593092832)))].join(''));
return [cljs.core.str("<li>"),cljs.core.str(key),cljs.core.str(" --> "),cljs.core.str(value),cljs.core.str("</li>")].join('');
}),cljs.core.seq.call(null,cljs.core.dissoc.call(null,tree,new cljs.core.Keyword(null,"category","category",-593092832)))));
});
proton.core.extract_keyletter_from_event = (function proton$core$extract_keyletter_from_event(event){
var key = String.fromCharCode(event.originalEvent.keyCode);
var shift_key = event.originalEvent.shiftKey;
if(cljs.core.truth_(shift_key)){
return cljs.core.keyword.call(null,clojure.string.upper_case.call(null,key));
} else {
return cljs.core.keyword.call(null,clojure.string.lower_case.call(null,key));
}
});
proton.core.extract_keycode_from_event = (function proton$core$extract_keycode_from_event(event){
return event.originalEvent.keyCode;
});
proton.core.activate_proton = (function proton$core$activate_proton(){
console.log("----> Proton Chain activated!");

var editors = proton.core.workspace.getTextEditors();
var seq__12585 = cljs.core.seq.call(null,editors);
var chunk__12586 = null;
var count__12587 = (0);
var i__12588 = (0);
while(true){
if((i__12588 < count__12587)){
var editor = cljs.core._nth.call(null,chunk__12586,i__12588);
var view_12589 = proton.core.views.getView(editor);
var classList_12590 = view_12589.classList;
classList_12590.remove("vim-mode");

classList_12590.add("proton-mode");

(cljs.core.deref.call(null,proton.core.element)["innerHTML"] = proton.core.make_pretty.call(null,cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.PersistentVector.EMPTY)));

cljs.core.deref.call(null,proton.core.modal_panel).show();

cljs.core.reset_BANG_.call(null,proton.core.current_chain,cljs.core.PersistentVector.EMPTY);

var G__12591 = seq__12585;
var G__12592 = chunk__12586;
var G__12593 = count__12587;
var G__12594 = (i__12588 + (1));
seq__12585 = G__12591;
chunk__12586 = G__12592;
count__12587 = G__12593;
i__12588 = G__12594;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__12585);
if(temp__4425__auto__){
var seq__12585__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12585__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__12585__$1);
var G__12595 = cljs.core.chunk_rest.call(null,seq__12585__$1);
var G__12596 = c__5471__auto__;
var G__12597 = cljs.core.count.call(null,c__5471__auto__);
var G__12598 = (0);
seq__12585 = G__12595;
chunk__12586 = G__12596;
count__12587 = G__12597;
i__12588 = G__12598;
continue;
} else {
var editor = cljs.core.first.call(null,seq__12585__$1);
var view_12599 = proton.core.views.getView(editor);
var classList_12600 = view_12599.classList;
classList_12600.remove("vim-mode");

classList_12600.add("proton-mode");

(cljs.core.deref.call(null,proton.core.element)["innerHTML"] = proton.core.make_pretty.call(null,cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.PersistentVector.EMPTY)));

cljs.core.deref.call(null,proton.core.modal_panel).show();

cljs.core.reset_BANG_.call(null,proton.core.current_chain,cljs.core.PersistentVector.EMPTY);

var G__12601 = cljs.core.next.call(null,seq__12585__$1);
var G__12602 = null;
var G__12603 = (0);
var G__12604 = (0);
seq__12585 = G__12601;
chunk__12586 = G__12602;
count__12587 = G__12603;
i__12588 = G__12604;
continue;
}
} else {
return null;
}
}
break;
}
});
proton.core.deactivate_proton = (function proton$core$deactivate_proton(){
console.log("----> Proton Chain deactivated!");

var editors = proton.core.workspace.getTextEditors();
var seq__12609 = cljs.core.seq.call(null,editors);
var chunk__12610 = null;
var count__12611 = (0);
var i__12612 = (0);
while(true){
if((i__12612 < count__12611)){
var editor = cljs.core._nth.call(null,chunk__12610,i__12612);
var view_12613 = proton.core.views.getView(editor);
var classList_12614 = view_12613.classList;
classList_12614.remove("proton-mode");

classList_12614.add("vim-mode");

cljs.core.deref.call(null,proton.core.modal_panel).hide();

var G__12615 = seq__12609;
var G__12616 = chunk__12610;
var G__12617 = count__12611;
var G__12618 = (i__12612 + (1));
seq__12609 = G__12615;
chunk__12610 = G__12616;
count__12611 = G__12617;
i__12612 = G__12618;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__12609);
if(temp__4425__auto__){
var seq__12609__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12609__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__12609__$1);
var G__12619 = cljs.core.chunk_rest.call(null,seq__12609__$1);
var G__12620 = c__5471__auto__;
var G__12621 = cljs.core.count.call(null,c__5471__auto__);
var G__12622 = (0);
seq__12609 = G__12619;
chunk__12610 = G__12620;
count__12611 = G__12621;
i__12612 = G__12622;
continue;
} else {
var editor = cljs.core.first.call(null,seq__12609__$1);
var view_12623 = proton.core.views.getView(editor);
var classList_12624 = view_12623.classList;
classList_12624.remove("proton-mode");

classList_12624.add("vim-mode");

cljs.core.deref.call(null,proton.core.modal_panel).hide();

var G__12625 = cljs.core.next.call(null,seq__12609__$1);
var G__12626 = null;
var G__12627 = (0);
var G__12628 = (0);
seq__12609 = G__12625;
chunk__12610 = G__12626;
count__12611 = G__12627;
i__12612 = G__12628;
continue;
}
} else {
return null;
}
}
break;
}
});
proton.core.chain = (function proton$core$chain(e){
var letter = proton.core.extract_keyletter_from_event.call(null,e);
var key_code = proton.core.extract_keycode_from_event.call(null,e);
if(cljs.core._EQ_.call(null,key_code,(27))){
return proton.core.deactivate_proton.call(null);
} else {
var element = cljs.core.deref.call(null,proton.core.element);
cljs.core.swap_BANG_.call(null,proton.core.current_chain,cljs.core.conj,letter);

var extracted_chain = cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain));
cljs.core.println.call(null,extracted_chain);

if((extracted_chain == null)){
return proton.core.deactivate_proton.call(null);
} else {
return (element["innerHTML"] = proton.core.make_pretty.call(null,extracted_chain));
}
}
});
goog.exportSymbol('proton.core.chain', proton.core.chain);
proton.core.activate = (function proton$core$activate(state){
proton.core.keymaps.onDidMatchBinding((function (p1__12629_SHARP_){
if(cljs.core._EQ_.call(null,"space",p1__12629_SHARP_.keystrokes)){
return proton.core.activate_proton.call(null);
} else {
return null;
}
}));

return proton.core.subscriptions.add(proton.core.commands.add("atom-text-editor.proton-mode","proton:chain",proton.core.chain));
});
goog.exportSymbol('proton.core.activate', proton.core.activate);
proton.core.noop = (function proton$core$noop(){
return null;
});
cljs.core._STAR_main_cli_fn_STAR_ = proton.core.noop;
(module["exports"] = proton.core);
