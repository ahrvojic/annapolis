(ns annapolis.leader
  (:import (org.apache.curator.framework CuratorFramework)
           (org.apache.curator.framework.recipes.leader LeaderLatch)))

(defn ^LeaderLatch leader-latch
  [^CuratorFramework framework]
  (LeaderLatch. framework "/annapolis/leader"))

(defn ^LeaderLatch start
  [^CuratorFramework framework]
  (let [latch (leader-latch framework)]
    (.start latch)
    latch))

(defn stop
  [^LeaderLatch latch]
  (.close latch))
