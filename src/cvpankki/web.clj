(ns cvpankki.web
  (:use compojure.core)
  (:use [hiccup core form page element])
  (:use [cvpankki.datastructures :as cv.data])
  (:require [compojure.route :as route]))

; ------------ base layout ------------
(defn layout [& content]
  (html5
    [:head
     [:title "cvpankki"]
     (include-css "/css/cvpankki.css")]
    [:body content]))

; ------------ landing page ------------
(defn index-page []
  (layout
    [:body
      [:h1 "cvpankki"]
      [:ul
        [:li (link-to "/user/all" "Users")]
        [:li (link-to "/company/all" "Companies")]
        [:li (link-to "/education/all" "Educations")]
        [:li (link-to "/cv/all" "CVs")]
      ]]))

; ------------ notifications ------------
(defn success-notification [{:keys [message]}]
  (layout
    [:div.row
     [:div.content-area.notice
      [:div.column
       [:p message]]
       [:div.clear]]]))

; ------------ skill ------------
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

;partial for showing skill data
(defn edit-skill-fields [{:keys [label category description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "Skill details" ]
   [:p "Label " label]
   [:p "Category " category]
   [:p "Description " description]]
  [:div.clear]]])

(defn skillpage [id]
  (layout
    (edit-skill-fields (cv.data/find-skill-by-id id))))

; List all skills on single page
(defn skilllistpage []
  (layout
    "Skill listing"
    (cv.data/find-skill-by-id "all")))

; ------------ person/user ------------
;partial for showing user data
(defn show-person-fields [{:keys [firstname lastname birthdate description]}]
  [:div.row [:div.content-area
  [:div.column
   [:h3 "User details" ]
   [:p "Name " firstname " " lastname]
   [:p "Birthday " birthdate]
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

(defn userpage [id]
  (layout
    (edit-person-fields (cv.data/find-person-by-id id))))

; List all users on single page
(defn userlistpage []
  (layout
    "User listing"
    (cv.data/find-person-by-id "all")))

; ------------ company ------------
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

(defn companypage [id]
  (layout
    (edit-company-fields (cv.data/find-company-by-id id))))

; List all companies on single page
(defn companylistpage []
  (layout
    "Company listing"
    (cv.data/find-company-by-id "all")))

; ------------ education ------------
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

(defn educationpage [id]
  (layout
    (edit-education-fields (cv.data/find-education-by-id id))))

; List all educations on single page
(defn educationlistpage []
  (layout
    "Education listing"
    (cv.data/find-education-by-id "all")))

; ------------ cv ------------
(defn cvpage [id]
  (layout
    (edit-person-fields (cv.data/find-person-by-id id))
    (edit-company-fields (cv.data/find-company-by-id id))
    (edit-education-fields (cv.data/find-education-by-id id))
    (show-skills-list (cv.data/find-skills-list-by-id id))
    ))

; List all cvs on single page
(defn cvlistpage []
  (layout
    "CV listing"
    (cv.data/find-cv-by-id "all")))

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
