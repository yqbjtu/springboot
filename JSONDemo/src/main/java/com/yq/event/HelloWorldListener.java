package com.yq.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: HelloWorldListener
 *
 * @author EricYang
 * @version 2019/4/20 10:57
 */
@Component
public class HelloWorldListener  implements ApplicationListener<HelloWorldEvent>{
    @Override
    public void onApplicationEvent(HelloWorldEvent event) {
        HelloWorldEvent myEvent = (HelloWorldEvent)event;
        myEvent.print();
        System.out.println("the source is:" + myEvent.getSource());
        System.out.println("the userId is:" + myEvent.getUserId());
        System.out.println("the context is:" + myEvent.getText());
    }
}
