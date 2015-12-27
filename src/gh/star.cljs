(ns gh.star
  (:require [cljs.nodejs :as nodejs]))

(defonce github (nodejs/require "octonode"))

(defonce client (.client github))

(defn public-info [username]
  (.get (str "/users/" username) #js {}
    (fn [err status body headers]
      (. js/console (log body)))))

(defn build-github-client []
  (. js/console (log "connecting"))
  (public-info "damassi"))

(defn init []
  (build-github-client))
