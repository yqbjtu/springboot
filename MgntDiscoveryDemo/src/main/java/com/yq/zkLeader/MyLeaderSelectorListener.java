package com.yq.zkLeader;


import com.yq.constant.ClusterConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple to Introduction
 * className: ConnectorServiceImpl
 *
 * @author EricYang
 * @version 2018/11/06 16:45
 */

@Slf4j
@Data
public class MyLeaderSelectorListener extends LeaderSelectorListenerAdapter implements Closeable {
    private final String instanceId;
    private final LeaderSelector leaderSelector;
    private final AtomicInteger leaderCount = new AtomicInteger();
    private CuratorFramework client = null;
    private String seedNodes = null;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public MyLeaderSelectorListener(CuratorFramework client, String path, String instanceId, String seedNodes) {
        this.instanceId = instanceId;

        this.client = client;
        this.seedNodes = seedNodes;
        leaderSelector = new LeaderSelector(client, path, this);

        // for most cases you will want your instance to requeue when it relinquishes leadership
        leaderSelector.autoRequeue();
    }

    public void start() throws IOException {
        leaderSelector.start();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        // we are now the leader. This method should not return until we want to relinquish leadership
        Long threadId = Thread.currentThread().getId();
        log.info("{} is now the leader. threadId={}", instanceId, threadId);

        registerMySelfAsSeedNode();

        try {
            countDownLatch.await();
        }
        catch (InterruptedException e ) {
            log.info("{} was interrupted.", instanceId );
            Thread.currentThread().interrupt();
        }
        finally {
            log.info("{}释放leadership.",instanceId);
        }
    }

    private void registerMySelfAsSeedNode() throws Exception {
        Long threadId = Thread.currentThread().getId();
        String workerPath = ClusterConstants.SEED_NODE_PATH;
        try {
            Stat stat = client.checkExists().forPath(workerPath);
            if(stat == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(workerPath, seedNodes.getBytes("UTF-8"));
                log.info("create workerPath={} in registerMySelfAsSeedNode. threadId={}", workerPath, threadId);
            }
            else {
                log.warn("{} has already been registered. threadId={}", workerPath, threadId);
            }
        }
        catch (Exception ex) {
            log.error("{} registered. threadId={}. exception", workerPath, threadId, ex);
        }
    }

}
