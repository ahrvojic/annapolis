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

(defn stop-server []
  (when @logger (@logger))
  (when @server
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& _args]
  (reset! logger (u/start-publisher! {:type :console}))
  (u/log ::server-starting)
  (reset! server (http/run-server #'app {:port 8080}))
  (u/log ::server-started))
