package com.yq.util;

import akka.actor.Extension;
import akka.actor.Props;
import com.yq.actor.SpringActorProducer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: SpringContextHolder
 *
 * @author EricYang
 * @version 2019/2/26 11:12
 */

@Component
public class SpringContextHolder implements Extension, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Create a Props for the specified actorBeanName using the
     * SpringActorProducer class.
     *
     * @param actorBeanName
     *            The name of the actor bean to create Props for
     * @return a Props that will create the named actor bean using Spring
     */
    public Props props(String actorBeanName, Object ... args) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName, args);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
