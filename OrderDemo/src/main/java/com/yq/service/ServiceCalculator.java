package com.yq.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Simple to Introduction
 * className: ServiceCalculator
 *
 * @author EricYang
 * @version 2018/10/21 16:21
 */


@Data
@Slf4j
@Service
public class ServiceCalculator {
    private int count = 0;


    public int calculate() {
        log.info("start to calculate");
        for(int i=0;i < 100 ; i++) {
            count += i;
        }
        return count;
    }
}
