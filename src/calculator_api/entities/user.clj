(ns calculator-api.entities.user
  (:require
    [calculator-api.config.config :as config]
    [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/user.sql")

(defn update-user-balance [id balance]
  (update-new-user-balance! config/db-spec-hugsql {:id id :balance balance}))

(defn get-user-by-id [id]
  (let [user (user-by-id config/db-spec-hugsql {:id id})]
    (if user
      user
      (throw (ex-info (str "User not found with ID: " id) {:user-id id})))))
