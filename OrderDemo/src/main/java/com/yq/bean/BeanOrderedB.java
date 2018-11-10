package com.yq.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * Simple to Introduction
 * className: BeanOrderedA
 *
 * @author EricYang
 * @version 2018/10/24 19:53
 */
@Slf4j
public class BeanOrderedB implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("BeanOrderedB. HIGHEST_PRECEDENCE + 2");
    }

    //数值越小说明优先级越高
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;
    }
}
