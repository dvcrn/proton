// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__7425__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__7425 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__7426__i = 0, G__7426__a = new Array(arguments.length -  0);
while (G__7426__i < G__7426__a.length) {G__7426__a[G__7426__i] = arguments[G__7426__i + 0]; ++G__7426__i;}
  args = new cljs.core.IndexedSeq(G__7426__a,0);
} 
return G__7425__delegate.call(this,args);};
G__7425.cljs$lang$maxFixedArity = 0;
G__7425.cljs$lang$applyTo = (function (arglist__7427){
var args = cljs.core.seq(arglist__7427);
return G__7425__delegate(args);
});
G__7425.cljs$core$IFn$_invoke$arity$variadic = G__7425__delegate;
return G__7425;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__7428__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__7428 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__7429__i = 0, G__7429__a = new Array(arguments.length -  0);
while (G__7429__i < G__7429__a.length) {G__7429__a[G__7429__i] = arguments[G__7429__i + 0]; ++G__7429__i;}
  args = new cljs.core.IndexedSeq(G__7429__a,0);
} 
return G__7428__delegate.call(this,args);};
G__7428.cljs$lang$maxFixedArity = 0;
G__7428.cljs$lang$applyTo = (function (arglist__7430){
var args = cljs.core.seq(arglist__7430);
return G__7428__delegate(args);
});
G__7428.cljs$core$IFn$_invoke$arity$variadic = G__7428__delegate;
return G__7428;
})()
;

return null;
});
