(defproject cvpankki "0.1.0-SNAPSHOT"
  :description "CV Database"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.5.0"]
                 [clj-time "0.4.4"] ;time functionalities
                 [compojure "1.1.5"]
                 [hiccup "1.0.0"]]
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler cvpankki.routes/app})
