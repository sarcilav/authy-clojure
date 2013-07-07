(ns authy-clojure.core-test
  (:require [clojure.test :refer :all]
            [authy-clojure.core :refer :all]))

(def conf (authy-clojure.conf/new-authy-config "http://sandbox-api.authy.com/protected/json" "7ea6e01f516b0a3ba8e9df75d1f9a6f6"))

(deftest test-verify
  (testing "verify"
    (let [response @(verify "0000000" 1 conf)]
      (is (= (:message response) "Token is valid"))
      (is (= (:status response) 200)))))

(def user-response @(register-user {:user {:email "somedude@example.com" :cellphone "333-333-4444" :country_code "57"}} conf))

(deftest test-register-user
  (testing "register-user"
    (let [response user-response]
      (is (= (:status response) 200))
      (is (= (:message response) "User created successfully"))
      (is (contains? (:user response) "id")))))

(deftest test-request-sms
  (testing "request-sms"
    (let [response @(request-sms ((:user user-response) "id") conf)]
      (is (= (:status response) 503))
      (is (= (:message response)
             "The text-message addon is not enabled for this app. Upgrade at least to Starter Plan to use SMS, see https://www.authy.com/pricing")))))

(deftest test-request-call
  (testing "request-call"
    (let [response @(request-call ((:user user-response) "id") conf)]
      (is (= (:status response) 503))
      (is (= (:message response)
             "The Calls addon is not enabled for this app. Contact us to support@authy.com to enable this feature")))))

(deftest test-delete-user
  (testing "delete-user"
    (let [response @(delete-user ((:user user-response) "id") conf)]
      (is (= (:status response) 200))
      (is (= (:message response) "User was added to remove.")))))

(deftest test-app-details
  (testing "app-details"
    (let [response @(app-details conf)]
      (is (= (:status response) 200))
      (is (= (:message response) "Application information."))
      (is (= (:app response) {"name" "Sandbox App 2", "plan" "starter", "sms_enabled" false, "white_label" false})))))

(deftest test-app-stats
  (testing "app-stats"
    (let [response @(app-stats conf)]
      (is (= (:status response) 200))
      (is (= (:message response) "Monthly statistics."))
      (is (= (class (:stats response)) clojure.lang.PersistentVector)))))