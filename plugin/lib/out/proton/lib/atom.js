// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.lib.atom');
goog.require('cljs.core');
goog.require('proton.lib.helpers');
goog.require('cljs.nodejs');
goog.require('clojure.string');
proton.lib.atom.commands = atom.commands;
proton.lib.atom.workspace = atom.workspace;
proton.lib.atom.keymaps = atom.keymaps;
proton.lib.atom.views = atom.views;
proton.lib.atom.element = cljs.core.atom.call(null,proton.lib.helpers.generate_div.call(null,"test","proton-which-key"));
proton.lib.atom.modal_panel = cljs.core.atom.call(null,proton.lib.atom.workspace.addBottomPanel(cljs.core.clj__GT_js.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"visible","visible",-1024216805),false,new cljs.core.Keyword(null,"item","item",249373802),cljs.core.deref.call(null,proton.lib.atom.element)], null))));
proton.lib.atom.insert_html = (function proton$lib$atom$insert_html(html){
return (cljs.core.deref.call(null,proton.lib.atom.element)["innerHTML"] = html);
});
proton.lib.atom.show_panel = (function proton$lib$atom$show_panel(){
return cljs.core.deref.call(null,proton.lib.atom.modal_panel).show();
});
proton.lib.atom.hide_panel = (function proton$lib$atom$hide_panel(){
return cljs.core.deref.call(null,proton.lib.atom.modal_panel).hide();
});
proton.lib.atom.activate_proton_mode_BANG_ = (function proton$lib$atom$activate_proton_mode_BANG_(){
console.log("----> Proton Chain activated!");

var editors = proton.lib.atom.workspace.getTextEditors();
var seq__8473 = cljs.core.seq.call(null,editors);
var chunk__8474 = null;
var count__8475 = (0);
var i__8476 = (0);
while(true){
if((i__8476 < count__8475)){
var editor = cljs.core._nth.call(null,chunk__8474,i__8476);
var view_8477 = proton.lib.atom.views.getView(editor);
var classList_8478 = view_8477.classList;
classList_8478.remove("vim-mode");

classList_8478.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__8479 = seq__8473;
var G__8480 = chunk__8474;
var G__8481 = count__8475;
var G__8482 = (i__8476 + (1));
seq__8473 = G__8479;
chunk__8474 = G__8480;
count__8475 = G__8481;
i__8476 = G__8482;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__8473);
if(temp__4425__auto__){
var seq__8473__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8473__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__8473__$1);
var G__8483 = cljs.core.chunk_rest.call(null,seq__8473__$1);
var G__8484 = c__5471__auto__;
var G__8485 = cljs.core.count.call(null,c__5471__auto__);
var G__8486 = (0);
seq__8473 = G__8483;
chunk__8474 = G__8484;
count__8475 = G__8485;
i__8476 = G__8486;
continue;
} else {
var editor = cljs.core.first.call(null,seq__8473__$1);
var view_8487 = proton.lib.atom.views.getView(editor);
var classList_8488 = view_8487.classList;
classList_8488.remove("vim-mode");

classList_8488.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__8489 = cljs.core.next.call(null,seq__8473__$1);
var G__8490 = null;
var G__8491 = (0);
var G__8492 = (0);
seq__8473 = G__8489;
chunk__8474 = G__8490;
count__8475 = G__8491;
i__8476 = G__8492;
continue;
}
} else {
return null;
}
}
break;
}
});
proton.lib.atom.deactivate_proton_mode_BANG_ = (function proton$lib$atom$deactivate_proton_mode_BANG_(){
console.log("----> Proton Chain deactivated!");

var editors = proton.lib.atom.workspace.getTextEditors();
var seq__8497 = cljs.core.seq.call(null,editors);
var chunk__8498 = null;
var count__8499 = (0);
var i__8500 = (0);
while(true){
if((i__8500 < count__8499)){
var editor = cljs.core._nth.call(null,chunk__8498,i__8500);
var view_8501 = proton.lib.atom.views.getView(editor);
var classList_8502 = view_8501.classList;
classList_8502.remove("proton-mode");

classList_8502.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__8503 = seq__8497;
var G__8504 = chunk__8498;
var G__8505 = count__8499;
var G__8506 = (i__8500 + (1));
seq__8497 = G__8503;
chunk__8498 = G__8504;
count__8499 = G__8505;
i__8500 = G__8506;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__8497);
if(temp__4425__auto__){
var seq__8497__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8497__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__8497__$1);
var G__8507 = cljs.core.chunk_rest.call(null,seq__8497__$1);
var G__8508 = c__5471__auto__;
var G__8509 = cljs.core.count.call(null,c__5471__auto__);
var G__8510 = (0);
seq__8497 = G__8507;
chunk__8498 = G__8508;
count__8499 = G__8509;
i__8500 = G__8510;
continue;
} else {
var editor = cljs.core.first.call(null,seq__8497__$1);
var view_8511 = proton.lib.atom.views.getView(editor);
var classList_8512 = view_8511.classList;
classList_8512.remove("proton-mode");

classList_8512.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__8513 = cljs.core.next.call(null,seq__8497__$1);
var G__8514 = null;
var G__8515 = (0);
var G__8516 = (0);
seq__8497 = G__8513;
chunk__8498 = G__8514;
count__8499 = G__8515;
i__8500 = G__8516;
continue;
}
} else {
return null;
}
}
break;
}
});
proton.lib.atom.eval_action_BANG_ = (function proton$lib$atom$eval_action_BANG_(tree,sequence){
cljs.core.println.call(null,"evalling");

var action = cljs.core.get_in.call(null,tree,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024)));
proton.lib.atom.commands.dispatch(proton.lib.atom.views.getView(proton.lib.atom.workspace),action);

return proton.lib.atom.deactivate_proton_mode_BANG_.call(null);
});
