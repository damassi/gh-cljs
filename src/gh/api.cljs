(ns gh.api)

(defn get-starred [user page success]
  (.starred user page
    (fn [err repos]
      (if (nil? err)
        (let [repo-urls (mapv #(get % "full_name") (js->clj repos))]
          (success user repo-urls))
        (js/console.log err)))))

(defn unstar! [user repo-urls]
  (let [len (count repo-urls)]
    (when (> len 0)
      (loop [x 0]
        (.unstar user (nth repo-urls x) (fn [r]
                                          (println "Unstarred repo")))
        (if (= (inc x) len)
          (println "Complete.")
          (recur (inc x)))))))

(defn build-api! [user]
  (get-starred user 10 unstar!))
