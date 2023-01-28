(ns annapolis.core
  (:require [com.brunobonacci.mulog :as u]
            [mount.core :as mount :refer [defstate]]
            [org.httpkit.server :as http]
            [reitit.ring :as ring])
  (:gen-class))

(defn ping-handler [_req]
  {:status 200 :body "OK"})

(def app
  (ring/ring-handler
    (ring/router
      ["/api"
       ["/ping" {:get ping-handler}]])))

(defstate logger
  :start (u/start-publisher! {:type :console})
  :stop logger)

(defstate server
  :start (do
           (u/log ::server-starting)
           (http/run-server app {:port 8080}))
  :stop (do
          (u/log ::server-stopping)
          server))

(defn -main [& _args]
  (mount/start))
