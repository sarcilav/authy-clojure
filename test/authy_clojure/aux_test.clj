(ns authy-clojure.aux-test
  (:require [clojure.test :refer :all]
            [authy-clojure.aux :refer :all]))

(deftest test-change-keys-to
  (testing "change-keys-to"
    (is (keyword? (first (keys (change-keys-to keyword {"key" 1})))))
    (is (string? (first (keys (change-keys-to name {:key 1})))))))

(deftest test-parse
  (testing "parse"
    (let [params {:status 200 :body "{ \"some\": [1, 2, 3]}" :opts "daopts" :other "da-other"}]
      (is (contains? (parse params) :status))
      (is (contains? (parse params) :some))
      (is (not (contains? (parse params) :other)))
      (is (not (contains? (parse params) :opts))))))

(deftest test-build-url
  (testing "build-url"
    (is (re-find #"authy.com" (build-url "https://api.authy.com" [])))
    (is (re-find #"param/test/do" (build-url "https://some.com" ["param" "test" "do"])))))

(deftest test-request
  (testing "request"
    (let [response @(request :get [] (authy-clojure.conf/new-authy-config "https://api.authy.com/protected/json" "some-api-key") {} parse)]
      (is (= 401 (:status response)))
      (is (= "Invalid API KEY" (:message response))))))
