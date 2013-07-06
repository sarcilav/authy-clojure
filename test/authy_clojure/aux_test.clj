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
    (is (re-find #"authy.com" (build-url [])))
    (is (re-find #"param/test/do" (build-url ["param" "test" "do"])))))

(deftest test-request
  (testing "request"
    (is (= 401 @(request :get [] {} {} #(:status %))))
    (is (= "{\"success\":\"false\",\"message\":\"Invalid API KEY\",\"errors\":{\"message\":\"Invalid API KEY\"}}"
           @(request :get [] {} {} #(:body %))))))
