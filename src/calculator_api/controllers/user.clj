(ns calculator-api.controllers.user
  (:require
    [calculator-api.entities.operation :as op]
    [calculator-api.entities.user :as us]))

(defn update-user-balance-after-operation [userId balance cost]
  (if (> balance cost)
    (us/update-user-balance userId (- balance cost))
    (throw (ex-info "Not enough balance" {:balance balance}))))

(defn discount-operation-cost [userId operationType]
  (let [user (us/get-user-by-id userId)
        {:keys [balance]} user
        type (op/get-operation-by-type operationType)
        {:keys [cost]} type]
    (update-user-balance-after-operation userId balance cost)))
