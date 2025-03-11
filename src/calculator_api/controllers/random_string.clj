(ns calculator-api.controllers.random-string
  (:require [calculator-api.controllers.user :as con-us]
            [calculator-api.entities.operation :as ent-op]
            [calculator-api.entities.record :as ent-rec]
            [calculator-api.entities.user :as ent-us]
            [calculator-api.utils.utils :as utils]
            [clj-http.client :as http]))

(defn get-random-string [user-id]
  (let [response (http/get "https://www.random.org/strings/?num=1&len=10&upperalpha=on&loweralpha=on&digits=on&unique=on&format=plain&rnd=new")]
    (if (= (:status response) 200)
      (let [user (ent-us/get-user-by-id user-id)
            user-name (:username user)
            before-balance (:balance user)
            typeMap (ent-op/get-operation-by-type "random_string")
            {:keys [id cost]} typeMap
            random-string (:body response)]
        (con-us/discount-operation-cost user-id "random_string")
        (ent-rec/create-record user-id id (:body response) "" cost)
        (utils/build-response random-string cost (- before-balance cost) user-id user-name)
        )
      (throw (ex-info "Failed to get random string" {:status (:status response)})))))