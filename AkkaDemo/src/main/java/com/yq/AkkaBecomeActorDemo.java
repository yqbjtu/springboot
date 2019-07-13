package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.yq.context.IoTContext;
import com.yq.ruleActor.HotSwapActor;

import java.io.IOException;


public class AkkaBecomeActorDemo {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("TimerActorDemo");
        try {
            IoTContext ioTContext = new IoTContext();

            //Props superprops = Props.create(TypedActor.Supervisor.class);
            //ActorRef supervisor = system.actorOf(superprops, "supervisor");

            ActorRef hotSwapActor = system.actorOf(Props.create(HotSwapActor.class), "hotSwapActor");

            hotSwapActor.tell("foo",ActorRef.noSender());

            hotSwapActor.tell("bar",ActorRef.noSender());
            hotSwapActor.tell("foo",ActorRef.noSender());
            hotSwapActor.tell("bar",ActorRef.noSender());


            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
