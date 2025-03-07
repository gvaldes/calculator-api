-- :name operation-by-type :?
-- :doc Get an operation by type
SELECT id,
       type,
       cost
FROM Operation
WHERE type = :type;