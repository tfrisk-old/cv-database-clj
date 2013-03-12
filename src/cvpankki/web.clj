(ns cvpankki.web
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers)
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

;partial for showing user data
(defpartial edit-person-fields [{:keys [firstname lastname birthdate description]}]
  (layout
    [:div.row
     [:div.content-area
      [:div.column
       [:h3 "User details" ]
       (form-to [:post "/user"]
	       [:p (label "firstname" "First name")
	       (text-field "firstname" firstname) ]
	       [:p (label "lastname" "Last name")
	       (text-field "lastname" lastname) ]
	       [:p (label "birthdate " "Birth date")
	       (text-field "birthdate" birthdate) ]
	       [:p (label "description " "Description")
	       (text-field "description" description) ]
        [:p (submit-button "Save user") ])
      ]
      [:div.clear] ;this ensures our styling stays on the whole column
     ]]))

(defpage "/user/:id" {:keys [id]}
  (edit-person-fields (cv.data/find-person-by-id id)))

(defpage [:post "/user"] {:as user}
  (layout
    [:div.row
     [:div.content-area.notice
      [:div.column
       [:p "User saved"]]
       [:div.clear]]]
    (render "/user/:id" user)))

(defpage "/" []
  "hello")

;(server/start 8080)
