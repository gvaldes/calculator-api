(ns calculator-api.schemas.schemas
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

; delete
(s/defschema Pizza
  {:name s/Str})

(s/defschema Operation
  {:type  s/Str
   :num_1 s/Num
   :num_2 s/Num
   :userId s/Num})

(s/defschema Result
  {:result s/Num
   :balance s/Num
   :userName s/Str})
