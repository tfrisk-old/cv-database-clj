(ns cvpankki.routes
  (:use compojure.core
        cvpankki.web
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/user/:id" [id] (userpage id))
  (GET "/company/:id" [id] (companypage id))
  (GET "/education/:id" [id] (educationpage id))
  (GET "/cv/:id" [id] (cvpage id))
  (route/files "/" {:root (str (System/getProperty "user.dir") "/resources/public")})
  (route/not-found "Page not found")
  )


(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))
