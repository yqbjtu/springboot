package com.yq.zkLeader;


import com.yq.config.ZkConfig;
import com.yq.constant.ClusterConstants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Simple to Introduction
 * className: LeaderSelectorService
 *
 * @author EricYang
 * @version 2018/9/2 12:01
 */
@Service
@Slf4j
@Getter
@Configuration
public class LeaderSelectorService {

    @Autowired
    ZkConfig zkConfig;

    private static final int CLIENT_QTY = 10;
    private CuratorFramework client = null;
    private MyLeaderSelectorListener selector = null;
    private String instanceId = null;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void init(String seedNodes) throws Exception {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            client =
                    //CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
                    CuratorFrameworkFactory.builder()
                            .connectString(zkConfig.getZkServers())
                            .sessionTimeoutMs(30 * 1000)
                            .connectionTimeoutMs(15 * 1000)
                            .retryPolicy(retryPolicy)
                            .namespace(zkConfig.getZkNamespace())
                            .build();

            client.start();

            selector = new MyLeaderSelectorListener(client, ClusterConstants.LEADER_PATH, instanceId, seedNodes);
            selector.start();
            countDownLatch.await();
        } finally {
            log.info("Shutting down...");
            CloseableUtils.closeQuietly(selector);
            CloseableUtils.closeQuietly(client);
        }
    }

    public String getSeedNode() {
        String content = null;
        if (client != null) {
            String taskFullPath = ClusterConstants.SEED_NODE_PATH;

            try {
                Stat stat = client.checkExists().forPath(taskFullPath);
                if (stat != null) {
                    byte[] existingValue = client.getData().forPath(taskFullPath);
                    content = new String(existingValue, "UTF-8");
                    log.info("taskFullPath={}, content={}", taskFullPath, content);
                } else {
                    log.warn("taskFullPath={} does not exist. but it should exist.", taskFullPath);
                }
            } catch (Exception ex) {
                log.error("get taskFullPath={} children exception", taskFullPath, ex);
            }
        }

        return content;
    }

    public boolean isLeader() {
        boolean result = false;
        if(selector != null) {
            LeaderSelector leaderSelector = selector.getLeaderSelector();
            if (leaderSelector != null) {
                return selector.getLeaderSelector().hasLeadership();
            }
        }

        return result;
    }
}
