(ns authy-clojure.core-test
  (:require [clojure.test :refer :all]
            [authy-clojure.core :refer :all]))

(defonce api-key "d57d919d11e6b221c9bf6f7c882028f9")

(deftest test-verify
  (let [response @(verify "0000000" "1" api-key)]
    (is (= (:message response) "Token is valid") "The message should be token is valid")
    (is (= (:status response) 200) "The status should be 200")))

(deftest test-register-user
  (let [response @(register-user {:user {:email "somedude@example.com" :cellphone "333-333-4444" :country_code "57"}} api-key)]
    (is (= (:status response) 200) "The status shoud be 200")
    (is (= (:message response) "User created successfully") "the message should be a successfull one")
    (is (contains? (:user response) "id") "the response should include an id for the new user")))
