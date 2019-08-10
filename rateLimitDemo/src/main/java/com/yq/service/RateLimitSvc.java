package com.yq.service;

/**
 * Simple to Introduction
 * className: RateLimitSvc
 *
 * @author EricYang
 * @version 2019/8/10 22:52
 */
public interface RateLimitSvc {
    boolean execRateLimit(String tenantId);
}
