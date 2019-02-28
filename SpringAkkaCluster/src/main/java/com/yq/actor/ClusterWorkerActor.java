package com.yq.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Address;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.CurrentClusterState;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.MemberRemoved;
import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.ClusterEvent.UnreachableMember;
import akka.cluster.ClusterReadView;
import akka.cluster.ClusterSettings;
import akka.cluster.Member;
import akka.cluster.MemberStatus;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.List;

public class ClusterWorkerActor extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    Cluster cluster = Cluster.get(getContext().system());

    List<Member> memberList = new ArrayList<>();

    ActorRef otherActor = getContext().actorOf(FromConfig.getInstance().props(),
            "workerActorRouter");

    public static class GetClusterInfo {
    }

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
                    ClusterSettings setting = cluster.settings();

                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("systemName:" + setting.systemName());
                    stringBuffer.append(", seedNodes:" + setting.SeedNodes());
                    Address address = state.getLeader();
                    if (address != null) {
                        stringBuffer.append(", leaderAddress:" + address.hostPort());
                    }

                    Iterable<Member> list = state.getMembers();
                    for (Member member : list) {
                        MemberStatus memberStatus = member.status();
                        String memberHostPort = member.address().hostPort();
                        stringBuffer.append(", (memberHostPort:" + memberHostPort + ",memberStatus:" + memberStatus + ")");
                    }

                    this.sender().tell(stringBuffer.toString(), getSelf());
                })
                .match(MemberUp.class, mUp -> {
                    log.info("Member is Up: {}", mUp.member());
                    memberList.add(mUp.member());
                })
                .match(UnreachableMember.class, mUnreachable -> {
                    log.info("Member detected as unreachable: {}", mUnreachable.member());
                })
                .match(MemberRemoved.class, mRemoved -> {
                    log.info("Member is Removed: {}", mRemoved.member());
                    memberList.remove(mRemoved.member());
                })
                .match(MemberEvent.class, message -> {
                    // ignore  memberList


                })
                .match(GetClusterInfo.class, get -> {
                    long threadId = Thread.currentThread().getId();
                    ClusterSettings setting = cluster.settings();

                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("systemName:" + setting.systemName());
                    stringBuffer.append(", seedNodes:" + setting.SeedNodes());
                    stringBuffer.append(", memberListSize:" + memberList.size());
                    ClusterReadView clusterReadView = cluster.readView();

                    CurrentClusterState currentClusterState = clusterReadView.state();
                    currentClusterState.getMembers();
                    Address address = currentClusterState.getLeader();
                    if (address != null) {
                        stringBuffer.append(", leaderAddress:" + address.hostPort());
                    }
                    stringBuffer.append(", currentNodeIsLeader:" + clusterReadView.isLeader());

                    Iterable<Member> list = currentClusterState.getMembers();
                    for (Member member : list) {
                        MemberStatus memberStatus = member.status();
                        String memberHostPort = member.address().hostPort();

                        stringBuffer.append(", (memberHostPort:" + memberHostPort + ", status:" + memberStatus + ")");
                    }
                    this.sender().tell(stringBuffer.toString(), getSelf());
                })
                .match(String.class, msg -> {
                    long threadId = Thread.currentThread().getId();
                    ClusterSettings setting = cluster.settings();
                    Config config = setting.config();
                    String port = config.getString("akka.remote.artery.canonical.port");
                    log.info("msg={}, objectStr={}, port={}, threadId={}.", msg, this.toString(), port, threadId);
                    String newMsg = msg + ", OriginPort_" + port;
                    otherActor.tell(newMsg, self());
                })
                .build();
    }
}
