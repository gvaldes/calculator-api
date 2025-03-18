(ns calculator-api.controllers.operation_test
  (:require [calculator-api.controllers.operation :as op]
            [calculator-api.entities.operation :as ent-op]
            [calculator-api.entities.record :as ent-rec]
            [calculator-api.entities.user :as ent-us]
            [clojure.test :refer :all]))

(deftest calculate-result-sum
  (is (= 10 (op/calculate-result "sum" 5 5))))

(deftest calculate-result-multiplication
  (is (= 25 (op/calculate-result "multiplication" 5 5))))

(deftest calculate-result-subtraction
  (is (= 10 (op/calculate-result "subtraction" 15 5))))

(deftest calculate-result-division
  (is (= 5 (op/calculate-result "division" 15 3))))

(deftest calculate-result-not-found
  (try (op/calculate-result "Mock type" 5 5)
       (catch Exception e
         (is (= "Invalid operation" (ex-message e))))))

(deftest perform-operation-success-sum
  (with-redefs [ent-us/get-user-by-id (constantly {:username "test user" :balance 50})
                ent-op/get-operation-by-type (constantly {:id "10" :cost 5})
                ent-rec/create-record (constantly {:id 1})]
    (testing "Sum scenario"
      (let [request {:type    "sum"
                     :num-1   10
                     :num-2   4
                     :user-id 1}
            response (op/performOperation request)]
        (is (= "test user" (:user-name response)))
        (is (= 14 (:result response)))
        (is (= 45 (:balance response)))))

    (testing "Multiplication scenario"
      (let [request {:type    "multiplication"
                     :num-1   10
                     :num-2   4
                     :user-id 1}
            response (op/performOperation request)]
        (is (= "test user" (:user-name response)))
        (is (= 40 (:result response)))
        (is (= 45 (:balance response)))))))

(deftest perform-operation-success-operation-not-found
  (with-redefs [ent-us/get-user-by-id (constantly {:username "test user" :balance 50})
                ent-op/get-operation-by-type (constantly {:id "10" :cost 5})
                ent-rec/create-record (constantly {:id 1})]
    (let [request {:type "mock_test"}]
      (try
        (op/performOperation request)
        (catch Exception e
          (is (= "Invalid operation" (ex-message e))))))))

(deftest perform-operation-validate-record
  (let [spy (atom {})]
    (with-redefs [ent-us/get-user-by-id (constantly {:username "test user" :balance 50})
                  ent-op/get-operation-by-type (constantly {:id "10" :cost 5})
                  ent-rec/create-record (fn [& args] (swap! spy assoc :record-created-args args) {:id 6666})]
      (testing "Multiplication scenario"
        (let [request {:type    "multiplication"
                       :num-1   10
                       :num-2   4
                       :user-id 5555}
              response (op/performOperation request)]
          (println "Spy = " spy)
          (is (= "test user" (:user-name response)))
          (is (= 40 (:result response)))
          ;user-id operation-id result operands operation-cost
          (is (= [5555 "10" 40 "[10 4]" 5] (:record-created-args @spy)))
          (is (= 45 (:balance response))))))))