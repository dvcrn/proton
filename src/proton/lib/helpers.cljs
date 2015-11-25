(ns proton.lib.helpers
  (:require [clojure.string :as string :refer [upper-case lower-case]]))

(defn generate-div [text]
  (let [d (.createElement js/document "div")]
    (set! (.-textContent d) text)
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
                          (str "action:" (options :action))
                          (str "category:" (options :category)))]

            (str "<li>" key " --> " value "</li>")))
      (seq (dissoc tree :category)))
    (string/join " ")))
