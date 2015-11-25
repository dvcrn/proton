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
proton.lib.atom.element = cljs.core.atom.call(null,proton.lib.helpers.generate_div.call(null,"test"));
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
var seq__17626 = cljs.core.seq.call(null,editors);
var chunk__17627 = null;
var count__17628 = (0);
var i__17629 = (0);
while(true){
if((i__17629 < count__17628)){
var editor = cljs.core._nth.call(null,chunk__17627,i__17629);
var view_17630 = proton.lib.atom.views.getView(editor);
var classList_17631 = view_17630.classList;
classList_17631.remove("vim-mode");

classList_17631.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__17632 = seq__17626;
var G__17633 = chunk__17627;
var G__17634 = count__17628;
var G__17635 = (i__17629 + (1));
seq__17626 = G__17632;
chunk__17627 = G__17633;
count__17628 = G__17634;
i__17629 = G__17635;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__17626);
if(temp__4425__auto__){
var seq__17626__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__17626__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__17626__$1);
var G__17636 = cljs.core.chunk_rest.call(null,seq__17626__$1);
var G__17637 = c__5471__auto__;
var G__17638 = cljs.core.count.call(null,c__5471__auto__);
var G__17639 = (0);
seq__17626 = G__17636;
chunk__17627 = G__17637;
count__17628 = G__17638;
i__17629 = G__17639;
continue;
} else {
var editor = cljs.core.first.call(null,seq__17626__$1);
var view_17640 = proton.lib.atom.views.getView(editor);
var classList_17641 = view_17640.classList;
classList_17641.remove("vim-mode");

classList_17641.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__17642 = cljs.core.next.call(null,seq__17626__$1);
var G__17643 = null;
var G__17644 = (0);
var G__17645 = (0);
seq__17626 = G__17642;
chunk__17627 = G__17643;
count__17628 = G__17644;
i__17629 = G__17645;
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
var seq__17650 = cljs.core.seq.call(null,editors);
var chunk__17651 = null;
var count__17652 = (0);
var i__17653 = (0);
while(true){
if((i__17653 < count__17652)){
var editor = cljs.core._nth.call(null,chunk__17651,i__17653);
var view_17654 = proton.lib.atom.views.getView(editor);
var classList_17655 = view_17654.classList;
classList_17655.remove("proton-mode");

classList_17655.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__17656 = seq__17650;
var G__17657 = chunk__17651;
var G__17658 = count__17652;
var G__17659 = (i__17653 + (1));
seq__17650 = G__17656;
chunk__17651 = G__17657;
count__17652 = G__17658;
i__17653 = G__17659;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__17650);
if(temp__4425__auto__){
var seq__17650__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__17650__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__17650__$1);
var G__17660 = cljs.core.chunk_rest.call(null,seq__17650__$1);
var G__17661 = c__5471__auto__;
var G__17662 = cljs.core.count.call(null,c__5471__auto__);
var G__17663 = (0);
seq__17650 = G__17660;
chunk__17651 = G__17661;
count__17652 = G__17662;
i__17653 = G__17663;
continue;
} else {
var editor = cljs.core.first.call(null,seq__17650__$1);
var view_17664 = proton.lib.atom.views.getView(editor);
var classList_17665 = view_17664.classList;
classList_17665.remove("proton-mode");

classList_17665.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__17666 = cljs.core.next.call(null,seq__17650__$1);
var G__17667 = null;
var G__17668 = (0);
var G__17669 = (0);
seq__17650 = G__17666;
chunk__17651 = G__17667;
count__17652 = G__17668;
i__17653 = G__17669;
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
