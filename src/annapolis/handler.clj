(ns annapolis.handler
  (:require [annapolis.service :as service]
            [reitit.ring :as ring]))

(def app
  (ring/ring-handler
    (ring/router
      ["/api"
       ["/ping" {:get service/ping}]])
    (ring/create-default-handler)))
