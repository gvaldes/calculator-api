(ns calculator-api.controllers.operation
  (:require [calculator-api.entities.operation_jdbc :as op_jdbc]
            [calculator-api.entities.user :as us]
            [calculator-api.entities.operation :as op]
            [calculator-api.entities.user_jdbc :as us_jdbc]))

(defn sum [num_1 num_2]
  (+ num_1 num_2))

(defn multiplication [num_1 num_2]
  (* num_1 num_2))

(defn subtraction [num_1 num_2]
  (- num_1 num_2))

(defn division [num_1 num_2]
  (/ num_1 num_2))

(defn performOperation [{:keys [num_1 num_2 userId type]}]
  (println "JDBC Type test = " (op_jdbc/get-operation-by-type type))
  (println "HugSQL Type test = " (op/get-operation-by-type type))

  (println "JDBC User test = " (us_jdbc/get-user-by-id userId))
  (println "HugSQL User test = " (us/get-user-by-id-hugh userId))

  (str (case type
         "sum" (sum num_1 num_2)
         "subtraction" (subtraction num_1 num_2)
         "multiplication" (multiplication num_1 num_2)
         "division" (division num_1 num_2)
         "Operation type not found"
         ))
  )
