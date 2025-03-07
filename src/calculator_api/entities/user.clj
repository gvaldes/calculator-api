(ns calculator-api.entities.user
  (:require
    [calculator-api.config.config :as config]
    [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/user.sql")

(defn get-user-by-id-hugh [id]
  (get-user-by-id config/db-spec-hugsql {:id id}))


