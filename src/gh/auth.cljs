(ns gh.auth
  (:require [cljs.nodejs :as nodejs]))

(defonce github (nodejs/require "octonode"))

(defn build-client [access-token]
  (let [client (.client github access-token)
        me (.me client)]
    (.starred me
      (fn [err repos]
        (.log js/console foo)
        (.log js/console repos)))))
