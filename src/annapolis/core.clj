(ns annapolis.core
  (:require [annapolis.handler :as handler]
            [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

(def config
  {:adapter/jetty {:port 8080
                   :join? false
                   :handler handler/app}})

(defmethod ig/init-key :adapter/jetty [_ {:keys [port join? handler]}]
  (log/info "Starting server on port " port "...")
  (jetty/run-jetty handler {:port port :join? join?}))

(defmethod ig/halt-key! :adapter/jetty [_ server]
  (log/info "Stopping server...")
  (.stop server))

(defn start []
  (log/info "Initializing server...")
  (ig/init config))

(defn -main [& _args]
  (start))
