(ns annapolis.leader
  (:import (org.apache.curator.framework CuratorFramework)
           (org.apache.curator.framework.recipes.leader LeaderLatch)))

(defn election
  ^LeaderLatch [^CuratorFramework framework]
  (LeaderLatch. framework "/annapolis/leader"))

(defn start
  ^LeaderLatch [^CuratorFramework framework]
  (let [election (election framework)]
    (.start election)
    election))

(defn stop
  [^LeaderLatch election]
  (.close election))

