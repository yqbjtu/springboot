package com.yq.context;

import akka.actor.ActorRef;
import lombok.Data;

/**
 * Simple to Introduction
 * className: IoTContext
 *
 * @author EricYang
 * @version 2018/12/25 9:26
 */
@Data
public class IoTContext {
    private ActorRef createAlarmActor;
    private ActorRef sendMailActor;
}
