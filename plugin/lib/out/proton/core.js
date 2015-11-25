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
proton.core.mock_tree = new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"g","g",1738089905),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"category","category",-593092832),"git",new cljs.core.Keyword(null,"s","s",1705939918),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_status"], null),new cljs.core.Keyword(null,"c","c",-1763192079),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_commit"], null),new cljs.core.Keyword(null,"p","p",151049309),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_push"], null),new cljs.core.Keyword(null,"P","P",1668913291),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"git_pull"], null)], null),new cljs.core.Keyword(null,"w","w",354169001),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"window",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null),new cljs.core.Keyword(null,"b","b",1482224470),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"buffer",new cljs.core.Keyword(null,"m","m",1632677161),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"maximise"], null)], null),new cljs.core.Keyword(null,"p","p",151049309),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"project",new cljs.core.Keyword(null,"t","t",-1397832519),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"action","action",-811238024),"tree-view:toggle"], null)], null)], null);
proton.core.is_action_QMARK_ = (function proton$core$is_action_QMARK_(tree,sequence){
cljs.core.println.call(null,"is action?");

cljs.core.println.call(null,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024)));

cljs.core.println.call(null,cljs.core.get_in.call(null,tree,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024))));

return !((cljs.core.get_in.call(null,tree,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024))) == null));
});
proton.core.current_chain = cljs.core.atom.call(null,cljs.core.PersistentVector.EMPTY);
proton.core.make_pretty = (function proton$core$make_pretty(tree){
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
var seq__13916 = cljs.core.seq.call(null,editors);
var chunk__13917 = null;
var count__13918 = (0);
var i__13919 = (0);
while(true){
if((i__13919 < count__13918)){
var editor = cljs.core._nth.call(null,chunk__13917,i__13919);
var view_13920 = proton.core.views.getView(editor);
var classList_13921 = view_13920.classList;
classList_13921.remove("vim-mode");

classList_13921.add("proton-mode");

(cljs.core.deref.call(null,proton.core.element)["innerHTML"] = proton.core.make_pretty.call(null,cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.PersistentVector.EMPTY)));

cljs.core.deref.call(null,proton.core.modal_panel).show();

cljs.core.reset_BANG_.call(null,proton.core.current_chain,cljs.core.PersistentVector.EMPTY);

var G__13922 = seq__13916;
var G__13923 = chunk__13917;
var G__13924 = count__13918;
var G__13925 = (i__13919 + (1));
seq__13916 = G__13922;
chunk__13917 = G__13923;
count__13918 = G__13924;
i__13919 = G__13925;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__13916);
if(temp__4425__auto__){
var seq__13916__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__13916__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__13916__$1);
var G__13926 = cljs.core.chunk_rest.call(null,seq__13916__$1);
var G__13927 = c__5471__auto__;
var G__13928 = cljs.core.count.call(null,c__5471__auto__);
var G__13929 = (0);
seq__13916 = G__13926;
chunk__13917 = G__13927;
count__13918 = G__13928;
i__13919 = G__13929;
continue;
} else {
var editor = cljs.core.first.call(null,seq__13916__$1);
var view_13930 = proton.core.views.getView(editor);
var classList_13931 = view_13930.classList;
classList_13931.remove("vim-mode");

classList_13931.add("proton-mode");

(cljs.core.deref.call(null,proton.core.element)["innerHTML"] = proton.core.make_pretty.call(null,cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.PersistentVector.EMPTY)));

cljs.core.deref.call(null,proton.core.modal_panel).show();

cljs.core.reset_BANG_.call(null,proton.core.current_chain,cljs.core.PersistentVector.EMPTY);

var G__13932 = cljs.core.next.call(null,seq__13916__$1);
var G__13933 = null;
var G__13934 = (0);
var G__13935 = (0);
seq__13916 = G__13932;
chunk__13917 = G__13933;
count__13918 = G__13934;
i__13919 = G__13935;
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
var seq__13940 = cljs.core.seq.call(null,editors);
var chunk__13941 = null;
var count__13942 = (0);
var i__13943 = (0);
while(true){
if((i__13943 < count__13942)){
var editor = cljs.core._nth.call(null,chunk__13941,i__13943);
var view_13944 = proton.core.views.getView(editor);
var classList_13945 = view_13944.classList;
classList_13945.remove("proton-mode");

classList_13945.add("vim-mode");

cljs.core.deref.call(null,proton.core.modal_panel).hide();

var G__13946 = seq__13940;
var G__13947 = chunk__13941;
var G__13948 = count__13942;
var G__13949 = (i__13943 + (1));
seq__13940 = G__13946;
chunk__13941 = G__13947;
count__13942 = G__13948;
i__13943 = G__13949;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__13940);
if(temp__4425__auto__){
var seq__13940__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__13940__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__13940__$1);
var G__13950 = cljs.core.chunk_rest.call(null,seq__13940__$1);
var G__13951 = c__5471__auto__;
var G__13952 = cljs.core.count.call(null,c__5471__auto__);
var G__13953 = (0);
seq__13940 = G__13950;
chunk__13941 = G__13951;
count__13942 = G__13952;
i__13943 = G__13953;
continue;
} else {
var editor = cljs.core.first.call(null,seq__13940__$1);
var view_13954 = proton.core.views.getView(editor);
var classList_13955 = view_13954.classList;
classList_13955.remove("proton-mode");

classList_13955.add("vim-mode");

cljs.core.deref.call(null,proton.core.modal_panel).hide();

var G__13956 = cljs.core.next.call(null,seq__13940__$1);
var G__13957 = null;
var G__13958 = (0);
var G__13959 = (0);
seq__13940 = G__13956;
chunk__13941 = G__13957;
count__13942 = G__13958;
i__13943 = G__13959;
continue;
}
} else {
return null;
}
}
break;
}
});
proton.core.eval_action_BANG_ = (function proton$core$eval_action_BANG_(tree,sequence){
cljs.core.println.call(null,"evalling");

var action = cljs.core.get_in.call(null,tree,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024)));
proton.core.commands.dispatch(proton.core.views.getView(proton.core.workspace),action);

return proton.core.deactivate_proton.call(null);
});
proton.core.chain = (function proton$core$chain(e){
var letter = proton.core.extract_keyletter_from_event.call(null,e);
var key_code = proton.core.extract_keycode_from_event.call(null,e);
if(cljs.core._EQ_.call(null,key_code,(27))){
return proton.core.deactivate_proton.call(null);
} else {
var element = cljs.core.deref.call(null,proton.core.element);
cljs.core.swap_BANG_.call(null,proton.core.current_chain,cljs.core.conj,letter);

cljs.core.println.call(null,"current chain:");

cljs.core.println.call(null,cljs.core.deref.call(null,proton.core.current_chain));

if(cljs.core.truth_(proton.core.is_action_QMARK_.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain)))){
return proton.core.eval_action_BANG_.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain));
} else {
var extracted_chain = cljs.core.get_in.call(null,proton.core.mock_tree,cljs.core.deref.call(null,proton.core.current_chain));
cljs.core.println.call(null,"not an action");

cljs.core.println.call(null,extracted_chain);

if((extracted_chain == null)){
return proton.core.deactivate_proton.call(null);
} else {
return (element["innerHTML"] = proton.core.make_pretty.call(null,extracted_chain));
}
}
}
});
goog.exportSymbol('proton.core.chain', proton.core.chain);
proton.core.activate = (function proton$core$activate(state){
proton.core.keymaps.onDidMatchBinding((function (p1__13960_SHARP_){
if(cljs.core._EQ_.call(null,"space",p1__13960_SHARP_.keystrokes)){
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
