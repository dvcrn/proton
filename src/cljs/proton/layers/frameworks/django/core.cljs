(ns proton.layers.frameworks.django.core
  (:use [proton.layers.base :only [init-layer! register-layer-dependencies]]))

(defmethod init-layer! :frameworks/django []
  (register-layer-dependencies :lang/python [:atom-django]))
