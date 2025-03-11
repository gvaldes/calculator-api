-- :name user-by-id :? :1
-- :doc Get a user by id
SELECT id,
       userName,
       balance
FROM User
WHERE id = :id;

-- :name update-new-user-balance! :! :n
-- :doc Update a user balance
UPDATE User
SET balance = :balance
where id = :id;