package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.yq.context.IoTContext;
import com.yq.ruleActor.HelloActor;
import com.yq.ruleActor.HelloFutureActor;

import java.io.IOException;


public class AkkaHelloFutureDemo {
    private static final String AKKA_CONF_FILE_NAME = "actor-system.conf";
    private static final String ACTOR_SYSTEM_NAME = "TimerActorDemo";

    public static void main(String[] args) {
        Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME).withFallback(ConfigFactory.load());
        final ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);
;
        try {
            IoTContext ioTContext = new IoTContext();

            final ActorRef helloActor =
                    system.actorOf(HelloFutureActor.props(), "helloActor");

            helloActor.tell(Integer.valueOf(5), ActorRef.noSender());

            helloActor.tell("exception", ActorRef.noSender());
            helloActor.tell("other Message", ActorRef.noSender());
            helloActor.tell("hello2", ActorRef.noSender());



            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
