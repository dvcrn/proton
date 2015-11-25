// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__6900__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__6900 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__6901__i = 0, G__6901__a = new Array(arguments.length -  0);
while (G__6901__i < G__6901__a.length) {G__6901__a[G__6901__i] = arguments[G__6901__i + 0]; ++G__6901__i;}
  args = new cljs.core.IndexedSeq(G__6901__a,0);
} 
return G__6900__delegate.call(this,args);};
G__6900.cljs$lang$maxFixedArity = 0;
G__6900.cljs$lang$applyTo = (function (arglist__6902){
var args = cljs.core.seq(arglist__6902);
return G__6900__delegate(args);
});
G__6900.cljs$core$IFn$_invoke$arity$variadic = G__6900__delegate;
return G__6900;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__6903__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__6903 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__6904__i = 0, G__6904__a = new Array(arguments.length -  0);
while (G__6904__i < G__6904__a.length) {G__6904__a[G__6904__i] = arguments[G__6904__i + 0]; ++G__6904__i;}
  args = new cljs.core.IndexedSeq(G__6904__a,0);
} 
return G__6903__delegate.call(this,args);};
G__6903.cljs$lang$maxFixedArity = 0;
G__6903.cljs$lang$applyTo = (function (arglist__6905){
var args = cljs.core.seq(arglist__6905);
return G__6903__delegate(args);
});
G__6903.cljs$core$IFn$_invoke$arity$variadic = G__6903__delegate;
return G__6903;
})()
;

return null;
});
