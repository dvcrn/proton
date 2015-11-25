// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('proton.lib.helpers');
goog.require('cljs.core');
goog.require('clojure.string');
proton.lib.helpers.generate_div = (function proton$lib$helpers$generate_div(text){
var d = document.createElement("div");
d.textContent = text;

return d;
});
proton.lib.helpers.extract_keyletter_from_event = (function proton$lib$helpers$extract_keyletter_from_event(event){
var key = String.fromCharCode(event.originalEvent.keyCode);
var shift_key = event.originalEvent.shiftKey;
if(cljs.core.truth_(shift_key)){
return cljs.core.keyword.call(null,clojure.string.upper_case.call(null,key));
} else {
return cljs.core.keyword.call(null,clojure.string.lower_case.call(null,key));
}
});
proton.lib.helpers.extract_keycode_from_event = (function proton$lib$helpers$extract_keycode_from_event(event){
return event.originalEvent.keyCode;
});
proton.lib.helpers.is_action_QMARK_ = (function proton$lib$helpers$is_action_QMARK_(tree,sequence){
cljs.core.println.call(null,"is action?");

cljs.core.println.call(null,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024)));

cljs.core.println.call(null,cljs.core.get_in.call(null,tree,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024))));

return !((cljs.core.get_in.call(null,tree,cljs.core.conj.call(null,sequence,new cljs.core.Keyword(null,"action","action",-811238024))) == null));
});
proton.lib.helpers.tree__GT_html = (function proton$lib$helpers$tree__GT_html(tree){
return clojure.string.join.call(null," ",cljs.core.map.call(null,(function (element){
var key = cljs.core.nth.call(null,element,(0));
var options = cljs.core.nth.call(null,element,(1));
var value = (((options.call(null,new cljs.core.Keyword(null,"category","category",-593092832)) == null))?[cljs.core.str("action:"),cljs.core.str(options.call(null,new cljs.core.Keyword(null,"action","action",-811238024)))].join(''):[cljs.core.str("category:"),cljs.core.str(options.call(null,new cljs.core.Keyword(null,"category","category",-593092832)))].join(''));
return [cljs.core.str("<li>"),cljs.core.str(key),cljs.core.str(" --> "),cljs.core.str(value),cljs.core.str("</li>")].join('');
}),cljs.core.seq.call(null,cljs.core.dissoc.call(null,tree,new cljs.core.Keyword(null,"category","category",-593092832)))));
});
