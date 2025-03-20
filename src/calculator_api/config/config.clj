(ns calculator-api.config.config)

;(def db-spec-hugsql {:subname     "//localhost:3307/calculator"
;                     :subprotocol "mysql"
;                     :classname   "com.mysql.cj.jdbc.Driver"
;                     :user        "root"
;                     :password    "password"})


(def db-spec-hugsql {:subname     "//calculator.cjw4iw4a24ym.us-west-2.rds.amazonaws.com:3306/calculator"
                     :subprotocol "mysql"
                     :classname   "com.mysql.cj.jdbc.Driver"
                     :user        "admin"
                     :password    "ClojureCalculator"})

