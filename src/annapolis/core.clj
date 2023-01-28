(ns annapolis.core
  (:require [com.brunobonacci.mulog :as u]
            [org.httpkit.server :as http]
            [reitit.ring :as ring]))

(defn ping-handler [_req]
  {:status 200 :body "OK"})

(def app
  (ring/ring-handler
    (ring/router
      ["/api"
       ["/ping" {:get ping-handler}]])))

(defonce server (atom nil))
(defonce logger (atom nil))

(defn start-server [handler config]
  (reset! logger (u/start-publisher! {:type :console}))
  (u/log ::server-starting)
  (reset! server (http/run-server handler config))
  (u/log ::server-started))

(defn stop-server []
  (u/log ::server-stopping)
  (when @server
    (@server :timeout 100)
    (reset! server nil))
  (u/log ::server-stopped)
  (when @logger
    (@logger)
    (reset! logger nil)))

(defn -main [& _args]
  (start-server app {:port 8080}))
