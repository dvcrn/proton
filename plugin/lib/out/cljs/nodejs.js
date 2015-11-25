// Compiled by ClojureScript 1.7.170 {:target :nodejs}
goog.provide('cljs.nodejs');
goog.require('cljs.core');
cljs.nodejs.require = require;
cljs.nodejs.process = process;
cljs.nodejs.enable_util_print_BANG_ = (function cljs$nodejs$enable_util_print_BANG_(){
cljs.core._STAR_print_newline_STAR_ = false;

cljs.core._STAR_print_fn_STAR_ = (function() { 
var G__17614__delegate = function (args){
return console.log.apply(console,cljs.core.into_array.call(null,args));
};
var G__17614 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__17615__i = 0, G__17615__a = new Array(arguments.length -  0);
while (G__17615__i < G__17615__a.length) {G__17615__a[G__17615__i] = arguments[G__17615__i + 0]; ++G__17615__i;}
  args = new cljs.core.IndexedSeq(G__17615__a,0);
} 
return G__17614__delegate.call(this,args);};
G__17614.cljs$lang$maxFixedArity = 0;
G__17614.cljs$lang$applyTo = (function (arglist__17616){
var args = cljs.core.seq(arglist__17616);
return G__17614__delegate(args);
});
G__17614.cljs$core$IFn$_invoke$arity$variadic = G__17614__delegate;
return G__17614;
})()
;

cljs.core._STAR_print_err_fn_STAR_ = (function() { 
var G__17617__delegate = function (args){
return console.error.apply(console,cljs.core.into_array.call(null,args));
};
var G__17617 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__17618__i = 0, G__17618__a = new Array(arguments.length -  0);
while (G__17618__i < G__17618__a.length) {G__17618__a[G__17618__i] = arguments[G__17618__i + 0]; ++G__17618__i;}
  args = new cljs.core.IndexedSeq(G__17618__a,0);
} 
return G__17617__delegate.call(this,args);};
G__17617.cljs$lang$maxFixedArity = 0;
G__17617.cljs$lang$applyTo = (function (arglist__17619){
var args = cljs.core.seq(arglist__17619);
return G__17617__delegate(args);
});
G__17617.cljs$core$IFn$_invoke$arity$variadic = G__17617__delegate;
return G__17617;
})()
;

return null;
});
