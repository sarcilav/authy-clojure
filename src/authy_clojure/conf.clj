(ns authy-clojure.conf)

(defrecord AuthyConf [url api-key])

(defn new-authy-config [url api-key]
  (AuthyConf. url api-key))