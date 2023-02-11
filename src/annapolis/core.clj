(ns annapolis.core
  (:require [annapolis.leader :as leader]
            [annapolis.zk :as zk]
            [com.brunobonacci.mulog :as u]
            [mount.core :as mount :refer [defstate]]
            [org.httpkit.server :as http]
            [reitit.ring :as ring])
  (:gen-class)
  (:import (java.util.concurrent TimeUnit)))

(defn ping [_req]
  (u/log ::ping)
  {:status 200 :body "OK"})

(def handler
  (ring/ring-handler
    (ring/router
      ["/api"
       ["/ping" {:get ping}]])))

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

(defstate leader
  :start (do
           (u/log ::leader-election-starting)
           (leader/start framework))
  :stop (do
          (u/log ::leader-election-stopping)
          (leader/stop leader)))

(defstate api
  :start (do
           (u/log ::api-starting)
           (http/run-server handler {:port 8080
                                     :legacy-return-value? false}))
  :stop (do
          (u/log ::api-stopping)
          (http/server-stop! api)))

(defn -main [& _args]
  (mount/start)
  (if (.await leader 30 TimeUnit/SECONDS)
    (u/log ::node-is-leader)
    (u/log ::node-is-worker)))
