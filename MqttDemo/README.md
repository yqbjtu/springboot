#

http://127.0.0.1:5000/swagger-ui.html
https://github.com/eclipse/paho.mqtt.java/issues/9

点击两次subscribe
//threadIdClass和controller的本地运行的线程id一致，每次请求都会在新线程中调用controller对象， controller对象只有一个
MqttCallback在单独的线程中运行
2019-02-21 14:24:10.558  INFO 7716 --- [           main] com.yq.MqttSubApplication                : MqttSubApplication start done.
2019-02-21 14:24:17.935  INFO 7716 --- [nio-5000-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2019-02-21 14:24:17.936  INFO 7716 --- [nio-5000-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2019-02-21 14:24:17.956  INFO 7716 --- [nio-5000-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 19 ms
2019-02-21 14:24:18.330  INFO 7716 --- [nio-5000-exec-1] com.yq.controller.OpsController          : {"threadId":20,"currentTime":"2019-02-21T14:24:18.015","controller":"com.yq.controller.OpsController@6878bae5","subscribeResult":"OK. threadId=20"}
2019-02-21 14:24:18.443  INFO 7716 --- [Call: yqClient0] com.yq.service.MyMqttCallback            : msg arrived. topic=topic01, QoS=1, msgBody={"deviceId":"abc", "msg":{"data":{"sensor1":"abc2"}} }. threadIdClass=20, threadId=38
2019-02-21 14:24:21.275  INFO 7716 --- [nio-5000-exec-2] com.yq.controller.OpsController          : {"threadId":21,"currentTime":"2019-02-21T14:24:21.218","controller":"com.yq.controller.OpsController@6878bae5","subscribeResult":"OK. threadId=21"}
2019-02-21 14:24:21.327  INFO 7716 --- [Call: yqClient1] com.yq.service.MyMqttCallback            : msg arrived. topic=topic01, QoS=1, msgBody={"deviceId":"abc", "msg":{"data":{"sensor1":"abc2"}} }. threadIdClass=21, threadId=43
2019-02-21 14:24:29.775  INFO 7716 --- [Call: yqClient0] com.yq.service.MyMqttCallback            : msg arrived. topic=topic01, QoS=1, msgBody=qqq7. threadIdClass=20, threadId=38
2019-02-21 14:24:29.775  INFO 7716 --- [Call: yqClient1] com.yq.service.MyMqttCallback            : msg arrived. topic=topic01, QoS=1, msgBody=qqq7. threadIdClass=21, threadId=43