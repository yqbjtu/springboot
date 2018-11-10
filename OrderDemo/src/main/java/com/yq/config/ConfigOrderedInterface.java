package com.yq.config;

import com.yq.bean.BeanOrderedA;
import com.yq.bean.BeanOrderedB;
import com.yq.service.UserServiceA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Simple to Introduction
 * className: ConfigA
 *
 * @author EricYang
 * @version 2018/10/21 15:39
 */
@Configuration
@Order(3)
@Slf4j
public class ConfigOrderedInterface {

    @Bean
    public BeanOrderedA beanOrderedA() {
        log.info("BeanOrderedA 生成");
        return new BeanOrderedA();
    }

    @Bean
    public BeanOrderedB beanOrderedB() {
        log.info("BeanOrderedB 生成");
        return new BeanOrderedB();
    }
}

