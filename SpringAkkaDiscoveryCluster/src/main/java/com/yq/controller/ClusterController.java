package com.yq.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.alibaba.fastjson.JSONObject;
import com.yq.actor.ClusterWorkerActor;
import com.yq.service.IoTContextService;
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
public class ClusterController {

    @Autowired
    private IoTContextService ioTContextService;

    private final static String counterActorPath = "akka://Hello/user/counterActor";
    private final static String helloActorPath = "akka://Hello/user/helloActor";

    @ApiOperation(value = "向clusterActor发送消息，clusterActor会选择本集群中某个workerActor处理消息", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", defaultValue = "testFrom0", value = "testFrom0", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/actor/send2cluster", produces = "application/json;charset=UTF-8")
    public String initActor(@RequestParam String msg) {
        Long threadId = Thread.currentThread().getId();

        ActorRef clusterActorRef = ioTContextService.getActor(ClusterWorkerActor.class.getCanonicalName());
        clusterActorRef.tell(msg + " "+ clusterActorRef.toString(), ActorRef.noSender());

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }

    @ApiOperation(value = "根据地址寻找workerActor,向workerActor发String消息", notes="private")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", defaultValue = "testFromNode0_Node0", value = "testFrom0", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "nodeAddress", defaultValue = "akka.tcp://RuleNodeActor@127.0.0.1:3000", value = "nodeAddress", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/actor/send2worker", produces = "application/json;charset=UTF-8")
    public String sendMsg2SpecificNode(@RequestParam String msg, @RequestParam String nodeAddress) {
        Long threadId = Thread.currentThread().getId();

        ActorRef clusterActorRef = ioTContextService.getActor(ClusterWorkerActor.class.getCanonicalName());
        ActorSystem actorSystem = ioTContextService.getActorSystem();
        actorSystem.actorSelection(nodeAddress + "/user/workerActor")
                .tell(msg + " "+ clusterActorRef.toString(), ActorRef.noSender());

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        return jsonObj.toJSONString();
    }

    @ApiOperation(value = "获取集群基本信息", notes="private")
    @GetMapping(value = "/cluster/info", produces = "application/json;charset=UTF-8")
    public String getClusterInfo() {
        ActorRef clusterActorRef = ioTContextService.getActor(ClusterWorkerActor.class.getCanonicalName());

        // print the result
        log.info("will wait 4 seconds to get result");
        FiniteDuration duration = FiniteDuration.create(4, TimeUnit.SECONDS);
        Future<Object> result = ask(clusterActorRef,  new ClusterWorkerActor.GetClusterInfo(), Timeout.durationToTimeout(duration));
        try {
            log.info("Got ClusterInfo back " + Await.result(result, duration));
        } catch (Exception e) {
            log.error("exception.", e);
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("currentTime", LocalDateTime.now().toString());
        jsonObj.put("result", result.toString());
        return jsonObj.toJSONString();
    }
}