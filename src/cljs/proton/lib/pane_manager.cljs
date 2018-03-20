(ns proton.lib.pane_manager)

(defn get-panes []
  "Returns list of panes."
  (.getPanes js/atom.workspace))

(defn pane-type [pane]
  "Get the type of a pane (eg TextEditor, TreeView)"
  (goog.object/getValueByKeys pane "activeItem" "constructor" "name"))

(defn pane-is-tree-view [pane]
  "Returns true if the pane is a treeview"
  (= (pane-type pane) "TreeView"))

(defn pane-is-not-tree-view [pane]
  "Returns true if the pane is not a treeview"
  (let [type (pane-type pane)]
    (and
      (not (= type "TreeView"))
      (not (nil? type)))))

(defn get-tree-view-panes []
  "Return all panes that are not treeviews"
  (filter pane-is-tree-view (get-panes)))

(defn get-non-tree-view-panes []
  "Return all panes that are not treeviews"
  (filter pane-is-not-tree-view (get-panes)))

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

(defn focus-on-tree-view []
  (if-let [tree (nth (get-tree-view-panes) 0 nil)]
    (focus-on-pane tree)))

(defn destroy-pane [pane]
  (when-not (nil? pane)
    (.destroy pane)))

(defn close-other-panes []
  (let [panes (get-panes)
        filtered-panes (filter #(not (.-focused %)) panes)]
    (doall (map destroy-pane filtered-panes))))

(defn focus-on-item [n]
  (if-let [pane (nth (get-non-tree-view-panes) n nil)]
    (focus-on-pane pane)
    (focus-on-tree-view)))
