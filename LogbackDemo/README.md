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


#case4
使用logback-spring2.xml， 也就是FILE中没有定义级别
```xml
    <!-- 文件输出 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <!-- 如果编辑器不支持请用普通日志 CONSOLE_LOG_PATTERN-彩色日志 FILE_LOG_PATTERN-普通日志 -->
            <pattern>%date %-5level [${HOSTNAME} %thread] %caller{1}%msg%n</pattern><!-- 格式化输出 -->
            <charset>utf8</charset> <!-- 输出编码 -->
        </encoder>
        <file>./logs/info1.log</file><!-- 文件存放路径 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>/logs/info/%d{yyyy-MM-dd}.log</fileNamePattern><!-- 每天归档 -->
            <maxHistory>7</maxHistory><!-- 日志存放周期（天） -->
        </rollingPolicy>
    </appender>
```

然后配置
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
    </root>


    <logger name="com.yq.controller" level="DEBUG" additivity="false">
        <appender-ref ref="FILE" />
    </logger>
```

结论  
    com.yq.controller包下面的所有DEBUG级别以上信息都在FILE配置中
    （虽然controller有一条trace日志，但是因为FILE自身的级别没有设置, 而<logger name="com.yq" level="DEBUG">设置了，日志依然采用DEBUG），
    ERROR_FILE严格按照自己的配置中的error级别记录日志，而不是root中的debug级别
    console没有记录com.yq.controller包下面error、warn、info、debug 四个级别的日志
    
info1.txt内容为
2018-11-10 14:49:18,900 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:49:12 CST 2018) by id=2
2018-11-10 14:49:18,900 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:49:12 CST 2018) by id=2
2018-11-10 14:49:18,900 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:49:12 CST 2018) by id=2
2018-11-10 14:49:18,909 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:49:12 CST 2018) by id=2


error1.txt内容为  
2018-11-10 14:49:14,671 ERROR [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:19)
error LogbackApplication Start done.


console内容为    
2018-11-10 14:50:44,533 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-3] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:49:12 CST 2018)


#case5
使用logback-spring2.xml， 也就是FILE中没有定义级别

然后配置
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
        <appender-ref ref="FILE" />
    </root>


    <logger name="com.yq.controller" level="DEBUG" additivity="false">
        <appender-ref ref="FILE" />
    </logger>
```

结论  
    info1.txt记录了很多springboot的日志包括debug级别的日志， 而com.yq部分只记录了com.yq.controller和com.yq.service包下面的所有DEBUG级别以上信息都在FILE配置中
    （虽然controller有一条trace日志，但是因为FILE自身的级别没有设置, 而<logger name="com.yq" level="DEBUG">设置了，日志依然采用DEBUG），
    ERROR_FILE严格按照自己的配置中的error级别记录日志，而不是root中的debug级别
    console没有记录com.yq.controller包下面error、warn、info、debug 四个级别的日志, 但记录了com.yq.service下面四个基本的日志
    
info1.txt内容为
...
Servlet 'dispatcherServlet' configured successfully
2018-11-10 15:00:27,425 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.filter.RequestContextFilter.initContextHolders(RequestContextFilter.java:114)
Bound request context to thread: org.apache.catalina.connector.RequestFacade@4abb116f
2018-11-10 15:00:27,426 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:869)
DispatcherServlet with name 'dispatcherServlet' processing GET request for [/user/users/2]
2018-11-10 15:00:27,429 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:310)
Looking up handler method for path /user/users/2
2018-11-10 15:00:27,429 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping.lookupHandlerMethod(PropertySourcedRequestMappingHandlerMapping.java:109)
looking up handler for path: /user/users/2
2018-11-10 15:00:27,430 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:320)
Did not find handler method for [/user/users/2]
2018-11-10 15:00:27,430 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:310)
Looking up handler method for path /user/users/2
2018-11-10 15:00:27,435 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:317)
Returning handler method [public com.yq.domain.User com.yq.controller.UserController.getUser(java.lang.String)]
2018-11-10 15:00:27,436 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'userController'
2018-11-10 15:00:27,438 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:955)
Last-Modified value for [/user/users/2] is: -1
2018-11-10 15:00:27,442 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.cors.DefaultCorsProcessor.processRequest(DefaultCorsProcessor.java:77)
Skip CORS processing: request is from same origin
2018-11-10 15:00:27,455 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
error find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,455 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:41)
warn find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,455 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:42)
info find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,455 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:43)
debug find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,456 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018) by id=2
2018-11-10 15:00:27,456 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018) by id=2
2018-11-10 15:00:27,456 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018) by id=2
2018-11-10 15:00:27,456 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018) by id=2
2018-11-10 15:00:27,494 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:234)
Written [User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@2bdeda33]
2018-11-10 15:00:27,496 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1048)
Null ModelAndView returned to DispatcherServlet with name 'dispatcherServlet': assuming HandlerAdapter completed request handling
2018-11-10 15:00:27,497 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1000)
Successfully completed request
2018-11-10 15:00:27,500 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:104)
Cleared thread-bound request context: org.apache.catalina.connector.RequestFacade@4abb116f

console内容为  
2018-11-10 15:00:27,455 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
error find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,455 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:41)
warn find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,455 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:42)
info find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)
2018-11-10 15:00:27,455 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:43)
debug find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:00:20 CST 2018)


#case6  
使用logback-spring2.xml， 也就是FILE中没有定义级别  

然后配置  
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
        <appender-ref ref="FILE" />
    </root>


    <logger name="com.yq.controller" level="DEBUG" additivity="false">
        <appender-ref ref="FILE" />
    </logger>
```

结论  
    info1.txt记录了很多springboot的日志包括debug级别的日志， 而com.yq部分只记录了com.yq.controller包下面的所有DEBUG级别以上信息都在FILE配置中
    （虽然controller有一条trace日志，但是因为FILE自身的级别没有设置, 而<logger name="com.yq" level="DEBUG">设置了，日志依然采用DEBUG），
    ERROR_FILE严格按照自己的配置中的error级别记录日志，而不是root中的debug级别
    console没有记录com.yq.controller包下面error、warn、info、debug 四个级别的日志
    
info1.txt内容为  
...
Unable to locate FlashMapManager with name 'flashMapManager': using default [org.springframework.web.servlet.support.SessionFlashMapManager@4b9aba65]
2018-11-10 14:54:42,057 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.FrameworkServlet.initWebApplicationContext(FrameworkServlet.java:568)
Published WebApplicationContext of servlet 'dispatcherServlet' as ServletContext attribute with name [org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet]
2018-11-10 14:54:42,058 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.FrameworkServlet.initServletBean(FrameworkServlet.java:508)
FrameworkServlet 'dispatcherServlet': initialization completed in 36 ms
2018-11-10 14:54:42,058 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:174)
Servlet 'dispatcherServlet' configured successfully
2018-11-10 14:54:42,072 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.filter.RequestContextFilter.initContextHolders(RequestContextFilter.java:114)
Bound request context to thread: org.apache.catalina.connector.RequestFacade@57ab6981
2018-11-10 14:54:42,074 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:869)
DispatcherServlet with name 'dispatcherServlet' processing GET request for [/user/users/2]
2018-11-10 14:54:42,078 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:310)
Looking up handler method for path /user/users/2
2018-11-10 14:54:42,078 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping.lookupHandlerMethod(PropertySourcedRequestMappingHandlerMapping.java:109)
looking up handler for path: /user/users/2
2018-11-10 14:54:42,079 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:320)
Did not find handler method for [/user/users/2]
2018-11-10 14:54:42,080 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:310)
Looking up handler method for path /user/users/2
2018-11-10 14:54:42,084 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:317)
Returning handler method [public com.yq.domain.User com.yq.controller.UserController.getUser(java.lang.String)]
2018-11-10 14:54:42,084 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'userController'
2018-11-10 14:54:42,086 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:955)
Last-Modified value for [/user/users/2] is: -1
2018-11-10 14:54:42,089 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.cors.DefaultCorsProcessor.processRequest(DefaultCorsProcessor.java:77)
Skip CORS processing: request is from same origin
2018-11-10 14:54:42,103 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:54:31 CST 2018)
2018-11-10 14:54:42,104 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:54:31 CST 2018) by id=2
2018-11-10 14:54:42,104 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:54:31 CST 2018) by id=2
2018-11-10 14:54:42,104 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:54:31 CST 2018) by id=2
2018-11-10 14:54:42,105 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:54:31 CST 2018) by id=2
2018-11-10 14:54:42,140 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:234)
Written [User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 14:54:31 CST 2018)] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@21da8b58]
2018-11-10 14:54:42,141 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1048)
Null ModelAndView returned to DispatcherServlet with name 'dispatcherServlet': assuming HandlerAdapter completed request handling
2018-11-10 14:54:42,142 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1000)
Successfully completed request
2018-11-10 14:54:42,144 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:104)
Cleared thread-bound request context: org.apache.catalina.connector.RequestFacade@57ab6981



#case7  
使用logback-spring2.xml， 也就是FILE中没有定义级别  

然后配置  
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
        <appender-ref ref="FILE" />
    </root>


    <logger name="com.yq.controller" level="trace" additivity="false">
        <appender-ref ref="FILE" />
    </logger>

    <logger name="com.yq.service" level="info" additivity="false">
        <appender-ref ref="FILE" />
    </logger>
```

结论   
    info1.txt记录了很多springboot的日志包括debug级别的日志， 而com.yq部分记录了debug部分  
    com.yq.controller包下面的所有trace级别以上信息   
    com.yq.service包下面的所有info级别以上的信息  
    console中controller包和servcie包都没有记录  
    
info1.txt内容为  
2018-11-10 15:14:15,189 ERROR [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:19)
error LogbackApplication Start done.
2018-11-10 15:14:15,189 WARN  [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:20)
warn LogbackApplication Start done.
2018-11-10 15:14:15,189 INFO  [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:21)
info LogbackApplication Start done.
2018-11-10 15:14:15,189 DEBUG [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:22)
debug LogbackApplication Start done.

。。。

2018-11-10 15:14:28,198 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.handler.AbstractHandlerMethodMapping.getHandlerInternal(AbstractHandlerMethodMapping.java:317)
Returning handler method [public com.yq.domain.User com.yq.controller.UserController.getUser(java.lang.String)]
2018-11-10 15:14:28,199 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'userController'
2018-11-10 15:14:28,199 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:955)
Last-Modified value for [/user/users/2] is: -1
2018-11-10 15:14:28,205 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.cors.DefaultCorsProcessor.processRequest(DefaultCorsProcessor.java:77)
Skip CORS processing: request is from same origin
2018-11-10 15:14:28,215 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
error find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018)
2018-11-10 15:14:28,215 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:41)
warn find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018)
2018-11-10 15:14:28,215 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:42)
info find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018)
2018-11-10 15:14:28,215 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018) by id=2
2018-11-10 15:14:28,215 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018) by id=2
2018-11-10 15:14:28,216 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018) by id=2
2018-11-10 15:14:28,216 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018) by id=2
2018-11-10 15:14:28,216 TRACE [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:41)
trace rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:14:12 CST 2018) by id=2

error1.txt内容为  
2018-11-10 15:14:15,189 ERROR [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:19)
error LogbackApplication Start done.

console中controller和servcie都没有记录


#case8  
使用logback-spring2.xml， 也就是FILE中没有定义级别  

然后配置  
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
        <appender-ref ref="FILE" />
    </root>


    <logger name="com.yq.controller" level="trace" additivity="false">
        <appender-ref ref="FILE" />
    </logger>

    <logger name="com.yq.service" level="info" additivity="false">
        <appender-ref ref="FILE" />
    </logger>

    <logger name="com.yq" level="warn" additivity="false">
        <appender-ref ref="FILE" />
    </logger>
```

结论   
    info1.txt记录了很多springboot的日志包括debug级别的日志， 而com.yq部分  
     com.yq记录了error和warn（<logger name="com.yq" level="warn" additivity="false">规定级别）
    com.yq.controller包下面的所有trace级别以上信息   
    com.yq.service包下面的所有info级别以上的信息  
    error1.txt为空
    console中controller包和servcie包都没有记录  
    
info1.txt内容为  
2018-11-10 15:09:23,444 ERROR [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:19)
error LogbackApplication Start done.
2018-11-10 15:09:23,444 WARN  [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:20)
warn LogbackApplication Start done.

。。。

Returning cached instance of singleton bean 'userController'
2018-11-10 15:09:27,840 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:955)
Last-Modified value for [/user/users/2] is: -1
2018-11-10 15:09:27,847 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.cors.DefaultCorsProcessor.processRequest(DefaultCorsProcessor.java:77)
Skip CORS processing: request is from same origin
2018-11-10 15:09:27,862 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
error find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018)
2018-11-10 15:09:27,863 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:41)
warn find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018)
2018-11-10 15:09:27,863 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:42)
info find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018)
2018-11-10 15:09:27,863 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018) by id=2
2018-11-10 15:09:27,863 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018) by id=2
2018-11-10 15:09:27,864 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018) by id=2
2018-11-10 15:09:27,864 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018) by id=2
2018-11-10 15:09:27,864 TRACE [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:41)
trace rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018) by id=2
2018-11-10 15:09:27,900 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:234)
Written [User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:09:21 CST 2018)] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@4d98dac5]
2018-11-10 15:09:27,900 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1048)
Null ModelAndView returned to DispatcherServlet with name 'dispatcherServlet': assuming HandlerAdapter completed request handling
2018-11-10 15:09:27,900 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1000)
Successfully completed request
2018-11-10 15:09:27,902 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:104)
Cleared thread-bound request context: org.apache.catalina.connector.RequestFacade@d523a78



error1.txt为空
console中controller和servcie都没有记录



#case9  
使用logback-spring2.xml， 也就是FILE中没有定义级别  

然后配置  
```xml
    <!-- 基础日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
    </root>


    <logger name="com.yq.controller" level="trace" additivity="false">
        <appender-ref ref="FILE" />
    </logger>

    <logger name="com.yq.service" level="info" additivity="false">
        <appender-ref ref="FILE" />
    </logger>

    <logger name="org.springframework.beans" level="debug" additivity="false">
        <appender-ref ref="FILE" />
    </logger>
```

结论   
    info1.txt记录了很多springboot的日志包括debug级别的日志， 
        com.yq.controller包下面的所有trace级别以上信息   
        com.yq.service包下面的所有info级别以上的信息  
        org.springframework.beans包下面的所有debug级别以上的信息  
    error1.txt只记录com.yq.LogbackApplication的错误日志，没有com.yq.controller和com.yq.controller的错误
    console中controller包和servcie包都没有记录， 只有springboot的com.yq.LogbackApplication的日志  
    
info1.txt内容为  
Returning cached instance of singleton bean 'mvcViewResolver'
2018-11-10 15:23:26,769 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'defaultViewResolver'
2018-11-10 15:23:26,769 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'viewResolver'
2018-11-10 15:23:26,769 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'thymeleafViewResolver'
2018-11-10 15:23:26,770 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:449)
Creating instance of bean 'org.springframework.web.servlet.support.SessionFlashMapManager'
2018-11-10 15:23:26,776 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:485)
Finished creating instance of bean 'org.springframework.web.servlet.support.SessionFlashMapManager'
2018-11-10 15:23:26,807 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:251)
Returning cached instance of singleton bean 'userController'
2018-11-10 15:23:26,829 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:40)
error find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018)
2018-11-10 15:23:26,830 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:41)
warn find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018)
2018-11-10 15:23:26,830 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.service.impl.UserServiceImpl.getById(UserServiceImpl.java:42)
info find user=2 by id=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018)
2018-11-10 15:23:26,830 ERROR [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:37)
error rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018) by id=2
2018-11-10 15:23:26,830 WARN  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:38)
warn rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018) by id=2
2018-11-10 15:23:26,830 INFO  [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:39)
info rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018) by id=2
2018-11-10 15:23:26,830 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:40)
debug rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018) by id=2
2018-11-10 15:23:26,830 TRACE [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at com.yq.controller.UserController.getUser(UserController.java:41)
trace rest get user=User(id=2, name=Tom2, mail=qq2@163.com, regDate=Sat Nov 10 15:23:18 CST 2018) by id=2  

error1.txt内容为  
2018-11-10 15:23:20,428 ERROR [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:19)
error LogbackApplication Start done.


控制台的内容为  
Starting application com.yq.LogbackApplication with URLs [file:/D:/E/workspaceGitub/springboot/LogbackDemo/target/classes/]
2018-11-10 15:23:20,428 INFO  [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at org.springframework.boot.StartupInfoLogger.logStarted(StartupInfoLogger.java:57)
Started LogbackApplication in 6.774 seconds (JVM running for 7.439)
2018-11-10 15:23:20,428 ERROR [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:19)
error LogbackApplication Start done.
2018-11-10 15:23:20,428 WARN  [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:20)
warn LogbackApplication Start done.
2018-11-10 15:23:20,428 INFO  [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:21)
info LogbackApplication Start done.
2018-11-10 15:23:20,428 DEBUG [DESKTOP-8S2E5H7 restartedMain] Caller+0	 at com.yq.LogbackApplication.main(LogbackApplication.java:22)
debug LogbackApplication Start done.
2018-11-10 15:23:26,749 DEBUG [DESKTOP-8S2E5H7 http-nio-9090-exec-1] Caller+0	 at org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:149)
Initializing servlet 'dispatcherServlet'  