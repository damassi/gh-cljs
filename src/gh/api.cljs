(ns gh.api)

(defn get-starred [user page success]
  (.starred user page
    (fn [err repos]
      (if (nil? err)
        (let [repo-urls (mapv #(get % "full_name") (js->clj repos))]
          (success user repo-urls))
        (.log js/console err)))))

(defn unstar! [user repo-urls]
  (when (> (count repo-urls) 0)
    (loop [x 0]
      (.unstar user (nth repo-urls x) (fn [r] (println "Unstarred repo")))
      (if (= (inc x) (count repo-urls))
        (println "Complete.")
        (recur (inc x))))))

(def api [get-starred, unstar!])

(defn build-api! [user]
  (get-starred user 10 unstar!))
