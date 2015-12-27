(ns gh.core
  (:require [cljs.nodejs :as nodejs]
            [gh.auth :refer [build-client]]
            [shodan.console :as console :include-macros true]))

(nodejs/enable-util-print!)

(defonce dotenv (nodejs/require "dotenv"))
(.load dotenv)

(def access-token (.. nodejs/process -env -GH_ACCESS_TOKEN))

(defn init []
  (build-client access-token))

(defn -main []
  (init))

(set! *main-cli-fn* -main)
