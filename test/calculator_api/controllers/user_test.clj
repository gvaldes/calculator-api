(ns calculator-api.controllers.user_test
  (:require [calculator-api.controllers.user :as us]
            [calculator-api.entities.operation :as ent-op]
            [calculator-api.entities.user :as ent-us]
            [clojure.test :refer :all]))

(deftest update-user-balance-after-operation-not-enough-balance
  (try
    (us/update-user-balance-after-operation 1 10 15)
    (catch Exception e
      (is (= "Not enough balance" (ex-message e))))))

(deftest discount-operation-cost-not-enough-balance
  (with-redefs [ent-us/get-user-by-id (constantly {:balance 5})
                ent-op/get-operation-by-type (constantly {:cost 10})]
    (try
      (us/discount-operation-cost 1 1)
      (catch Exception e
        (is (= "Not enough balance" (ex-message e)))))))

