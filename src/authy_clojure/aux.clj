(ns authy-clojure.aux
  (:require
   [org.httpkit.client :as http]
   [clojure.string :refer [join]]
   [clojure.data.json :as json]
   [authy-clojure.conf :refer :all]))


(defn change-keys-to
  "Gets a HashMap and returns a HashMap after changing the type of the keys"
  [f m]
  (zipmap (map f (keys m)) (vals m)))

(defn parse
  "Decompose the response into body and status"
  [{body :body status :status}]
  (conj {:status status} (change-keys-to keyword (json/read-str body))))

(defn build-url
  "Constructs the proper url for the given endpoint"
  [uri-params]
  (join "/" (cons authy-url uri-params)))

(defn request
  "Basic way to send request"
  [method uri-params query-params form-params callback]
  (http/request {:method method
                 :url (build-url uri-params)
                 :query-params query-params
                 :form-params form-params}
                callback))
