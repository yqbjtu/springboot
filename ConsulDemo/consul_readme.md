

 http://127.0.0.1:8080/swagger-ui.html
 
https://github.com/Ecwid/consul-api
    <dependency>
    <groupId>com.ecwid.consul</groupId>
    <artifactId>consul-api</artifactId>
    <version>1.1.10</version>
    </dependency>
spring-cloud-consul-core 1.3.0 使用的是com.ecwid.consul
		<dependency>
			<groupId>com.ecwid.consul</groupId>
			<artifactId>consul-api</artifactId>
			<optional>true</optional>
		</dependency>

 
    @Autowired
    private ConsulDiscoveryClient consulDiscoveryClient;
    
https://github.com/OrbitzWorldwide/consul-client，  0.13.10需要jersey
    <dependency>
    <groupId>com.orbitz.consul</groupId>
    <artifactId>consul-client</artifactId>
    <version>0.13.10</version>
    </dependency>
            <!--<dependency>-->
                <!--<groupId>org.glassfish.jersey.core</groupId>-->
                <!--<artifactId>jersey-client</artifactId>-->
                <!--<version>2.22.2</version>-->
            <!--</dependency>-->


这是搭配的一组
```
        <!-- consul-client -->
        <dependency>
            <groupId>com.orbitz.consul</groupId>
            <artifactId>consul-client</artifactId>
            <version>1.2.4</version>
        </dependency>

        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>3.9.0</version>
        </dependency>
```

2.  nodejs
 
  https://github.com/silas/node-consul#documentation
  npm install consul
  
  
3. consul properties
#spring.cloud.consul.discovery.queryPassing=true
加上后discoveryClient只查询heathy的服务
https://github.com/spring-cloud/spring-cloud-consul/blob/master/spring-cloud-consul-discovery/src/main/java/org/springframework/cloud/consul/discovery/ConsulDiscoveryProperties.java