#logback demo

http://127.0.0.1:9090/swagger-ui.html

error 
warn   
info 
debug 
trace 
   
#case1
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
    </root>


    <logger name="com.yq" level="DEBUG">
        <appender-ref ref="FILE" />
    </logger>
```
 结论：  
  com.yq.controller包下面的所有INFO级别以上信息都在FILE配置中
  （虽然controller有一条debug日志，但是因为FILE自身的级别设置为INFO, 即使<logger name="com.yq" level="DEBUG">设置了，日志依然采用INFO），
  ERROR_FILE严格按照自己的配置中的error级别记录日志，而不是root中的debug级别
  console记录了error、warn、info、debug 四个级别的日志
  
    
    
    info1.txt 内容为  
    2018-11-10 13:44:21,199 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
    error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    2018-11-10 13:44:21,200 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
    warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    2018-11-10 13:44:21,200 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
    info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2



    error1.txt内容为  
    2018-11-10 13:44:21,199 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    
    console内容为  
    2018-11-10 13:44:21,199 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
    find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018)
    2018-11-10 13:44:21,199 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
    error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    2018-11-10 13:44:21,200 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
    warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    2018-11-10 13:44:21,200 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
    info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    2018-11-10 13:44:21,209 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-2] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
    debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:44:12 CST 2018) by id=2
    


      
#case2  
```xml
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
    </root>


    <logger name="com.yq.controller" level="ERROR">
        <appender-ref ref="FILE" />
    </logger>
```
结论：  
        com.yq.controller包下面的所有ERROR级别的信息都在FILE配置中（虽然controller有一条debug和warn， info日志，而且FILE自身的级别设置为INFO,   
        但是<logger name="com.yq" level="ERROR">设置了，日志采用ERROR， ）
        ERROR_FILE严格按照自己的配置中的error级别记录日志，而不是root中的debug级别
        console只记录了INFO和ERROR，console自身的基本是debug
        
        
info1.txt内容为
2018-11-10 13:40:11,047 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:39:58 CST 2018) by id=2


error1.txt内容为  
2018-11-10 13:40:11,047 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:39:58 CST 2018) by id=2

console内容为    
2018-11-10 13:40:11,047 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:39:58 CST 2018)
2018-11-10 13:40:11,047 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:39:58 CST 2018) by id=2


      
#case3    
```xml
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
    </root>


    <logger name="com.yq.controller" level="WARN">
        <appender-ref ref="FILE" />
    </logger>
```
结论：  
        com.yq.controller包下面的所有WARN级别以上的信息都在FILE配置中（虽然controller有一条debug和info日志，而且FILE自身的级别设置为INFO,   
        但是<logger name="com.yq" level="WARN">设置了，日志采用WARN ）
        ERROR_FILE严格按照自己的配置中的error级别记录日志，而不是root中的debug级别
        console只记录了INFO和ERROR，WARN，console自身的基本是debug
        
        
info1.txt内容为
2018-11-10 13:48:52,512 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:48:45 CST 2018) by id=2
2018-11-10 13:48:52,513 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:48:45 CST 2018) by id=2


error1.txt内容为  
2018-11-10 13:48:52,512 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:48:45 CST 2018) by id=2

console内容为    
2018-11-10 13:48:52,512 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:48:45 CST 2018)
2018-11-10 13:48:52,512 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:48:45 CST 2018) by id=2
2018-11-10 13:48:52,513 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 13:48:45 CST 2018) by id=2
