(ns gh.core
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [chan close! <! >!]]
            [gh.auth :refer [build-client!]]
            [gh.api :refer [build-api!]])

  (:require-macros [cljs.core.async.macros :refer [go]]))

(nodejs/enable-util-print!)
(.load (nodejs/require "dotenv"))

(def access-token (.. nodejs/process -env -GH_ACCESS_TOKEN))
(def ch (chan))

(defn init []
  "Initialies app by building a GitHub client and the API"
  (go
    (build-api! (<! ch)))

  (build-client! access-token ch))

(defn -main []
  (init))

(set! *main-cli-fn* -main)
