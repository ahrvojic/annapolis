(ns annapolis.server
  (:require [annapolis.service :as service]
            [reitit.ring :as ring]))

(def handler
  (ring/ring-handler
    (ring/router
      ["/api"
       ["/ping" {:get service/ping}]])
    (ring/create-default-handler)))
