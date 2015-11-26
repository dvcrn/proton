// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__8461__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__8461 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8462__i = 0, G__8462__a = new Array(arguments.length -  0);
while (G__8462__i < G__8462__a.length) {G__8462__a[G__8462__i] = arguments[G__8462__i + 0]; ++G__8462__i;}
  args = new cljs.core.IndexedSeq(G__8462__a,0);
} 
return G__8461__delegate.call(this,args);};
G__8461.cljs$lang$maxFixedArity = 0;
G__8461.cljs$lang$applyTo = (function (arglist__8463){
var args = cljs.core.seq(arglist__8463);
return G__8461__delegate(args);
});
G__8461.cljs$core$IFn$_invoke$arity$variadic = G__8461__delegate;
return G__8461;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__8464__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__8464 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8465__i = 0, G__8465__a = new Array(arguments.length -  0);
while (G__8465__i < G__8465__a.length) {G__8465__a[G__8465__i] = arguments[G__8465__i + 0]; ++G__8465__i;}
  args = new cljs.core.IndexedSeq(G__8465__a,0);
} 
return G__8464__delegate.call(this,args);};
G__8464.cljs$lang$maxFixedArity = 0;
G__8464.cljs$lang$applyTo = (function (arglist__8466){
var args = cljs.core.seq(arglist__8466);
return G__8464__delegate(args);
});
G__8464.cljs$core$IFn$_invoke$arity$variadic = G__8464__delegate;
return G__8464;
})()
;

return null;
});
