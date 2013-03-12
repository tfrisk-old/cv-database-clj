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
    [:div.row
     [:div.content-area
      [:div.column
       [:h3 "User details" ]
       [:p "Name " firstname " " lastname]
       [:p "Birthday " birthdate]
       [:p "Description " description]
      ]
      [:div.clear] ;this ensures our styling stays on the whole column
     ]])

;partial for showing company data
(defpartial show-company-fields [{:keys [name title startdate stopdate description]}]
    [:div.row
     [:div.content-area
      [:div.column
       [:h3 "Company details" ]
       [:p "Name " name " " name]
       [:p "Title " title]
       [:p "Start date " startdate]
       [:p "Stop date " stopdate]
       [:p "Description " description]
      ]
      [:div.clear] ;this ensures our styling stays on the whole column
     ]])

;partial for showing education data
(defpartial show-education-fields [{:keys [keeper name startdate stopdate description]}]
    [:div.row
     [:div.content-area
      [:div.column
       [:h3 "Education details" ]
       [:p "Education provider " keeper]
       [:p "Name " name]
       [:p "Start date " startdate]
       [:p "Stop date " stopdate]
       [:p "Description " description]
      ]
      [:div.clear] ;this ensures our styling stays on the whole column
     ]])

;partial for editing user data
(defpartial edit-person-fields [{:keys [firstname lastname birthdate description]}]
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
     ]])

(defpage "/user/:id" {:keys [id]}
  (layout
    (edit-person-fields (cv.data/find-person-by-id id))))

(defpage "/company/:id" {:keys [id]}
  (layout
    (show-company-fields (cv.data/find-company-by-id id))))

(defpage "/education/:id" {:keys [id]}
  (layout
    (show-education-fields (cv.data/find-education-by-id id))))

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
