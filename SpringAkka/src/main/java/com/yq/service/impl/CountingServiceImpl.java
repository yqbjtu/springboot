package com.yq.service.impl;

import com.yq.service.CountingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Simple to Introduction
 * className: CountingServiceImpl
 *
 * @author EricYang
 * @version 2019/2/26 11:09
 */
@Service("countingService")
@Slf4j
public class CountingServiceImpl implements CountingService {

    @Override
    public int increment(int count) {
        log.info("increase " + count + " by 1.");
        return count + 1;
    }

}
