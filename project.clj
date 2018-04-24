(defproject hugsql-adapter-case "0.0.0"
  :description "A HugSQL adapter that converts name of SQL columns"
  :url "https://github.com/druids/hugsql-adapter-case"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :dependencies [[camel-snake-kebab "0.4.0"]]


  :profiles {:dev {:plugins [[lein-cloverage "1.0.10"]
                             [lein-kibit "0.1.6"]
                             [jonase/eastwood "0.2.5"]]

                   :dependencies [[org.clojure/clojure "1.9.0"]]}})
