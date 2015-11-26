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
proton.lib.atom.bottom_panel = cljs.core.atom.call(null,proton.lib.atom.workspace.addBottomPanel(cljs.core.clj__GT_js.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"visible","visible",-1024216805),false,new cljs.core.Keyword(null,"item","item",249373802),cljs.core.deref.call(null,proton.lib.atom.element)], null))));
proton.lib.atom.modal_element = cljs.core.atom.call(null,proton.lib.helpers.generate_div.call(null,"test","proton-modal-panel"));
proton.lib.atom.modal_panel = cljs.core.atom.call(null,proton.lib.atom.workspace.addModalPanel(cljs.core.clj__GT_js.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"visible","visible",-1024216805),false,new cljs.core.Keyword(null,"item","item",249373802),cljs.core.deref.call(null,proton.lib.atom.modal_element)], null))));
proton.lib.atom.update_bottom_panel = (function proton$lib$atom$update_bottom_panel(html){
return (cljs.core.deref.call(null,proton.lib.atom.element)["innerHTML"] = html);
});
proton.lib.atom.update_modal_panel = (function proton$lib$atom$update_modal_panel(html){
return (cljs.core.deref.call(null,proton.lib.atom.modal_element)["innerHTML"] = html);
});
proton.lib.atom.show_modal_panel = (function proton$lib$atom$show_modal_panel(){
return cljs.core.deref.call(null,proton.lib.atom.modal_panel).show();
});
proton.lib.atom.hide_modal_panel = (function proton$lib$atom$hide_modal_panel(){
return cljs.core.deref.call(null,proton.lib.atom.modal_panel).hide();
});
proton.lib.atom.show_bottom_panel = (function proton$lib$atom$show_bottom_panel(){
return cljs.core.deref.call(null,proton.lib.atom.bottom_panel).show();
});
proton.lib.atom.hide_bottom_panel = (function proton$lib$atom$hide_bottom_panel(){
return cljs.core.deref.call(null,proton.lib.atom.bottom_panel).hide();
});
proton.lib.atom.steps = cljs.core.atom.call(null,cljs.core.PersistentVector.EMPTY);
proton.lib.atom.insert_process_step = (function proton$lib$atom$insert_process_step(text){
cljs.core.swap_BANG_.call(null,proton.lib.atom.steps,cljs.core.conj,text);

return proton.lib.atom.update_modal_panel.call(null,proton.lib.helpers.process__GT_html.call(null,cljs.core.deref.call(null,proton.lib.atom.steps)));
});
proton.lib.atom.activate_proton_mode_BANG_ = (function proton$lib$atom$activate_proton_mode_BANG_(){
console.log("----> Proton Chain activated!");

var editors = proton.lib.atom.workspace.getTextEditors();
var seq__7437 = cljs.core.seq.call(null,editors);
var chunk__7438 = null;
var count__7439 = (0);
var i__7440 = (0);
while(true){
if((i__7440 < count__7439)){
var editor = cljs.core._nth.call(null,chunk__7438,i__7440);
var view_7441 = proton.lib.atom.views.getView(editor);
var classList_7442 = view_7441.classList;
classList_7442.remove("vim-mode");

classList_7442.add("proton-mode");

proton.lib.atom.show_bottom_panel.call(null);

var G__7443 = seq__7437;
var G__7444 = chunk__7438;
var G__7445 = count__7439;
var G__7446 = (i__7440 + (1));
seq__7437 = G__7443;
chunk__7438 = G__7444;
count__7439 = G__7445;
i__7440 = G__7446;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__7437);
if(temp__4425__auto__){
var seq__7437__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7437__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__7437__$1);
var G__7447 = cljs.core.chunk_rest.call(null,seq__7437__$1);
var G__7448 = c__5471__auto__;
var G__7449 = cljs.core.count.call(null,c__5471__auto__);
var G__7450 = (0);
seq__7437 = G__7447;
chunk__7438 = G__7448;
count__7439 = G__7449;
i__7440 = G__7450;
continue;
} else {
var editor = cljs.core.first.call(null,seq__7437__$1);
var view_7451 = proton.lib.atom.views.getView(editor);
var classList_7452 = view_7451.classList;
classList_7452.remove("vim-mode");

classList_7452.add("proton-mode");

proton.lib.atom.show_bottom_panel.call(null);

var G__7453 = cljs.core.next.call(null,seq__7437__$1);
var G__7454 = null;
var G__7455 = (0);
var G__7456 = (0);
seq__7437 = G__7453;
chunk__7438 = G__7454;
count__7439 = G__7455;
i__7440 = G__7456;
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
var seq__7461 = cljs.core.seq.call(null,editors);
var chunk__7462 = null;
var count__7463 = (0);
var i__7464 = (0);
while(true){
if((i__7464 < count__7463)){
var editor = cljs.core._nth.call(null,chunk__7462,i__7464);
var view_7465 = proton.lib.atom.views.getView(editor);
var classList_7466 = view_7465.classList;
classList_7466.remove("proton-mode");

classList_7466.add("vim-mode");

proton.lib.atom.hide_bottom_panel.call(null);

var G__7467 = seq__7461;
var G__7468 = chunk__7462;
var G__7469 = count__7463;
var G__7470 = (i__7464 + (1));
seq__7461 = G__7467;
chunk__7462 = G__7468;
count__7463 = G__7469;
i__7464 = G__7470;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__7461);
if(temp__4425__auto__){
var seq__7461__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7461__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__7461__$1);
var G__7471 = cljs.core.chunk_rest.call(null,seq__7461__$1);
var G__7472 = c__5471__auto__;
var G__7473 = cljs.core.count.call(null,c__5471__auto__);
var G__7474 = (0);
seq__7461 = G__7471;
chunk__7462 = G__7472;
count__7463 = G__7473;
i__7464 = G__7474;
continue;
} else {
var editor = cljs.core.first.call(null,seq__7461__$1);
var view_7475 = proton.lib.atom.views.getView(editor);
var classList_7476 = view_7475.classList;
classList_7476.remove("proton-mode");

classList_7476.add("vim-mode");

proton.lib.atom.hide_bottom_panel.call(null);

var G__7477 = cljs.core.next.call(null,seq__7461__$1);
var G__7478 = null;
var G__7479 = (0);
var G__7480 = (0);
seq__7461 = G__7477;
chunk__7462 = G__7478;
count__7463 = G__7479;
i__7464 = G__7480;
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
