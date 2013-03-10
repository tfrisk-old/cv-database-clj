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
			:coursekeeper "Institution",
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

(defn empty-cv-map [cvname]
  (assoc '{}
         :name cvname,
         :createdate (cvpankki.date/get-current-iso-8601-date)
         :person empty-person-map,
         :workhistory (conj '() empty-company-map),
         :educationhistory '(),
         :skills '())
)