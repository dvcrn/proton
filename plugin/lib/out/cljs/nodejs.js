// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__13842__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__13842 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__13843__i = 0, G__13843__a = new Array(arguments.length -  0);
while (G__13843__i < G__13843__a.length) {G__13843__a[G__13843__i] = arguments[G__13843__i + 0]; ++G__13843__i;}
  args = new cljs.core.IndexedSeq(G__13843__a,0);
} 
return G__13842__delegate.call(this,args);};
G__13842.cljs$lang$maxFixedArity = 0;
G__13842.cljs$lang$applyTo = (function (arglist__13844){
var args = cljs.core.seq(arglist__13844);
return G__13842__delegate(args);
});
G__13842.cljs$core$IFn$_invoke$arity$variadic = G__13842__delegate;
return G__13842;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__13845__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__13845 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__13846__i = 0, G__13846__a = new Array(arguments.length -  0);
while (G__13846__i < G__13846__a.length) {G__13846__a[G__13846__i] = arguments[G__13846__i + 0]; ++G__13846__i;}
  args = new cljs.core.IndexedSeq(G__13846__a,0);
} 
return G__13845__delegate.call(this,args);};
G__13845.cljs$lang$maxFixedArity = 0;
G__13845.cljs$lang$applyTo = (function (arglist__13847){
var args = cljs.core.seq(arglist__13847);
return G__13845__delegate(args);
});
G__13845.cljs$core$IFn$_invoke$arity$variadic = G__13845__delegate;
return G__13845;
})()
;

return null;
});
