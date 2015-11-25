// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__12511__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__12511 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__12512__i = 0, G__12512__a = new Array(arguments.length -  0);
while (G__12512__i < G__12512__a.length) {G__12512__a[G__12512__i] = arguments[G__12512__i + 0]; ++G__12512__i;}
  args = new cljs.core.IndexedSeq(G__12512__a,0);
} 
return G__12511__delegate.call(this,args);};
G__12511.cljs$lang$maxFixedArity = 0;
G__12511.cljs$lang$applyTo = (function (arglist__12513){
var args = cljs.core.seq(arglist__12513);
return G__12511__delegate(args);
});
G__12511.cljs$core$IFn$_invoke$arity$variadic = G__12511__delegate;
return G__12511;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__12514__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__12514 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__12515__i = 0, G__12515__a = new Array(arguments.length -  0);
while (G__12515__i < G__12515__a.length) {G__12515__a[G__12515__i] = arguments[G__12515__i + 0]; ++G__12515__i;}
  args = new cljs.core.IndexedSeq(G__12515__a,0);
} 
return G__12514__delegate.call(this,args);};
G__12514.cljs$lang$maxFixedArity = 0;
G__12514.cljs$lang$applyTo = (function (arglist__12516){
var args = cljs.core.seq(arglist__12516);
return G__12514__delegate(args);
});
G__12514.cljs$core$IFn$_invoke$arity$variadic = G__12514__delegate;
return G__12514;
})()
;

return null;
});
