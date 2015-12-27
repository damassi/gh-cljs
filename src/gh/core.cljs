(ns gh.core
  (:require [cljs.nodejs :as nodejs]
            [gh.star :as star]))

(nodejs/enable-util-print!)

(defn -main []
  (println "Initializing")
  (star/init))

(set! *main-cli-fn* -main)
