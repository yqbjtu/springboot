package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//springboot 2,.0.x 不能直接使用TomcatConnectorCustomizer
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
/**
 * Simple to Introduction
 * className: FileUploaderApplication
 *
 * @author EricYang
 * @version 2018/10/28 20:44
 */


@SpringBootApplication
@Slf4j
public class FileUploaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploaderApplication.class, args);
        log.info("FileUploaderApplication Start done.");
    }

    //Tomcat large file upload connection reset
    //http://www.mkyong.com/spring/spring-file-upload-and-connection-reset-issue/
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });

        return tomcat;

    }
}