// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__16064__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__16064 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__16065__i = 0, G__16065__a = new Array(arguments.length -  0);
while (G__16065__i < G__16065__a.length) {G__16065__a[G__16065__i] = arguments[G__16065__i + 0]; ++G__16065__i;}
  args = new cljs.core.IndexedSeq(G__16065__a,0);
} 
return G__16064__delegate.call(this,args);};
G__16064.cljs$lang$maxFixedArity = 0;
G__16064.cljs$lang$applyTo = (function (arglist__16066){
var args = cljs.core.seq(arglist__16066);
return G__16064__delegate(args);
});
G__16064.cljs$core$IFn$_invoke$arity$variadic = G__16064__delegate;
return G__16064;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__16067__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__16067 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__16068__i = 0, G__16068__a = new Array(arguments.length -  0);
while (G__16068__i < G__16068__a.length) {G__16068__a[G__16068__i] = arguments[G__16068__i + 0]; ++G__16068__i;}
  args = new cljs.core.IndexedSeq(G__16068__a,0);
} 
return G__16067__delegate.call(this,args);};
G__16067.cljs$lang$maxFixedArity = 0;
G__16067.cljs$lang$applyTo = (function (arglist__16069){
var args = cljs.core.seq(arglist__16069);
return G__16067__delegate(args);
});
G__16067.cljs$core$IFn$_invoke$arity$variadic = G__16067__delegate;
return G__16067;
})()
;

return null;
});
