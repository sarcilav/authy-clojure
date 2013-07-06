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

(defn request-sms
  "request an sms, it will send a token via sms to the user"
  [user-id api-key]
  (request :get ["sms" user-id] {:api_key api-key} {} parse))

(defn request-call
  "request a call, it will call the user to the cellphone (it works like voice-token"
  [user-id api-key]
  (request :get ["call" user-id] {:api_key api-key} {} parse))

(defn delete-user
  "delete users from the application"
  [user-id api-key]
  (request :post ["users" "delete" user-id] {:api_key api-key} {} parse))

(defn app-details
  "The application details"
  [api-key]
  (request :get ["app" "details"] {:api_key api-key} {} parse))

(defn app-stats
  "The application stats"
  [api-key]
  (request :get ["app" "stats"] {:api_key api-key} {} parse))