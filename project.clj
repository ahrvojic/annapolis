(defproject annapolis "0.1.0-SNAPSHOT"
  :description "Kubernetes distributed application lab"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.brunobonacci/mulog "0.9.0"]
                 [com.brunobonacci/mulog-adv-console "0.9.0"]
                 [http-kit "2.6.0"]
                 [metosin/reitit-ring "0.5.18"]
                 [mount/mount "0.1.17"]
                 [nonseldiha/slf4j-mulog "0.2.1"]
                 [org.apache.curator/curator-recipes "5.3.0"]]
  :exclusions [log4j]
  :main annapolis.core
  :profiles {:uberjar {:aot [annapolis.core]}})
