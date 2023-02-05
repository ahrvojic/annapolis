(ns annapolis.core
  (:require [annapolis.zk :as zk]
            [com.brunobonacci.mulog :as u]
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
  :start (u/start-publisher! {:type :console-json})
  :stop (logger))

(defstate framework
  :start (do
           (u/log ::framework-starting)
           (zk/start (zk/framework)))
  :stop (do
          (u/log ::framework-stopping)
          (zk/stop framework)))

(defstate api
  :start (do
           (u/log ::api-starting)
           (http/run-server app {:port 8080
                                 :legacy-return-value? false}))
  :stop (do
          (u/log ::api-stopping)
          (http/server-stop! api)))

(defn -main [& _args]
  (mount/start))
