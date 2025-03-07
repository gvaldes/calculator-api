(ns calculator-api.config.config)

(def db-spec
  {
   :dbtype   "mysql"
   :dbname   "calculator"
   :host     "localhost"
   :port     3307
   :user     "root"
   :password "password"})

(def db-spec-hugsql
  {
   :subname     "//localhost:3307/calculator"
   :subprotocol "mysql"
   :classname   "com.mysql.cj.jdbc.Driver"
   :user        "root"
   :password    "password"})
