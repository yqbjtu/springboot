package sample.cluster.simple;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import sample.cluster.factorial.FactorialBackend;

import java.util.ArrayList;
import java.util.List;

public class SimpleClusterApp {

  public static void main(String[] args) {
    if (args.length == 0)
      startup(new String[] { "2551", "2552", "0" });
    else
      startup(args);
  }

  public static void startup(String[] ports) {
    List<ActorRef> actorRefList = new ArrayList<>();
    for (String port : ports) {
      // Override the configuration of the port
      Config config = ConfigFactory.parseString(
          "akka.remote.artery.canonical.port=" + port)
          .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]"))
          .withFallback(ConfigFactory.load());

      // Create an Akka system
      ActorSystem system = ActorSystem.create("ClusterSystem", config);

      // Create an actor that handles cluster domain events
      ActorRef actorRef = system.actorOf(Props.create(SimpleClusterListener2.class),
          "clusterListener");
      ActorRef workActor = system.actorOf(Props.create(WorkActor.class),
              "workerActor");

      actorRef.tell("foo1 " + actorRef.toString() + " p_"+ port,ActorRef.noSender());
      actorRefList.add(actorRef);
    }
    //必须要多等待一会，否则会报dead letters
    /*
    [INFO] [02/25/2019 15:27:25.358] [ClusterSystem-akka.actor.default-dispatcher-22]
    [akka://ClusterSystem/deadLetters] Message [java.lang.String] from Actor[akka://ClusterSystem/user/clusterListener#-1801590123]
    to Actor[akka://ClusterSystem/deadLetters] was not delivered. [1] dead letters encountered.
    If this is not an expected behavior, then [Actor[akka://ClusterSystem/deadLetters]] may have terminated unexpectedly,
    This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'
     */

    try {
      Thread.sleep(9000);
    }
    catch (Exception ex) {

    }
    for (ActorRef actorRef : actorRefList) {
      actorRef.tell("foo4 "+ actorRef.toString(), ActorRef.noSender());
    }
  }
}
