package com.yq.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.yq.actor.ClusterWorkerActor;
import com.yq.actor.WorkActor;
import com.yq.config.ClusterConfig;
import com.yq.service.MyContextService;

import com.yq.zkLeader.LeaderSelectorService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yq.constant.ClusterConstants;

/**
 * Simple to Introduction
 * className: IoTContextServiceImpl
 *
 * @author EricYang
 * @version 2018/12/27 15:16
 */


@Service
@Slf4j
public class MyContextServiceImpl implements MyContextService {
    private static final String ACTOR_SYSTEM_NAME = "ClusterDemo";

    @Autowired
    private Environment env;

    @Autowired
    LeaderSelectorService leaderSelectionSvc;

    @Autowired
    ClusterConfig zkConfig;

    private ActorSystem actorSystem;
    private Map<String, ActorRef> classActorRefMap = new HashMap<>();
    private boolean isInitialized = false;

    public MyContextServiceImpl() {
        //初始化
        //boolean initResultInConstructor = init();
        //log.info("initResultInConstructor={}, actorSystem={}", initResultInConstructor, actorSystem);
    }

    @Override
    public boolean init() {
        synchronized (this) {
            actorSystem = actorSystem();
            if (!isInitialized && actorSystem != null) {
                //初始化
                try {
                    /*String port = myClusterConfig.getPort();

                    Config config = ConfigFactory.parseString(
                            "akka.remote.artery.canonical.port=" + port)
                            .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]"))
                            .withFallback(ConfigFactory.load());

                    // Create an Akka system
                    //Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME).withFallback(ConfigFactory.load());
                    ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);*/

                    // Create an actor that handles cluster domain events
                    ActorRef clusterActorRef = actorSystem.actorOf(Props.create(ClusterWorkerActor.class),
                            "clusterActor");
                    ActorRef workActorRef = actorSystem.actorOf(Props.create(WorkActor.class),
                            "workerActor");

                    classActorRefMap.put(ClusterWorkerActor.class.getCanonicalName(), clusterActorRef);
                    classActorRefMap.put(WorkActor.class.getCanonicalName(), workActorRef);
                    isInitialized = true;
                }
                catch (Exception ex) {
                    log.error("failed to initialize actors", ex);
                }
            }
        }

        return isInitialized;
    }

    @Override
    public ActorRef getActor(String actorName) {
        ActorRef actor = classActorRefMap.get(actorName);
        return actor;
    }

    @Override
    public ActorRef getWorkerActor() {
        ActorRef actor = classActorRefMap.get(ClusterWorkerActor.class.getCanonicalName());
        return actor;
    }

    @Override
    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    public ActorSystem actorSystem() {
        log.info("create a bean for actorSystem, zkConfig={}", zkConfig);
        ActorSystem system = null;
        if (zkConfig != null) {
            int port = zkConfig.getPort();

            //canonical.hostname
            //InetAddress.getLocalHost.getHostAddress
            Map<String, Object> configMap = new HashMap<>();
            String host = "localhost";
            try {
                host = InetAddress.getLocalHost().getHostAddress();
                log.info("localHost={}", host);
            }
            catch (Exception ex) {
                log.error("GetLocalAddress exception", ex);
            }
        /*configMap.put("akka.remote.artery.canonical.port", port);
        //String envValue = env.getProperty(ENV_NAME); 如果是用docker swarm可以将每个部署image的机器ip传递过来作为host
        //同时也可以将每个环节中的seedNodes
        configMap.put("akka.remote.artery.canonical.hostname", host);
        */
            final String seedNodes = "akka.tcp://" + ACTOR_SYSTEM_NAME + "@" + host + ":" + port;

            configMap.put(ClusterConstants.NETTY_PORT, port);
            configMap.put(ClusterConstants.NETTY_HOSTNAME, host);
            //启动先读取zk 指定path的值，如果该值为空，试图zk获取leader，获取后开始将自己设置为seedNodes（ephemeral）。
            // 没有获取leader时，自己等待一段时间后，读取指定path的值， 否则自己作为自己的集群
            // 第一次，path没有值，任意一个实例启动后获取path的值都是空，然后其中一个实例成功写入了地址ip1。其他实例加入到第一个成功写入seedNodes的集群
            //第二次，当原有的写入seedNodes的实例，自己down机了(因为是ephemeral)，这是其余的任意一台机器获得leader，然后将自己的地址ip2为seedNodes，之后新加入的实例使用ip2作为seedNodes
            //第三次，当所有的实例都down了，path的值也没有了（ephemeral类型），下次启动就如同第一次一样。
            try {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            leaderSelectionSvc.init(seedNodes);
                        }
                        catch (Exception ex) {
                            log.error("LeaderElectionSvc failed to init", ex);
                        }
                    }
                };

                (new Thread(runnable)).start();

                Thread.sleep(1000);
            }
            catch(Exception ex) {
                log.error("leader exception", ex);
            }
            String existingSeedNode = leaderSelectionSvc.getSeedNode();

            List<String> list = (new ArrayList<String>(0));

            if (StringUtils.isNotBlank(existingSeedNode)) {
                //如果其他实例已经写入的seedNode，就使用其他实例的seedNode
                list.add(existingSeedNode);
                log.info("从zk path获取到existingSeedNode={}", existingSeedNode);
            } else {
                //多读几次，可能正在读取的时候比人正在写入
                //查询自己是否成为leader， 如果是leader， 自己就是seedNodes，直接使用自己的host，如果不是leader，多查询3次，如果都换失败，就把自己作为新的集群
                if (leaderSelectionSvc.getSelector() == null) {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception ex) {
                        log.error("when waiting selector. exception", ex);
                    }
                }

                if (leaderSelectionSvc.isLeader()) {
                    log.info("本节点为leader 写入zk path。 seedNodes={}", seedNodes);
                    list.add(seedNodes);
                }
                else {
                    int RETRIES_COUNT = 3;
                    while (StringUtils.isBlank(existingSeedNode) && RETRIES_COUNT > 0) {
                        try {
                            Thread.sleep(500);
                        }
                        catch (Exception ex) {
                            log.error("when waiting to get seedNode. exception", ex);
                        }
                        existingSeedNode = leaderSelectionSvc.getSeedNode();
                        RETRIES_COUNT --;
                    }
                    log.info("本节点非leader 循环查询zk path。 existingSeedNode={}", existingSeedNode);
                    if (StringUtils.isNotBlank(existingSeedNode)) {
                        //如果其他实例已经写入的seedNode，就使用其他实例的seedNode
                        list.add(existingSeedNode);
                    }else {
                        log.info("本节点非leader 多次循环查询zk path依然无法获取的seedNode。 建立新的集群={}", seedNodes);
                        list.add(seedNodes);
                    }
                }
            }

            configMap.put("akka.cluster.seed-nodes", list);
            Config config = ConfigFactory.parseMap(configMap)
                    .withFallback(ConfigFactory.parseString("akka.cluster.roles=[rule1]"))
                    .withFallback(ConfigFactory.load());
            system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);
        }

        return system;
    }
}
