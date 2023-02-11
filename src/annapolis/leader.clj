(ns annapolis.leader
  (:import (org.apache.curator.framework CuratorFramework)
           (org.apache.curator.framework.recipes.leader LeaderLatch)))

(defn ^LeaderLatch election
  [^CuratorFramework framework]
  (LeaderLatch. framework "/annapolis/leader"))

(defn ^LeaderLatch start
  [^CuratorFramework framework]
  (let [election (election framework)]
    (.start election)
    election))

(defn stop
  [^LeaderLatch election]
  (.close election))
