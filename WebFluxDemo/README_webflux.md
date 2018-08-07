##

Consider defining a bean of type 'org.springframework.web.reactive.DispatcherHandler' in your configuration.

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        并且使用
        会引发Consider defining a bean of type 'org.springframework.web.reactive.DispatcherHandler' in your configuration. 错误，导致无法启动
        
        
        
在pom文件中禁用了        <!--&lt;!&ndash;支持 Web 应用开发，包含 Tomcat 和 spring-mvc。 &ndash;&gt;-->
                  <!--<dependency>-->
                      <!--<groupId>org.springframework.boot</groupId>-->
                      <!--<artifactId>spring-boot-starter-web</artifactId>-->
                  <!--</dependency>-->
          
                  <!--<dependency>-->
                      <!--<groupId>org.springframework.boot</groupId>-->
                      <!--<artifactId>spring-boot</artifactId>-->
                  <!--</dependency>-->
就能正常启动，但是启动日志是        
        
        2018-08-07 14:20:02.298  INFO 2596 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Located managed bean 'configurationPropertiesRebinder': registering with JMX server as MBean [org.springframework.cloud.context.properties:name=configurationPropertiesRebinder,context=a4add54,type=ConfigurationPropertiesRebinder]
        2018-08-07 14:20:02.914  INFO 2596 --- [ctor-http-nio-1] r.ipc.netty.tcp.BlockingNettyContext     : Started HttpServer on /0:0:0:0:0:0:0:0:8080
        2018-08-07 14:20:02.915  INFO 2596 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8080
        
        pom中加入
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </dependency>


也会导致
2018-08-07 14:31:33.517  WARN 18824 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
2018-08-07 14:31:33.536  INFO 18824 --- [           main] ConditionEvaluationReportLoggingListener 
Description:

Parameter 0 of method hystrixGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration$HystrixConfiguration required a bean of type 'org.springframework.web.reactive.DispatcherHandler' that could not be found.


Action:

Consider defining a bean of type 'org.springframework.web.reactive.DispatcherHandler' in your configuration.


我们使用(HttpServletRequest ， 原来是引用的的spring-boot-starter-tomcat，
现在不能使用spring-boot-starter-tomcat，需要添加
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        </dependency>
