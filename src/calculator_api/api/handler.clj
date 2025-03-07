(ns calculator-api.api.handler
  (:require [calculator-api.controllers.operation :as ops]
            [calculator-api.schemas.schemas :as sch]
            [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :as jetty]
            [ring.util.http-response :refer :all]))

(def app
  (api
    {:swagger
     {:ui   "/"
      :spec "/swagger.json"
      :data {:info {:title       "Calculator API"
                    :description "This service contains the API's to perform calculator operations"}
             :tags [{:name "calculator", :description "Calculator methods"}]}}}

    (context "/calculator" []
      :tags ["calculator"]

      (GET "/custom" []
        :summary "Return sample custom text new"
        (ok {:message "Custom GET endpoint updated"}))

      (GET "/plus" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "Adds two numbers together"
        (ok {:result (+ x y)}))

      (POST "/operation" []
        ;:return sch/Operation
        :body [operation sch/Operation]
        :summary "Perform an arithmetic operation"
        (ok (ops/performOperation operation)))

      (POST "/echo" []
        :return sch/Pizza
        :body [pizza sch/Pizza]
        :summary "Echoes a Pizza"
        (ok pizza)))))

(defn -main
  [& args]
  (jetty/run-jetty app {:port 4300}))
