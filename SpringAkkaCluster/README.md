#spring 与akka cluster集成 demo

http://127.0.0.1:5000/swagger-ui.html

  program arguments:--spring.profiles.active=dev2
  
  如果otherActor发两次，两个otherActor可能不同
  4个node， 一个消息发出去，比如从dev3发出去， 有两份消息被收到了， 这两份可能同时是dev2， 也可能一个dev1一个dev2收到。
  同理， dev2发出去的消，可能被dev0， dev1， dev3收到， 并且每次的消息也是两份
  
  如果otherActor发一次，每次发消息也能被不同的node上的actor收到