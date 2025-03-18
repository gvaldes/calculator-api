(ns calculator-api.utils.utils)

(defn build-response [result cost balance user-id user-name]
  {:result    result
   :cost      cost
   :balance   balance
   :user-id   user-id
   :user-name user-name})