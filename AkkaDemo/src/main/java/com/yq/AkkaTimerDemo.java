package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.yq.context.IoTContext;
import com.yq.ruleActor.MyTimerActor;

import java.io.IOException;


public class AkkaTimerDemo {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("AlarmDemo");
        try {
            IoTContext ioTContext = new IoTContext();

            final ActorRef timerActor =
                    system.actorOf(MyTimerActor.props(1), "timerActor");

            final ActorRef timerActor3 =
                    system.actorOf(MyTimerActor.props(3), "timer3Actor");

            timerActor.tell("done1", ActorRef.noSender());
            timerActor3.tell("done3", ActorRef.noSender());




            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
