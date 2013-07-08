# authy-clojure

This is a non-production ready client for authy api

## Usage

```clojure
user> (ns foo
  (:require
      [authy-clojure.core :as authy]
      [authy-clojure.conf :as authy-conf]))

foo> (def config (authy.conf/new-authy-config "https://api.authy.com/protected/json" "API_KEY"))
;; #'foo/config
;; foo> config
;; #authy_clojure.conf.AuthyConf{:url "https://api.authy.com/protected/json", :api-key "API_KEY"}


foo> (authy/register-user {:user
    {:email "some@place.com"
     :cellphone "3228276771"
     :country_code "1"}}
     config)
;; #<core$promise$reify__6310@5b7f494c: :pending>
;; As you notice you will need to deref the promise object to be able to read it

foo> @(authy/register-user {:user
    {:email "sebastianarcila@gmail.com"
     :cellphone "3148174881"
     :country_code "57"}}
     config)
;; {:message "User created successfully", :user {"id" 3996}, :success "true", :status 200}

foo> @(authy/verify "3281286" 3996 config)
;; {:message "token is valid", :token "is valid", :success "true", :status 200}

foo> @(authy/request-sms 3996 config)
;; {:success "false", :message "The text-message addon is not enabled for this app. Upgrade at least to Starter Plan to use SMS, see https://www.authy.com/pricing", :errors {"message" "The text-message addon is not enabled for this app. Upgrade at least to Starter Plan to use SMS, see https://www.authy.com/pricing"}, :status 503}

foo> @(authy/request-call 3996 config)
;; {:success "false", :message "The calls add-on is not enabled on this App. To enable please contact support@authy.com", :errors {"message" "The calls add-on is not enabled on this App. To enable please contact support@authy.com"}, :status 503}

foo> @(authy/delete-user 3996 config)
;; {:message "User was added to remove.", :success "true", :status 200}

foo> @(authy/app-details config)
;; {:app {"name" "test", "plan" "sandbox", "sms_enabled" false, "white_label" false}, :message "Application information.", :success "true", :status 200}

foo> @(authy/app-stats config)
;; {:stats [{"sms_count" 0, "calls_count" 0, "users_count" 1, "auths_count" 28, "month" "April", "api_calls_count" 61, "year" 2013} {"sms_count" 0, "calls_count" 0, "users_count" 4, "auths_count" 12, "month" "May", "api_calls_count" 58, "year" 2013} {"sms_count" 0, "calls_count" 0, "users_count" 0, "auths_count" 9, "month" "June", "api_calls_count" 66, "year" 2013} {"sms_count" 0, "calls_count" 0, "users_count" 1, "auths_count" 2, "month" "July", "api_calls_count" 20, "year" 2013}], :message "Monthly statistics.", :count 4, :success "true", :status 200}
```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
=======
