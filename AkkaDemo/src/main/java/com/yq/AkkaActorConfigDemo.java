package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.TypedActor;
import com.typesafe.config.ConfigFactory;
import com.yq.context.IoTContext;
import com.yq.ruleActor.HotSwapActor;

import java.io.IOException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
public class AkkaActorConfigDemo {
    private static final String AKKA_CONF_FILE_NAME = "actor-system.conf";
    private static final String ACTOR_SYSTEM_NAME = "TimerActorDemo";

    public static void main(String[] args) {
        Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME).withFallback(ConfigFactory.load());
        final ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);

        try {

            ActorRef hotSwapActor =
                    system.actorOf(Props.create(HotSwapActor.class).withDispatcher("my-dispatcher"),
                            "myactor");

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
