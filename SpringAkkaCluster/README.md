#spring 与akka cluster集成 demo

http://127.0.0.1:5000/swagger-ui.html

  program arguments:--spring.profiles.active=dev2
  
  如果otherActor发两次，两个otherActor可能不同
  4个node， 一个消息发出去，比如从dev3发出去， 有两份消息被收到了， 这两份可能同时是dev2， 也可能一个dev1一个dev2收到。
  同理， dev2发出去的消，可能被dev0， dev1， dev3收到， 并且每次的消息也是两份
  
  如果otherActor发一次，每次发消息也能被不同的node上的actor收到
  
  从日志可以看出service初始化被bean要早
  2019-03-01 14:53:43,612 INFO  [DESKTOP-8S2E5H7 main] Caller+0	 at com.yq.service.impl.MyContextServiceImpl.<init>(MyContextServiceImpl.java:45)
  initResultInConstructor=false, actorSystem=null
  2019-03-01 14:53:43,640 INFO  [DESKTOP-8S2E5H7 main] Caller+0	 at com.yq.config.ClusterConfigBean.springClusterConfig(ClusterConfigBean.java:32)
  Create a springClusterConfig bean
  2019-03-01 14:53:43,651 INFO  [DESKTOP-8S2E5H7 main] Caller+0	 at com.yq.config.ClusterConfigBean.actorSystem(ClusterConfigBean.java:38)
  create a bean for actorSystem, myClusterConfig=SpringClusterConfig(port=3001)
  2019-03-01 14:53:47,913 INFO  [DESKTOP-8S2E5H7 main] Caller+0	 at org.springframework.boot.StartupInfoLogger.logStarted(StartupInfoLogger.java:59)
  Started SpringAkkaClusterApplication in 10.214 seconds (JVM running for 10.889)
  2019-03-01 14:53:47,916 INFO  [DESKTOP-8S2E5H7 main] Caller+0	 at com.yq.ClusterCLRunner.run(ClusterCLRunner.java:26)
  initialize cluster 
  2019-03-01 14:53:47,919 INFO  [DESKTOP-8S2E5H7 main] Caller+0	 at com.yq.ClusterCLRunner.run(ClusterCLRunner.java:29)
  initResultInCLR=true