(ns calculator-api.entities.operation
  (:require
    [calculator-api.config.config :as config]
    [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/operation.sql")

(defn get-operation-by-type [type]
  (let [operation (operation-by-type config/db-spec-hugsql {:type type})]
    (if operation
      operation
      (throw (ex-info (str "Operation type not found with type: " type) {:type type}))
      )
    )
  )
