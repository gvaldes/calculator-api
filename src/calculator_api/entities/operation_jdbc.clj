(ns calculator-api.entities.operation_jdbc
  (:require
    [hugsql.core :refer :all]
    [next.jdbc :as jdbc]
    [calculator-api.config.config :as config]))

(def-db-fns "sql/operation.sql")

(def ds (jdbc/get-datasource config/db-spec))

(defn get-operation-by-type [type]
  (jdbc/execute! ds ["SELECT id, type, cost FROM Operation WHERE type = ?" type]))