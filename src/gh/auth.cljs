(ns gh.auth
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [put!]]))

(defonce github (nodejs/require "octonode"))

(defn build-client! [access-token ch]
  (let [client (.client github access-token)
        me (.me client)]
    (put! ch me)))
