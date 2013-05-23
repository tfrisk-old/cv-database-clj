(ns cvpankki.datastructures
  (:use [cvpankki.date :as cvpankki.date]))

(def empty-person-map
  '{
    :firstname "Firstname",
    :lastname "Lastname",
    :birthdate "1.1.1970",
    :description "Introtext"
   }
)

(def empty-company-map
  '{
			:companyname "Company",
			:title "Title",
			:startdate "1.1.1970",
			:stopdate nil,
			:description "Description",
			:skills ()
		}
)

(def empty-education-map
  '{
			:provider "Institution",
			:coursename "Name",
			:startdate "1.1.1970",
			:stopdate "1.1.1970",
			:description "Description",
			:skills (
				"skill1",
				"skill2"
			)
		}
)

(def empty-skill-map
  '{
			:label "Clojure",
			:category "programming",
			:description "Clojure (pronounced like \"closure\") is a dialect of
the Lisp programming language created by Rich Hickey. It is a functional
general-purpose language. Its focus on programming with immutable values
and explicit progression-of-time constructs are intended to facilitate the
development of more robust programs, particularly multithreaded ones."
		}
)

(defn empty-cv-map [cvname]
  (assoc '{}
         :name cvname,
         :createdate (cvpankki.date/get-current-iso-8601-date)
         :person empty-person-map,
         :workhistory (conj '() empty-company-map),
         :educationhistory (conj '() empty-education-map),
         :skills (conj '() empty-skill-map)))

;nested map assoc-in examples: 
;(def cv-foo (empty-cv-map "foo"))
;(assoc-in cv-foo [:person :firstname] "Teemu")
;(assoc-in cv-teemu [:skills] '("Clojure", "Project Management"))

(defn find-person-by-id [id]
  (assoc empty-person-map :description id))

(defn find-all-persons []
  '[empty-person-map,empty-person-map,empty-person-map])

(defn find-company-by-id [id]
  (assoc empty-company-map :description id))

(defn find-education-by-id [id]
  (assoc empty-education-map :description id))

(defn find-cv-by-id [id]
  (assoc empty-cv-map :cvid id))

(defn find-skill-by-id [id]
  (assoc empty-skill-map :id id))

(defn find-skills-list-by-id [id]
  '[(assoc empty-skill-map :id id),(assoc empty-skill-map :id id)])
