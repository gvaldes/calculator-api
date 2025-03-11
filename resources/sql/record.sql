-- :name create-record<! :i!
-- :doc Insert a new record
INSERT INTO Record
(userId,
 operationId,
 result,
 numbers,
 cost)
VALUES (:user_id,
        :operation_id,
        :result,
        :numbers,
        :cost);