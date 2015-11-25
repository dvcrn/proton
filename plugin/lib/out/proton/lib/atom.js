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
var seq__6963 = cljs.core.seq.call(null,editors);
var chunk__6964 = null;
var count__6965 = (0);
var i__6966 = (0);
while(true){
if((i__6966 < count__6965)){
var editor = cljs.core._nth.call(null,chunk__6964,i__6966);
var view_6967 = proton.lib.atom.views.getView(editor);
var classList_6968 = view_6967.classList;
classList_6968.remove("vim-mode");

classList_6968.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__6969 = seq__6963;
var G__6970 = chunk__6964;
var G__6971 = count__6965;
var G__6972 = (i__6966 + (1));
seq__6963 = G__6969;
chunk__6964 = G__6970;
count__6965 = G__6971;
i__6966 = G__6972;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__6963);
if(temp__4425__auto__){
var seq__6963__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__6963__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__6963__$1);
var G__6973 = cljs.core.chunk_rest.call(null,seq__6963__$1);
var G__6974 = c__5471__auto__;
var G__6975 = cljs.core.count.call(null,c__5471__auto__);
var G__6976 = (0);
seq__6963 = G__6973;
chunk__6964 = G__6974;
count__6965 = G__6975;
i__6966 = G__6976;
continue;
} else {
var editor = cljs.core.first.call(null,seq__6963__$1);
var view_6977 = proton.lib.atom.views.getView(editor);
var classList_6978 = view_6977.classList;
classList_6978.remove("vim-mode");

classList_6978.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__6979 = cljs.core.next.call(null,seq__6963__$1);
var G__6980 = null;
var G__6981 = (0);
var G__6982 = (0);
seq__6963 = G__6979;
chunk__6964 = G__6980;
count__6965 = G__6981;
i__6966 = G__6982;
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
var seq__6987 = cljs.core.seq.call(null,editors);
var chunk__6988 = null;
var count__6989 = (0);
var i__6990 = (0);
while(true){
if((i__6990 < count__6989)){
var editor = cljs.core._nth.call(null,chunk__6988,i__6990);
var view_6991 = proton.lib.atom.views.getView(editor);
var classList_6992 = view_6991.classList;
classList_6992.remove("proton-mode");

classList_6992.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__6993 = seq__6987;
var G__6994 = chunk__6988;
var G__6995 = count__6989;
var G__6996 = (i__6990 + (1));
seq__6987 = G__6993;
chunk__6988 = G__6994;
count__6989 = G__6995;
i__6990 = G__6996;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__6987);
if(temp__4425__auto__){
var seq__6987__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__6987__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__6987__$1);
var G__6997 = cljs.core.chunk_rest.call(null,seq__6987__$1);
var G__6998 = c__5471__auto__;
var G__6999 = cljs.core.count.call(null,c__5471__auto__);
var G__7000 = (0);
seq__6987 = G__6997;
chunk__6988 = G__6998;
count__6989 = G__6999;
i__6990 = G__7000;
continue;
} else {
var editor = cljs.core.first.call(null,seq__6987__$1);
var view_7001 = proton.lib.atom.views.getView(editor);
var classList_7002 = view_7001.classList;
classList_7002.remove("proton-mode");

classList_7002.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__7003 = cljs.core.next.call(null,seq__6987__$1);
var G__7004 = null;
var G__7005 = (0);
var G__7006 = (0);
seq__6987 = G__7003;
chunk__6988 = G__7004;
count__6989 = G__7005;
i__6990 = G__7006;
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
