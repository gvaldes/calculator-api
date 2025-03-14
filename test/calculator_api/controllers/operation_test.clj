(ns calculator-api.controllers.operation_test
  (:require [calculator-api.controllers.operation :as op]
            [calculator-api.entities.operation :as ent-op]
            [calculator-api.entities.record :as ent-rec]
            [calculator-api.entities.user :as ent-us]
            [clojure.test :refer :all]))

(deftest sum
  (is (= 10 (op/sum 5 5))))

(deftest multiplication
  (is (= 25 (op/multiplication 5 5))))

(deftest subtraction
  (is (= 10 (op/subtraction 15 5))))

(deftest division
  (is (= 5 (op/division 15 3))))

(deftest calculate-result-sum
  (is (= 10 (op/calculate-result "sum" 5 5))))

(deftest calculate-result-multiplication
  (is (= 25 (op/calculate-result "multiplication" 5 5))))

(deftest calculate-result-not-found
  (try (op/calculate-result "error" 5 5)
       (catch Exception e
         (is (= "Invalid operation" (ex-message e))))))

(deftest perform-operation-success-sum
  (with-redefs [ent-us/get-user-by-id (fn [& _] {:username "test user"
                                                 :balance  50})
                ent-op/get-operation-by-type (fn [& _] {:id   "10"
                                                        :cost 5
                                                        })
                ent-rec/create-record (fn [& _] {:id 1})]
    (let [request {:type    "sum"
                   :num-1   10
                   :num-2   4
                   :user-id 1
                   }
          response (op/performOperation request)]
      (is (= "test user" (:user-name response)))
      (is (= "14" (:result response)))
      (is (= 45 (:balance response))))))

(deftest perform-operation-success-multiplication
  (with-redefs [ent-us/get-user-by-id (fn [& _] {:username "test user"
                                                 :balance  50})
                ent-op/get-operation-by-type (fn [& _] {:id   "10"
                                                        :cost 5
                                                        })
                ent-rec/create-record (fn [& _] {:id 1})]
    (let [request {:type    "multiplication"
                   :num-1   10
                   :num-2   4
                   :user-id 1
                   }
          response (op/performOperation request)]
      (is (= "test user" (:user-name response)))
      (is (= "40" (:result response)))
      (is (= 45 (:balance response))))))

(deftest perform-operation-success-operation-not-found
  (with-redefs [ent-us/get-user-by-id (fn [& _] {:username "test user"
                                                 :balance  50})
                ent-op/get-operation-by-type (fn [& _] {:id   "10"
                                                        :cost 5
                                                        })
                ent-rec/create-record (fn [& _] {:id 1})]
    (let [request {:type    "mock_test"}]
      (try
        (op/performOperation request)
        (catch Exception e
          (is (= "Invalid operation" (ex-message e))))))))