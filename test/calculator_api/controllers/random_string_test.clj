(ns calculator-api.controllers.random-string-test
  (:require [calculator-api.controllers.random-string :as con-rdn]
            [calculator-api.entities.operation :as ent-op]
            [calculator-api.entities.record :as ent-rec]
            [calculator-api.entities.user :as ent-us]
            [clj-http.client :as http]
            [clojure.test :refer :all]))

(deftest get-random-string-success
  (with-redefs [ent-us/get-user-by-id (fn [& _] {:username "test user"
                                                 :balance  50})
                ent-op/get-operation-by-type (fn [& _] {:id   "5"
                                                        :cost 15})
                http/get (fn [& _] {:body "##RandomString$$"
                                    :status 200})
                ent-rec/create-record (fn [& _] {:id 1})]
    (let [response (con-rdn/get-random-string 10)]
      (is (= "##RandomString$$" (:result response)))
      (is (= 35 (:balance response))))))