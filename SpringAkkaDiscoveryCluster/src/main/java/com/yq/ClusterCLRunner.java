package com.yq;

import com.yq.service.IoTContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Simple to Introduction
 * className: RuleMonitorPointService
 *
 * @author EricYang
 * @version 2018/6/5 11:01
 */
@Component
@Slf4j
@Order(4)
public class ClusterCLRunner implements CommandLineRunner {

    @Autowired
    private IoTContextService myContextService;

    @Override
    public void run(String... strings) throws Exception {
        long threadId = Thread.currentThread().getId();
        log.info("initialize cluster. threadId={}", threadId);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 10 );
                    boolean initResultInCLR = myContextService.init();
                    log.info("initResultInCLR={}", initResultInCLR);
                }
                catch (Exception ex) {
                    log.error("LeaderElectionSvc failed to init", ex);
                }
            }
        };
        (new Thread(runnable)).start();
    }

}
