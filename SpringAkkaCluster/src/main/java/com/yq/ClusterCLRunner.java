package com.yq;

import com.yq.service.MyContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class ClusterCLRunner implements CommandLineRunner {

    @Autowired
    private MyContextService myContextService;


    @Override
    public void run(String... strings) throws Exception {
        log.info("initialize cluster ");

        myContextService.init();
    }

}
