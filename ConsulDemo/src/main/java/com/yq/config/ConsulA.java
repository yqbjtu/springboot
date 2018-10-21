package com.yq.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: ConsulConfig
 *  从配置文件中获取配置使用@value，当你在bean加载时调用@value时会出现空指针异常。因为bean加载完成后才会执行@value。
 * @author EricYang
 * @version 2018/9/1 10:04
 */

@Data
@Order(1)
@Slf4j
@Component
public class ConsulA {
    @Autowired
    private ApplicationContext context;

    public ConsulA() {
       log.info("ConsulConfigA启动初始化。。。 context={}", context);
    }
}