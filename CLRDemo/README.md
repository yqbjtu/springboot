#CLRDEmo

http://127.0.0.1:9091/swagger-ui.html


https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/html/boot-features-spring-application.html#boot-features-command-line-runner

  You can additionally implement the org.springframework.core.Ordered interface or  
  use the org.springframework.core.annotation.Order annotation if several CommandLineRunner or  
  ApplicationRunner beans are defined that must be called in a specific order.
  
  If you need to run some specific code once the SpringApplication has started,  
   you can implement the ApplicationRunner or CommandLineRunner interfaces.   
   Both interfaces work in the same way and offer a single run method which will be called just before SpringApplication.run(…​) completes.
  The CommandLineRunner interfaces provides access to application arguments as a simple string array,   
  whereas the ApplicationRunner uses the ApplicationArguments interface discussed above.  
  总结起来就是当SpringApplication启动后需要运行一次，他们会在SpringApplication.run(…​)方法完成前被调用。
  两者差别在于CommandLineRunner以字符串数组的方式访问应用的参数，而ApplicationRunner以ApplicationArguments方式访问应用的参数
  
  
  https://www.mkyong.com/java/jce-encryption-data-encryption-standard-des-tutorial/  
  
  https://docs.oracle.com/javase/8/
  
  https://docs.oracle.com/javase/8/docs/technotes/guides/security/index.html  
  
  https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#SecretKeyFactory  
  
  