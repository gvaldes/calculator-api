-- :name get-users :? :*
SELECT * FROM User;

-- :name get-user-by-id :? :1
-- :doc Get a user by id
SELECT id,
       userName,
       balance
FROM User
WHERE id = :id;

-- :name update-balance-user :?
-- :doc Update a user balance
UPDATE User
SET balance = :balance
where id = :id;