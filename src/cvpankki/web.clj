(ns cvpankki.web
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes app
  (GET "/" [] "<h1>Hello World!</h1>")
  (route/not-found "<h1>Page not found.</h1>")
  (GET "/user/:id" [id] (str "<h1>Hello user " id "</h1>"))
  (GET "/company/:id" [id] (str "<h1>Hello company " id "</h1>"))
  (GET "/education/:id" [id] (str "<h1>Hello education " id "</h1>"))
  (GET "/cv/:id" [id] (str "<h1>Hello cv " id "</h1>"))
  )

(defn layout [& content]
  (html5
    [:head
     [:title "cvpankki"]
     (include-css "/css/cvpankki.css")]
    [:body content]))

(defn show-skill-item [{:keys [label category]}]
  [:li.skill 
   [:p label "("category")"]])

(defn show-skills-list [skills-list]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "Skills" ]
    [:ul
     (map show-skill-item skills-list)]]
  [:div.clear]
  ]])

;partial for showing user data
(defn show-person-fields [{:keys [firstname lastname birthdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "User details" ]
   [:p "Name " firstname " " lastname]
   [:p "Birthday " birthdate]
   [:p "Description " description]]
  [:div.clear]]])

;partial for showing company data
(defn show-company-fields [{:keys [companyname title startdate stopdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "Company details" ]
   [:p "Company name " companyname]
   [:p "Title " title]
   [:p "Start date " startdate]
   [:p "Stop date " stopdate]
   [:p "Description " description]]
  [:div.clear]]])

;partial for showing education data
(defn show-education-fields [{:keys [provider coursename startdate stopdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "Education details" ]
   [:p "Education provider " provider]
   [:p "Course name " coursename]
   [:p "Start date " startdate]
   [:p "Stop date " stopdate]
   [:p "Description " description]]
  [:div.clear]]])

;partial for editing user data
(defn edit-person-fields [{:keys [firstname lastname birthdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "User details" ]
   (form-to [:post "/user"]
    [:p (label "firstname" "First name")
    (text-field "firstname" firstname) ]
    [:p (label "lastname" "Last name")
    (text-field "lastname" lastname) ]
    [:p (label "birthdate " "Birth date")
    (text-field "birthdate" birthdate) ]
    [:p (label "description" "Description")
    (text-field "description" description) ]
    [:p (submit-button "Save user") ]
   )][:div.clear]]])

;partial for editing company data
(defn edit-company-fields [{:keys [companyname title startdate stopdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "Company details" ]
   (form-to [:post "/company"]
    [:p (label "companyname" "Company name")
    (text-field "companyname" companyname) ]
    [:p (label "title" "Title")
    (text-field "title" title ) ]
    [:p (label "startdate" "Start date")
    (text-field "startdate" startdate ) ]
    [:p (label "stopdate" "Stop date")
    (text-field "stopdate" stopdate ) ]
    [:p (label "description" "Description")
    (text-field "description" description) ]
    [:p (submit-button "Save company") ]
  )][:div.clear]]])

;partial for editing education data
(defn edit-education-fields [{:keys [provider coursename startdate stopdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "Education details" ]
   (form-to [:post "/education"]
    [:p (label "provider" "Education provider")
    (text-field "provider" provider) ]
    [:p (label "coursename" "Course name")
    (text-field "coursename" coursename ) ]
    [:p (label "startdate" "Start date")
    (text-field "startdate" startdate ) ]
    [:p (label "stopdate" "Stop date")
    (text-field "stopdate" stopdate ) ]
    [:p (label "description" "Description")
    (text-field "description" description) ]
    [:p (submit-button "Save education") ]
  )][:div.clear]]])

;(defpage "/user/:id" {:keys [id]}
;  (layout
;    (edit-person-fields (cv.data/find-person-by-id id))))

;(defpage "/company/:id" {:keys [id]}
;  (layout
;    (edit-company-fields (cv.data/find-company-by-id id))))

;(defpage "/education/:id" {:keys [id]}
;  (layout
;    (edit-education-fields (cv.data/find-education-by-id id))))

;(defpage "/cv/:id" {:keys [id]}
;  (layout
;    (edit-person-fields (cv.data/find-person-by-id id))
;    (edit-company-fields (cv.data/find-company-by-id id))
;    (edit-education-fields (cv.data/find-education-by-id id))
;    (show-skills-list (cv.data/find-skills-list-by-id id))
;    ))

(defn success-notification [{:keys [message]}]
  (layout
    [:div.row
     [:div.content-area.notice
      [:div.column
       [:p message]]
       [:div.clear]]]))

;(defpage [:post "/user"] {:as user}
;  (success-notification ["User saved"])
;    (render "/user/:id" user))

;(defpage [:post "/company"] {:as company}
;  (success-notification ["Company saved"])
;    (render "/company/:id" company))

;(defpage [:post "/education"] {:as education}
;  (success-notification ["Education saved"])
;    (render "/education/:id" education))

;(server/start 8080)
