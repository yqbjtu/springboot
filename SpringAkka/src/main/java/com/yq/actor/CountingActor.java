package com.yq.actor;

import akka.actor.UntypedActor;
import com.yq.service.CountingService;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * Simple to Introduction
 * className: CountingActor
 *
 * @author EricYang
 * @version 2019/2/26 11:21
 */
@Named("CountingActor")
@Scope("prototype")
public class CountingActor extends UntypedActor {

    public static class Count {
    }

    public static class Get {
    }

    // the service that will be automatically injected
    @Resource
    private CountingService countingService;

    private int count = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Count) {
            count = countingService.increment(count);
        } else if (message instanceof Get) {
            getSender().tell(count, getSelf());
        } else {
            unhandled(message);
        }
    }
}
