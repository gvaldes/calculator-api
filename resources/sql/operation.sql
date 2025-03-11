-- :name operation-by-type :? :1
-- :doc Get an operation by type
SELECT id,
       type,
       cost
FROM Operation
WHERE type = :type;