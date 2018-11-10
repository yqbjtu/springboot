package com.yq.config;

import com.yq.service.UserServiceA;
import com.yq.service.UserServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Simple to Introduction
 * className: ConfigB
 *  这个标记包含一个value属性，类型是整型，如：1,2 等等。值越小拥有越高的优先级。

 默认的属性是Ordered.LOWEST_PRECEDENCE，

 代表的是最低优先级。
 * @author EricYang
 * @version 2018/10/21 15:39
 */
@Configuration
@Order(2)
@Slf4j
public class ConfigB {

    //方法名称必须不同，要不然configA和configB只有一个userService方法执行了
    @Bean
    public UserServiceB userServiceB() {
        log.info("ConfigB 执行. order 2");
        return new UserServiceB("order 2. userB");
    }
}