# springboot
springboot learn

https://docs.spring.io/spring-data/jpa/docs/1.11.0.RELEASE/reference/html/  

    在Eclispe中启动
  选中DemoApplication.java, run as->Java Application, 然后登录http://localhost:8080  

  或者命令行下面C:\E\git_repo\springboot2\springboot\target>java -jar demo-0.0.1-SNAPSHOT.jar  

　　the default user and password is 'admin' and 'admin'.  
　　以admin登录， 就可以查看http://localhost:8080/admin  
　　 而以user1/user1登录，查看http://localhost:8080/admin就会出现Access is denied  
  http://localhost:8080  

domain对象是quickCD的domain
  PathVariableDemo  
  http://localhost:8080/pathVarDemo/{username}  
  http://localhost:8080/pathVarDemo/zhangsan  
  http://localhost:8080/pathVarDemo/lis  
  http://localhost:8080/pathVarDemo/zhaoqiansunli周吴郑王  result：Hi zhaoqiansunli周吴郑王  
  
  http://localhost:8080/pathVarDemo/{id}  
  http://localhost:8080/pathVarDemo/2  
  http://localhost:8080/pathVarDemo/33    result：Add 33, Count:xx  
  http://localhost:8080/pathVarDemo/{id}17  
  
  http://localhost:8080/greeting?name=周可  
  http://localhost:8080/greeting  
  http://localhost:8080/greeting?name=吴用 -- result：{"id":1,"content":"Hello, 吴用!"}  
  
  RequestPara demo  
  http://localhost:8080/requestParaDemo/userDemo?id=001&name=Eric&email=bjxjsx@163.com   
  {"id":1,"name":"Eric","email":"bjxjsx@163.com","description":null}  
  http://localhost:8080/requestParaDemo/arrayListIntegerDemo?id=1&id=3&id=7  
  {"id":3,"name":"defaultName","email":null,"description":"[1, 3, 7]"}  
  http://localhost:8080/requestParaDemo/arrayDemo?id=1,5,9,34,67  
  {"id":5,"name":"defaultName","email":null,"description":"[1, 5, 9, 34, 67]"}  

  thymeleaf demo  
  http://localhost:8080/thymeleafHello  
  http://localhost:8080/thymeleafHello2?name=Craig  
  http://www.thymeleaf.org/  

  http://localhost:8080/qutoe  

http://127.0.0.1:8080/user/add?name=u1&email=yqbjtu@163.com  
http://127.0.0.1:8080/user/all  
http://127.0.0.1:8080/user/delete  
http://127.0.0.1:8080/user/init  
http://127.0.0.1:8080/user/find?name=xxx  
http://127.0.0.1:8080/user/find?name=%E5%BC%A0%E4%B8%89  

Pageable demo  
http://localhost:8080/user/pages?pageNumber=1&pageSize=10  

http://localhost:8080/agentpool/findByName?name=
http://localhost:8080/agentpool/all
http://localhost:8080/agentpool/getAgentUuidByAgentPoolUuid?poolUuid=


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

2018-03-13 16:33:56,511 INFO  com.yq.demo.runner.myAppRunner01 - my second apprunner. order is 1. AppArgs:  
2018-03-13 16:33:56,511 INFO  com.yq.demo.runner.MyRunner01 - my first runner order is 1. args:  
2018-03-13 16:33:56,511 INFO  com.yq.demo.runner.myAppRunner02 - my second apprunner. order is 2. AppArgs:  
2018-03-13 16:33:56,511 INFO  com.yq.demo.runner.MyRunner02 - my second runner order is 2. args:  


只有启动netty的情况下，下面才能运行，
package com.yq.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by yangqian on 2018/8/7.
 */
@Configuration
public class WebFluxRoutes {
    public static final String RESULT = "flux";

    @Bean(name = "flux-01")
    public RouterFunction<ServerResponse> webFluxGet() {
        Mono<String> date = Mono.just(RESULT);
        return RouterFunctions.route(RequestPredicates.path("/p1/flux/get"), request -> ServerResponse.ok().body(date, String.class));
    }
}