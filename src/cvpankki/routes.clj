(ns cvpankki.routes
  (:use compojure.core
        cvpankki.web
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (route/resources "/")
  (route/not-found "Page not found")
  (GET "/user/:id" [id] (str "<h1>Hello user " id "</h1>"))
  (GET "/company/:id" [id] (str "<h1>Hello company " id "</h1>"))
  (GET "/education/:id" [id] (str "<h1>Hello education " id "</h1>"))
  (GET "/cv/:id" [id] (str "<h1>Hello cv " id "</h1>"))
  )

(defn index-page []
  (html5
    [:head
      [:title "Hello World"]
      (include-css "/css/style.css")]
    [:body
      [:h1 "Hello World"]]))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))