(ns authy-clojure.core
  (:require [authy-clojure.aux :refer [request parse]]))


(defn verify
  "Request to Authy to Verify the token for a given user"
  [token authy-id api-key]
  (request :get  ["verify" token authy-id] {:api_key api-key} {} parse))

(defn register-user
  "creates a new user"
  [user-params api-key]
  (request :post ["users/new"] {:api_key api-key} user-params parse))