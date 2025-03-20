(ns calculator-api.controllers.operation
  (:require
    [calculator-api.controllers.user :as us]
    [calculator-api.entities.operation :as ent-op]
    [calculator-api.entities.record :as ent-rec]
    [calculator-api.entities.user :as ent-us]
    [calculator-api.utils.utils :as utils]))

(defn sum [num_1 num_2]
  (+ num_1 num_2))

(defn multiplication [num_1 num_2]
  (* num_1 num_2))

(defn subtraction [num_1 num_2]
  (- num_1 num_2))

(defn division [num_1 num_2]
  (/ num_1 num_2))

(defn calculate-result [type num-1 num-2]
  (case type
    "sum" (sum num-1 num-2)
    "subtraction" (subtraction num-1 num-2)
    "multiplication" (multiplication num-1 num-2)
    "division" (division num-1 num-2)
    (throw (ex-info "Invalid operation" {:type type}))))

(defn performOperation [{:keys [num-1 num-2 user-id type]}]
  (us/discount-operation-cost user-id type)

  (let [typeMap (ent-op/get-operation-by-type type)
        {:keys [id cost]} typeMap
        result (calculate-result type num-1 num-2)
        user (ent-us/get-user-by-id user-id)
        user-name (:username user)
        before-balance (:balance user)]
    (ent-rec/create-record user-id id result (str "[" num-1 " " num-2 "]") cost)
    (utils/build-response (str result) cost (- before-balance cost) user-id user-name)))
