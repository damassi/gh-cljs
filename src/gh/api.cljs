(ns gh.api
  (:require [cljs.core.async :refer [chan put! take! <!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def api-ch (chan))

(defn get-starred [user success]
  (.starred user
    (fn [err repos]
      (if (nil? err)
        (success (js->clj repos))
        (.log js/console err)))))

(defn build-api! [user]
  (get-starred user
    (fn [repos]
      (let [urls (mapv #(get % "full_name") repos)]
        (println urls)))))
