package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.yq.context.IoTContext;
import com.yq.ruleActor.TimerActor;

import java.io.IOException;


public class AkkaTimerActorDemo {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("TimerActorDemo");
        try {
            IoTContext ioTContext = new IoTContext();

            final ActorRef timerActor =
                    system.actorOf(TimerActor.props(), "timerActor");

            timerActor.tell(new TimerActor.FirstTick(), ActorRef.noSender());

            timerActor.tell(new TimerActor.Tick(), ActorRef.noSender());



            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
