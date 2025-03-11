(ns calculator-api.config.config)

(def db-spec-hugsql
  {
   :subname     "//localhost:3307/calculator"
   :subprotocol "mysql"
   :classname   "com.mysql.cj.jdbc.Driver"
   :user        "root"
   :password    "password"})
