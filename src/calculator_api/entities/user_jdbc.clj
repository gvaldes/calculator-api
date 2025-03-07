(ns calculator-api.entities.user_jdbc
  (:require
    [calculator-api.config.config :as config]
    [next.jdbc :as jdbc]))

(def ds (jdbc/get-datasource config/db-spec))

(defn get-user-by-id [id]
  (jdbc/execute! ds ["SELECT id, username FROM User where id = ?" id]))

