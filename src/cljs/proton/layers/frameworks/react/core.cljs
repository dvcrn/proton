(ns proton.layers.frameworks.react.core
  (:use [proton.layers.base :only [init-layer! register-layer-dependencies]]))

(defmethod init-layer! :frameworks/react []
  (register-layer-dependencies :lang/javascript [:react :react-snippets]))
