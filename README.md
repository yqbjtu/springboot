# springboot
springboot learn


    在Eclispe中启动
  选中DemoApplication.java, run as->Java Application, 然后登录http://localhost:8080

  或者命令行下面C:\E\git_repo\springboot2\springboot\target>java -jar demo-0.0.1-SNAPSHOT.jar

　　the default user and password is 'admin' and 'admin'.
　　以admin登录， 就可以查看http://localhost:8080/admin
　　 而以user1/user1登录，查看http://localhost:8080/admin就会出现Access is denied
  http://localhost:8080
  http://localhost:8080/hello
  http://localhost:8080/users/{username}
  http://localhost:8080/users/zhangsan
  http://localhost:8080/users/lis
  http://localhost:8080/users/zhaoqiansunli周吴郑王  result：Hi zhaoqiansunli周吴郑王
  http://localhost:8080/add/{id}
  http://localhost:8080/add/2
  http://localhost:8080/add/33    result：Add 33, Count:xx
  http://localhost:8080/add/{id}17
  http://localhost:8080/greeting?name=周可
  http://localhost:8080/greeting
  http://localhost:8080/greeting?name=吴用 -- result：{"id":1,"content":"Hello, 吴用!"}

  http://localhost:8080/userDemo?id=001&name=Eric&email=bjxjsx@163.com
  {"id":1,"name":"Eric","email":"bjxjsx@163.com","description":null}
  http://localhost:8080/arrayListIntegerDemo?id=1&id=3&id=7
  {"id":3,"name":"defaultName","email":null,"description":"[1, 3, 7]"}
  http://localhost:8080//arrayDemo?id=1,5,9,34,67
  {"id":5,"name":"defaultName","email":null,"description":"[1, 5, 9, 34, 67]"}

  http://localhost:8080/thymeleafHello
  http://localhost:8080/thymeleafHello2?name=Craig
  http://www.thymeleaf.org/

  http://localhost:8080/qutoe

http://127.0.0.1:8080/demo/add?name=u1&email=yqbjtu@163.com
http://127.0.0.1:8080/demo/all
http://127.0.0.1:8080/demo/delete
http://127.0.0.1:8080/demo/init
http://127.0.0.1:8080/demo/find?name=xxx
http://127.0.0.1:8080/demo/find?name=%E5%BC%A0%E4%B8%89

  For I enable the security and loign with admin with ADMIN/USER roles, but no ACTUATOR role.
  http://127.0.0.1:8080/  have the health check page link.
  when accessing http://127.0.0.1:8080/env
  app will show the following error:
  Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Fri Mar 02 10:46:40 CST 2018
There was an unexpected error (type=Forbidden, status=403).
Access is denied. User must have one of the these roles: ACTUATOR

默认就支持热启动