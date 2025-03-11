(ns calculator-api.schemas.schemas
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema operation-request
  {:type  s/Str
   :num-1 s/Num
   :num-2 s/Num
   :user-id s/Num})

(s/defschema string-request
  {:type  s/Str
   :user-id s/Num})

(s/defschema result
  {:result s/Str
   :cost s/Num
   :balance s/Num
   :user-id s/Num
   :user-name s/Str})
