(ns cvpankki.web
  (:use noir.core
        hiccup.core
        hiccup.page-helpers)
  (:require [cvpankki.datastructures :as cv.data])
  (:require [noir.server :as server]))

(defpartial layout [& content]
  (html5
    [:head
     [:title "cvpankki"]
     (include-css "/css/cvpankki.css")]
    [:body content]))

;partial for showing user data
(defpartial show-person-fields [{:keys [firstname lastname birthdate description]}]
  (layout
    [:div.row
     [:div.content-area
      [:div.column
       [:h3 "User details" ]
       [:p "Name " firstname " " lastname]
       [:p "Birthday " birthdate]
       [:p "Description " description]
      ]
      [:div.clear] ;this ensures our styling stays on the whole column
     ]]))

(defpage "/user/:id" {:keys [id]}
  (show-person-fields (cv.data/find-person-by-id id)))

(defpage "/" []
  "hello")

;(server/start 8080)
