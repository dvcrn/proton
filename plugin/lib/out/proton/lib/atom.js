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
var seq__16076 = cljs.core.seq.call(null,editors);
var chunk__16077 = null;
var count__16078 = (0);
var i__16079 = (0);
while(true){
if((i__16079 < count__16078)){
var editor = cljs.core._nth.call(null,chunk__16077,i__16079);
var view_16080 = proton.lib.atom.views.getView(editor);
var classList_16081 = view_16080.classList;
classList_16081.remove("vim-mode");

classList_16081.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__16082 = seq__16076;
var G__16083 = chunk__16077;
var G__16084 = count__16078;
var G__16085 = (i__16079 + (1));
seq__16076 = G__16082;
chunk__16077 = G__16083;
count__16078 = G__16084;
i__16079 = G__16085;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__16076);
if(temp__4425__auto__){
var seq__16076__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__16076__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__16076__$1);
var G__16086 = cljs.core.chunk_rest.call(null,seq__16076__$1);
var G__16087 = c__5471__auto__;
var G__16088 = cljs.core.count.call(null,c__5471__auto__);
var G__16089 = (0);
seq__16076 = G__16086;
chunk__16077 = G__16087;
count__16078 = G__16088;
i__16079 = G__16089;
continue;
} else {
var editor = cljs.core.first.call(null,seq__16076__$1);
var view_16090 = proton.lib.atom.views.getView(editor);
var classList_16091 = view_16090.classList;
classList_16091.remove("vim-mode");

classList_16091.add("proton-mode");

proton.lib.atom.show_panel.call(null);

var G__16092 = cljs.core.next.call(null,seq__16076__$1);
var G__16093 = null;
var G__16094 = (0);
var G__16095 = (0);
seq__16076 = G__16092;
chunk__16077 = G__16093;
count__16078 = G__16094;
i__16079 = G__16095;
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
var seq__16100 = cljs.core.seq.call(null,editors);
var chunk__16101 = null;
var count__16102 = (0);
var i__16103 = (0);
while(true){
if((i__16103 < count__16102)){
var editor = cljs.core._nth.call(null,chunk__16101,i__16103);
var view_16104 = proton.lib.atom.views.getView(editor);
var classList_16105 = view_16104.classList;
classList_16105.remove("proton-mode");

classList_16105.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__16106 = seq__16100;
var G__16107 = chunk__16101;
var G__16108 = count__16102;
var G__16109 = (i__16103 + (1));
seq__16100 = G__16106;
chunk__16101 = G__16107;
count__16102 = G__16108;
i__16103 = G__16109;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__16100);
if(temp__4425__auto__){
var seq__16100__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__16100__$1)){
var c__5471__auto__ = cljs.core.chunk_first.call(null,seq__16100__$1);
var G__16110 = cljs.core.chunk_rest.call(null,seq__16100__$1);
var G__16111 = c__5471__auto__;
var G__16112 = cljs.core.count.call(null,c__5471__auto__);
var G__16113 = (0);
seq__16100 = G__16110;
chunk__16101 = G__16111;
count__16102 = G__16112;
i__16103 = G__16113;
continue;
} else {
var editor = cljs.core.first.call(null,seq__16100__$1);
var view_16114 = proton.lib.atom.views.getView(editor);
var classList_16115 = view_16114.classList;
classList_16115.remove("proton-mode");

classList_16115.add("vim-mode");

proton.lib.atom.hide_panel.call(null);

var G__16116 = cljs.core.next.call(null,seq__16100__$1);
var G__16117 = null;
var G__16118 = (0);
var G__16119 = (0);
seq__16100 = G__16116;
chunk__16101 = G__16117;
count__16102 = G__16118;
i__16103 = G__16119;
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
