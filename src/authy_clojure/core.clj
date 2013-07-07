(ns authy-clojure.core
  (:require [authy-clojure.aux :refer [request parse]]))


(defn verify
  "Request to Authy to Verify the token for a given user"
  [token authy-id conf]
  (request :get  ["verify" token authy-id] conf {} parse))

(defn register-user
  "creates a new user"
  [user-params conf]
  (request :post ["users/new"] conf user-params parse))

(defn request-sms
  "request an sms, it will send a token via sms to the user"
  [user-id conf]
  (request :get ["sms" user-id] conf {} parse))

(defn request-call
  "request a call, it will call the user to the cellphone (it works like voice-token"
  [user-id conf]
  (request :get ["call" user-id] conf {} parse))

(defn delete-user
  "delete users from the application"
  [user-id conf]
  (request :post ["users/delete" user-id] conf {} parse))

(defn app-details
  "The application details"
  [conf]
  (request :get ["app/details"] conf {} parse))

(defn app-stats
  "The application stats"
  [conf]
  (request :get ["app/stats"] conf {} parse))