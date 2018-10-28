#File Upload

http://127.0.0.1:8080
8080一般防火墙都打开着，不用再配置

#http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#common-application-properties
#search multipart

http://www.mkyong.com/spring-boot/spring-boot-file-upload-example/  
https://spring.io/guides/gs/uploading-files/#_tuning_file_upload_limits  


for srping boot 2.0
spring.servlet.multipart.max-file-size=128KB
spring.servlet.multipart.max-request-size=128KB
spring.http.multipart.enabled=false


Embedded containers package structure

In order to support reactive use cases, the embedded containers package structure has been refactored quite extensively. EmbeddedServletContainer has been renamed to WebServer and the org.springframework.boot.context.embedded package has been relocated to org.springframework.boot.web.server. Correspondingly, EmbeddedServletContainerCustomizer has been renamed to WebServerFactoryCustomizer.

For example, if you were customizing the embedded Tomcat container using the TomcatEmbeddedServletContainerFactory callback interface, you should now use TomcatServletWebServerFactory and if you were using an EmbeddedServletContainerCustomizer bean, you should now use a WebServerFactoryCustomizer<TomcatServletWebServerFactory> bean.
