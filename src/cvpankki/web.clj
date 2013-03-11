(ns cvpankki.web
  (:use noir.core
        hiccup.core)
  (:require [noir.server :as server]))

(defpage "/welcome" []
  "Welcome to noir")

(defpage "/" []
  "hello")

;(server/start 8080)
