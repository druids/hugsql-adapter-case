(ns hugsql-adapter-case.adapters
  (:require
    [camel-snake-kebab.core :refer [->kebab-case ->snake_case ->camelCase]]
    [hugsql.adapter :as adapter]))


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
  [jdbc-adapter]
  (->CaseAdapter ->kebab-case jdbc-adapter))


(defn snake-adapter
  [jdbc-adapter]
  (->CaseAdapter ->snake_case jdbc-adapter))


(defn camel-adapter
  [jdbc-adapter]
  (->CaseAdapter ->camelCase jdbc-adapter))
