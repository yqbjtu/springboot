#spring 与akka remote acotr集成 demo

http://127.0.0.1:7001/swagger-ui.html

  program arguments:--spring.profiles.active=dev2
  
#  1, 依赖包
      <dependency>
        <groupId>com.typesafe.akka</groupId>
        <artifactId>akka-remote_2.12</artifactId>
        <version>2.5.21</version>
      </dependency>
      
      如果没有的话会报如下错误
Caused by: java.lang.ClassNotFoundException: akka.remote.RemoteActorRefProvider

#  查询

ActorSelection selection =
  context.actorSelection("akka.tcp://app@127.0.0.1:2552/user/serviceA/worker");
  