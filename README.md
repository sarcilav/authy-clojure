# authy-clojure

This is a non-production ready client for authy api

## Usage

```clojure
(:require [authy-clojure :as authy])

(def config (authy.conf/new-authy-config "https://api.authy.com" API_KEY))

(authy.core/register-user {:user {:email USER_EMAIL :cellphone USER_CELL :country_code USER_COUNTRY_CODE}} config)
(authy.core/verify USER_TOKEN USER_ID config)
(authy.core/request-sms USER_ID config)
(authy.core/request-call USER_ID config)
(authy.core/delete-user USER_ID config)
(authy.core/app-details config)
(authy.core/app-stats config)
```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
=======
