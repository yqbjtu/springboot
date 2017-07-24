# springboot
springboot learn


pom.xml 如果使用1.5.4.RELEASE， 在eclispe中无法启动tomcat，错误信息。 修改为1.3.6就可以在eclipse中启动了
Spring boot app: java.lang.NoSuchMethodError: org.springframework.web.filter.CharacterEncodingFilter.setForceRequestEncoding

<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    
    在Eclispe中启动
  选中DemoApplication.java, run as->Java Application, 然后登录http://localhost:8080/hello
  
  或者命令行下面C:\E\git_repo\springboot2\springboot\target>java -jar demo-0.0.1-SNAPSHOT.jar