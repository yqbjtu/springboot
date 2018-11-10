package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: MyCommandLineRunner
 *
 * @author EricYang
 * @version 2018/11/10 11:15
 */

@Slf4j
@Component
public class MyCommandLineRunnerOne implements CommandLineRunner, ApplicationContextAware {
    private ApplicationContext applicationContext = null;

    @Override
    public void run(String... args) throws Exception {
        log.info("info myCLR one");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
        log.info("set applicationContext one");
    }
}
