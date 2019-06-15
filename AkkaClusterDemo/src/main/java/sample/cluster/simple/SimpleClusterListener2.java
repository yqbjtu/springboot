package sample.cluster.simple;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.CurrentClusterState;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.MemberRemoved;
import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.ClusterEvent.UnreachableMember;
import akka.cluster.ClusterSettings;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import com.typesafe.config.Config;

public class SimpleClusterListener2 extends AbstractActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  Cluster cluster = Cluster.get(getContext().system());

  ActorRef otherActor = getContext().actorOf(FromConfig.getInstance().props(),
          "workerActorRouter");

  //subscribe to cluster changes
  @Override
  public void preStart() {
    cluster.subscribe(self(), MemberEvent.class, UnreachableMember.class);
  }

  //re-subscribe when restart
  @Override
  public void postStop() {
    cluster.unsubscribe(self());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(CurrentClusterState.class, state -> {
        log.info("Current members: {}", state.members());
      })
      .match(MemberUp.class, mUp -> {
        log.info("Member is Up: {}", mUp.member());
      })
      .match(UnreachableMember.class, mUnreachable -> {
        log.info("Member detected as unreachable: {}", mUnreachable.member());
      })
      .match(MemberRemoved.class, mRemoved -> {
        log.info("Member is Removed: {}", mRemoved.member());
      })
      .match(MemberEvent.class, message -> {
        // ignore

      })
     .match(String.class, msg -> {
         long threadId = Thread.currentThread().getId();
         ClusterSettings setting = cluster.settings();
         Config config = setting.config();
         String port = config.getString("akka.remote.artery.canonical.port");
         log.info("msg={}, objectStr={}, port={}, threadId={}.", msg, this.toString(), port, threadId);
         String newMsg = msg + ", OriginPort_"+ port;
         otherActor.tell(newMsg, self());
         otherActor.tell(newMsg, self());
     })
      .build();
  }
}
