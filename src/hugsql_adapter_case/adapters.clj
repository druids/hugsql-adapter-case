(ns hugsql-adapter-case.adapters
  (:require
    [camel-snake-kebab.core :refer [->kebab-case ->snake_case ->camelCase]]
    [hugsql.adapter :as adapter]
    [hugsql.adapter.clojure-java-jdbc :refer [hugsql-adapter-clojure-java-jdbc]]))


(deftype CaseAdapter [case-fn jdbc-adapter]

  adapter/HugsqlAdapter
  (execute [this db sqlvec options]
    (adapter/execute jdbc-adapter db sqlvec options))

  (query [this db sqlvec options]
    (->> options
         (adapter/query jdbc-adapter db sqlvec)
         (map #(reduce-kv (fn [acc k v] (assoc acc (case-fn k) v)) {} %))))

  (result-one [this result options]
    (adapter/result-one jdbc-adapter result options))

  (result-many [this result options]
    (adapter/result-many jdbc-adapter result options))

  (result-affected [this result options]
    (adapter/result-affected jdbc-adapter result options))

  (result-raw [this result options]
    (adapter/result-raw jdbc-adapter result options))

  (on-exception [this exception]
    (throw exception)))


(defn kebab-adapter
  []
  (->CaseAdapter ->kebab-case (hugsql-adapter-clojure-java-jdbc)))


(defn snake-adapter
  []
  (->CaseAdapter ->snake_case (hugsql-adapter-clojure-java-jdbc)))


(defn camel-adapter
  []
  (->CaseAdapter ->camelCase (hugsql-adapter-clojure-java-jdbc)))
