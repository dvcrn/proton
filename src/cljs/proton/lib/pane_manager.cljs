(ns proton.lib.pane_manager)

(defn get-panes []
  "Returns list of panes."
  (.getPanes js/atom.workspace))

(defn get-panels
  "Returns list of panels by location.
   Possible locations: left, right, top, bottom, modal"
  [location]
  (seq (.getPanels js/atom.workspace location)))

(defn focus-on-panel [panel]
  (if-let [item (.getItem panel)]
    (.focus item)))

(defn focus-on-pane [pane]
  (when-not (nil? pane)
    (.activate pane)))

(defn destroy-pane [pane]
  (when-not (nil? pane)
    (.destroy pane)))

(defn close-other-panes []
  (let [panes (get-panes)
        filtered-panes (filter #(not (.-focused %)) panes)]
    (doall (map destroy-pane filtered-panes))))

(defn focus-on-item [n]
  (let [panels-left (get-panels "left")
        panels-left-count (count panels-left)
        panels-right (get-panels "right")
        panels-right-count (count panels-right)
        panes (get-panes)
        panes-count (count panes)]
      (cond (> panels-left-count n) (focus-on-panel (nth panels-left n))
            (> (+ panels-left-count panes-count) n) (focus-on-pane (nth panes (- n panels-left-count)))
            (> (+ panels-right-count panels-left-count panes-count) n) (focus-on-panel (nth panels-right (- n (+ panes-count panels-left-count)))))))
