(ns calculator-api.entities.record
  (:require
    [calculator-api.config.config :as config]
    [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/record.sql")

(defn create-record [user-id operation-id result numbers cost]
  (create-record<! config/db-spec-hugsql {:user_id user-id :operation_id operation-id :result result :numbers numbers :cost cost}))
