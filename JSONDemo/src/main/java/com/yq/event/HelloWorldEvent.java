package com.yq.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Simple to Introduction
 * className: HelloWorldEvent
 *
 * @author EricYang
 * @version 2019/4/20 10:54
 */
@Data
public class HelloWorldEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String text;

    public HelloWorldEvent(Object source) {
        super(source);
    }

    public HelloWorldEvent(Object source, String userId, String text) {
        super(source);
        this.userId = userId;
        this.text = text;
    }

    public void print() {
        System.out.println("hello spring event!");
    }
}
