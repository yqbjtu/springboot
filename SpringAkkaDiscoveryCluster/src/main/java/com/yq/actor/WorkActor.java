package com.yq.actor;

import akka.actor.AbstractActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.CurrentClusterState;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.MemberRemoved;
import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.ClusterEvent.UnreachableMember;
import akka.cluster.ClusterSettings;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;
import com.yq.constant.ClusterConstants;

public class WorkActor extends AbstractActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  Cluster cluster = Cluster.get(getContext().system());

  ///将节点注册到集群
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
            .match(String.class, msg -> {
              long threadId = Thread.currentThread().getId();
              ClusterSettings setting = cluster.settings();
              Config config = setting.config();
              String port = config.getString(ClusterConstants.NETTY_PORT);
              log.info("msg={}, objectStr={}, receivedPort={}, threadId={}.", msg, this.toString(), port, threadId);
            })
            .build();
  }
}
