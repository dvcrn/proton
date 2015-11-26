(ns proton.lib.helpers
  (:require [clojure.string :as string :refer [upper-case lower-case]]))

(defn generate-div [text class-name]
  (let [d (.createElement js/document "div")]
    (set! (.-textContent d) text)
    (.add (.-classList d) class-name)
    d))

(defn extract-keyletter-from-event [event]
  (let [key (.fromCharCode js/String (.. event -originalEvent -keyCode))
        shift-key (.. event -originalEvent -shiftKey)]
      (if shift-key
        (keyword (upper-case key))
        (keyword (lower-case key)))))

(defn extract-keycode-from-event [event]
  (.. event -originalEvent -keyCode))

(defn is-action? [tree sequence]
  (println "is action?")
  (println (conj sequence :action))
  (println (get-in tree (conj sequence :action)))
  (not (nil? (get-in tree (conj sequence :action)))))

(defn tree->html [tree]
  (->>
    (map (fn [element]
          (let [key (nth element 0)
                options (nth element 1)
                value (if (nil? (options :category))
                          (options :action)
                          (options :category))]

            (str "<li class='flex-item'>[" (name key) "] ➜ " value "</li>")))
      (seq (dissoc tree :category)))
    (string/join " ")
    (conj [])
    (apply #(str "<p>Keybindings:</p><ul class='flex-container'>" % "</ul>"))))
