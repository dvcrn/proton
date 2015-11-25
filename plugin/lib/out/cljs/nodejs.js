// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__6951__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__6951 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__6952__i = 0, G__6952__a = new Array(arguments.length -  0);
while (G__6952__i < G__6952__a.length) {G__6952__a[G__6952__i] = arguments[G__6952__i + 0]; ++G__6952__i;}
  args = new cljs.core.IndexedSeq(G__6952__a,0);
} 
return G__6951__delegate.call(this,args);};
G__6951.cljs$lang$maxFixedArity = 0;
G__6951.cljs$lang$applyTo = (function (arglist__6953){
var args = cljs.core.seq(arglist__6953);
return G__6951__delegate(args);
});
G__6951.cljs$core$IFn$_invoke$arity$variadic = G__6951__delegate;
return G__6951;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__6954__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__6954 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__6955__i = 0, G__6955__a = new Array(arguments.length -  0);
while (G__6955__i < G__6955__a.length) {G__6955__a[G__6955__i] = arguments[G__6955__i + 0]; ++G__6955__i;}
  args = new cljs.core.IndexedSeq(G__6955__a,0);
} 
return G__6954__delegate.call(this,args);};
G__6954.cljs$lang$maxFixedArity = 0;
G__6954.cljs$lang$applyTo = (function (arglist__6956){
var args = cljs.core.seq(arglist__6956);
return G__6954__delegate(args);
});
G__6954.cljs$core$IFn$_invoke$arity$variadic = G__6954__delegate;
return G__6954;
})()
;

return null;
});
