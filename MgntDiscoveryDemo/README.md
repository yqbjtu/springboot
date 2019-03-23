#

配置文件的读取
 This means roughly that the default is to parse all application.conf, application.json and application.properties found at the root of the class path—please refer to the aforementioned documentation for details. The actor system then merges in all reference.conf resources found at the root of the class path to form the fallback configuration, i.e. it internally uses
 
 appConfig.withFallback(ConfigFactory.defaultReference(classLoader))
 
 
 akka mgnt rest
 https://github.com/akka/akka-management/blob/v1.0.0/docs/src/main/paradox/cluster-http-management.md
 
 Automatically joining to seed nodes with Cluster Bootstrap
 Instead of manually configuring seed nodes, which is useful in development or statically assigned node IPs, you may want to automate the discovery of seed nodes using your cloud providers or cluster orchestrator, or some other form of service discovery (such as managed DNS). The open source Akka Management library includes the Cluster Bootstrap module which handles just that. Please refer to its documentation for more details.
   
  https://doc.akka.io/docs/akka/current/cluster-usage.html#automatically-joining-to-seed-nodes-with-cluster-bootstrap  
 
 http://192.168.29.1:8558/bootstrap/seed-nodes
 暴露了{"seedNodes":[],"selfNode":"akka.tcp://ClusterDemo@0.0.0.0:3001"}
 
 https://developer.lightbend.com/docs/akka-management/current/akka-management.html
 akka.management.http.hostname = "127.0.0.1"
 akka.management.http.port = 8558
 