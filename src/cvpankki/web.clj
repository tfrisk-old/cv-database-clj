(ns cvpankki.web
  (:use noir.core
        hiccup.core)
  (:require [cvpankki.datastructures :as cv.data])
  (:require [noir.server :as server]))

(defpartial person-fields [{:keys [firstname lastname birthdate description]}]
  [:h2 firstname " " lastname]
  [:h3 birthdate]
  [:h3 description])

(defpage "/user/:id" {:keys [id]}
  (person-fields (cv.data/find-person-by-id id)))

(defpage "/" []
  "hello")

;(server/start 8080)
