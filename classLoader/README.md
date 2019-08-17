#
启动后访问
http://127.0.0.1:8080/swagger-ui.html，  点击swagger中API


# 使用devtools之前的日志
2019-08-17 14:06:34,909 INFO  [DESKTOP-8S2E5H7 http-nio-8080-exec-1] Caller+0	 at com.yq.controller.UserController.testRule(UserController.java:51)
ControllerClassLoader=sun.misc.Launcher$AppClassLoader@18b4aac2
2019-08-17 14:06:34,911 DEBUG [DESKTOP-8S2E5H7 http-nio-8080-exec-1] Caller+0	 at com.yq.service.RuleRunnable.<init>(RuleRunnable.java:49)
RuleRunnable init.
2019-08-17 14:06:34,911 INFO  [DESKTOP-8S2E5H7 http-nio-8080-exec-1] Caller+0	 at com.yq.controller.UserController.testRule(UserController.java:58)
threadClassLoader=TomcatEmbeddedWebappClassLoader
  context: ROOT
  delegate: true
----------> Parent Classloader:
sun.misc.Launcher$AppClassLoader@18b4aac2

...
2019-08-17 14:06:36,030 INFO  [DESKTOP-8S2E5H7 Thread-4] Caller+0	 at com.yq.service.RuleRunnable.run(RuleRunnable.java:82)
obj=User(id=001, name=null, mail=null, regDate=null)
2019-08-17 14:06:36,030 INFO  [DESKTOP-8S2E5H7 Thread-4] Caller+0	 at com.yq.service.RuleRunnable.run(RuleRunnable.java:87)
drools uses classLoader=sun.misc.Launcher$AppClassLoader@18b4aac2, objClassName=com.yq.domain.User

# devtools之后的日志
2019-08-17 13:57:30,710 INFO  [DESKTOP-8S2E5H7 http-nio-8080-exec-2] Caller+0	 at com.yq.controller.UserController.testRule(UserController.java:51)
ControllerClassLoader=org.springframework.boot.devtools.restart.classloader.RestartClassLoader@5c30ced3
2019-08-17 13:57:30,712 DEBUG [DESKTOP-8S2E5H7 http-nio-8080-exec-2] Caller+0	 at com.yq.service.RuleRunnable.<init>(RuleRunnable.java:49)
RuleRunnable init.
2019-08-17 13:57:30,712 INFO  [DESKTOP-8S2E5H7 http-nio-8080-exec-2] Caller+0	 at com.yq.controller.UserController.testRule(UserController.java:58)
threadClassLoader=TomcatEmbeddedWebappClassLoader
  context: ROOT
  delegate: true
----------> Parent Classloader:
org.springframework.boot.devtools.restart.classloader.RestartClassLoader@5c30ced3

2019-08-17 13:57:30,717 DEBUG [DESKTOP-8S2E5H7 Thread-10] Caller+0	 at com.yq.service.RuleRunnable.run(RuleRunnable.java:61)
RuleRunnable start for threadId=42, userId=001
...

2019-08-17 13:57:32,000 INFO  [DESKTOP-8S2E5H7 Thread-10] Caller+0	 at com.yq.service.RuleRunnable.run(RuleRunnable.java:82)
obj=User(id=001, name=null, mail=null, regDate=null)
2019-08-17 13:57:32,000 INFO  [DESKTOP-8S2E5H7 Thread-10] Caller+0	 at com.yq.service.RuleRunnable.run(RuleRunnable.java:90)
objClassLoader=sun.misc.Launcher$AppClassLoader@18b4aac2, userClassLoader=org.springframework.boot.devtools.restart.classloader.RestartClassLoader@5c30ced3, objClassName=com.yq.domain.User
2019-08-17 13:57:32,000 DEBUG [DESKTOP-8S2E5H7 Thread-10] Caller+0	 at com.yq.service.RuleRunnable.run(RuleRunnable.java:98)
RuleRunnable end for userId=001, cost=1287