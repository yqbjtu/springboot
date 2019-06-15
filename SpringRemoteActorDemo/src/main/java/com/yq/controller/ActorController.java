package com.yq.controller;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorRefProvider;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Address;
import akka.actor.EmptyLocalActorRef;
import akka.actor.Inbox;
import akka.util.Timeout;
import com.alibaba.fastjson.JSONObject;
import com.yq.actor.CountingActor;
import com.yq.util.SpringContextHolder;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

/**
 * Simple to Introduction
 * className: ActorController
 *
 * @author EricYang
 * @version 2019/2/26 11:17
 */
@Slf4j
@RestController
@RequestMapping("/v1")
public class ActorController {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringContextHolder springContextHolder;

    private final static String LOCAL_FULL_COUNTER_ACTOR_PATH = "akka://RemoteActorDemo/user/counterActor";
    private final static String LOCAL_FULL_HELLO_ACTOR_PATH = "akka://RemoteActorDemo/user/helloActor";
    private final static String HELLO_ACTOR_PATH = "/user/helloActor";

    @ApiOperation(value = "演示使用，初始counterActor和helloActor,已经考虑线程安全", notes="private")
    @GetMapping(value = "/actor/init", produces = "application/json;charset=UTF-8")
    public String initActor() {
        Long threadId = Thread.currentThread().getId();

        ActorRef helloActor0;
        ActorRef helloActor1 = null;
        synchronized(this) {
            //可以获取到helloActor
            helloActor0 = actorSystem.actorFor(LOCAL_FULL_HELLO_ACTOR_PATH);

            if (helloActor0 instanceof EmptyLocalActorRef) {
                helloActor1 = actorSystem.actorOf(springContextHolder.props("HelloWorldActor"), "helloActor");
                ActorPath actorPath1 = helloActor1.path();
                String name1 = actorPath1.name();
                //RepointableActorRef
            }
        }

        ActorRefProvider actorRefProvider = actorSystem.provider();
        ActorRef helloActorFromProvider = actorRefProvider.resolveActorRef(LOCAL_FULL_HELLO_ACTOR_PATH);

        ActorSelection actorSelection = actorSystem.actorSelection(LOCAL_FULL_HELLO_ACTOR_PATH);
        String pathString = actorSelection.pathString();
        ActorRef helloActorFromSelectionAnchor = actorSelection.anchor();
        ActorPath actorPath = helloActorFromSelectionAnchor.path();
        scala.collection.immutable.Iterable<String> itrElements = actorPath.elements();
        String name = actorPath.name();

        ActorSelection as = actorSystem.actorSelection(LOCAL_FULL_HELLO_ACTOR_PATH);
        as.tell("hello", ActorRef.noSender());

        //可以获取到helloActor

        ActorRef counterActor0;
        ActorRef counterActor1 = null;
        synchronized(this) {
            counterActor0 = actorSystem.actorFor(LOCAL_FULL_COUNTER_ACTOR_PATH);

            if (counterActor0 instanceof EmptyLocalActorRef) {
                counterActor1 = actorSystem.actorOf(springContextHolder.props("CountingActor"), "counterActor");
                ActorPath actorPath1 = counterActor1.path();
                String name1 = actorPath1.name();
                //RepointableActorRef
            }
        }

        log.info("helloActor0={}, counterActor0={}, helloActorFromProvider={}, threadId={}, this={}",
                helloActor0, counterActor0, helloActorFromProvider, threadId, this.toString());
        log.info("helloActor1={}, counterActor1={}", helloActor1, counterActor1);

        ActorRef counterActor = counterActor1 != null? counterActor1 : counterActor0;
        ActorRef helloActor = helloActor1 != null? helloActor1 : helloActor0;

        // Create the "actor-in-a-box"
        final Inbox inbox = Inbox.create(actorSystem);

        // tell it to count three times
        inbox.send(counterActor, new CountingActor.Count());
        inbox.send(counterActor, new CountingActor.Count());
        inbox.send(counterActor, new CountingActor.Count());

        // print the result
        log.info("will wait 3 seconds to get result");
        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Future<Object> result = ask(counterActor, new CountingActor.Get(), Timeout.durationToTimeout(duration));
        try {
             log.info("Got back " + Await.result(result, duration));
        } catch (Exception e) {
           log.error("exception.", e);
        }


        helloActor.tell("init",ActorRef.noSender());
        inbox.send(helloActor, "msg from inbox");


        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }

    @ApiOperation(value = "演示使用，将本地counterActor和helloActor写入redis，供远程使用", notes="private")
    @GetMapping(value = "/actor/remoteAddress", produces = "application/json;charset=UTF-8")
    public String writeActorToRedis() {
        Long threadId = Thread.currentThread().getId();

        ActorRefProvider actorRefProvider = actorSystem.provider();
        ActorRef helloActorFromProvider = actorRefProvider.resolveActorRef(LOCAL_FULL_HELLO_ACTOR_PATH);
        ActorRef counterActorFromProvider = actorRefProvider.resolveActorRef(LOCAL_FULL_COUNTER_ACTOR_PATH);


        ActorSelection actorSelection = actorSystem.actorSelection(LOCAL_FULL_HELLO_ACTOR_PATH);
        String pathString = actorSelection.pathString();  // pathString is /user/helloActor


        log.info("helloActorFromProvider={}, counterActorFromProvider={}, threadId={}, this={}",
                helloActorFromProvider, counterActorFromProvider, threadId, this.toString());

        JSONObject jsonObj = new JSONObject();
        Address helloAddress = helloActorFromProvider.path().address();
        //无法获取带有ip地址的远程可以访问的address，这种方式还是本地akka://RemoteActorDemo
        String addressString = helloAddress.toString();
        jsonObj.put("helloPath", helloActorFromProvider.path().address().toString());
        jsonObj.put("counterPath", counterActorFromProvider.path().address());
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }

    @ApiOperation(value = "向counterActor发送计数消息", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", defaultValue = "testFromNode0_Node0", value = "testFrom0", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "nodeAddress", defaultValue = "akka.tcp://RemoteActorDemo@127.0.0.1:2987", value = "nodeAddress", required = true, dataType = "string", paramType = "query")
    })
    //akka.tcp://app@127.0.0.1:2552/user/serviceA/worker
    @GetMapping(value = "/actor/send", produces = "application/json;charset=UTF-8")
    public String sendMsg2SpecificNode(@RequestParam String msg, @RequestParam String nodeAddress) {
        ActorRef actorRef = actorSystem.actorSelection(nodeAddress + HELLO_ACTOR_PATH).anchor();

        if (actorRef != null) {
            // 实际上无法成功发送
            actorRef.tell("直接向远程actor发消息", ActorRef.noSender());
        }

        ActorSelection actorSelection = actorSystem.actorSelection(nodeAddress + HELLO_ACTOR_PATH);

        if (actorSelection != null) {
            actorSelection.tell("通过actorSelection直接向远程actor发消息", ActorRef.noSender());
        }

        ActorSelection as = actorSystem.actorSelection(LOCAL_FULL_HELLO_ACTOR_PATH);
        as.tell(nodeAddress + HELLO_ACTOR_PATH, ActorRef.noSender());


        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("actorRef", actorRef);
        jsonObj.put("actorSelection", actorSelection);
        return jsonObj.toJSONString();
    }

}