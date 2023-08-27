(ns annapolis.zk
  (:import (org.apache.curator.framework CuratorFramework CuratorFrameworkFactory)
           (org.apache.curator.retry ExponentialBackoffRetry)
           (org.apache.curator.utils CloseableUtils)))

(defn framework
  ^CuratorFramework []
  (let [zk-host (System/getenv "ZK_HOST")
        zk-port (System/getenv "ZK_PORT")
        retries-sleep-ms 1000
        retries-max 3]
    (-> (CuratorFrameworkFactory/builder)
        (.connectString (str zk-host ":" zk-port))
        (.retryPolicy (ExponentialBackoffRetry. retries-sleep-ms retries-max))
        (.build))))

(defn start
  ^CuratorFramework [^CuratorFramework framework]
  (.start framework)
  framework)

(defn stop
  [^CuratorFramework framework]
  (CloseableUtils/closeQuietly framework))

