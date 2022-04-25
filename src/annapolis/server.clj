(ns annapolis.server
  (:require [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
    (ring/router
      ["/api"
       ["/ping" {:get (fn [_] {:status 200
                               :body "ok"})}]])
    (ring/create-default-handler)))

(defn start []
  (jetty/run-jetty #'app {:port 3000, :join? false})
  (println "Server running on port 3000"))
