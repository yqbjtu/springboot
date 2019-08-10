package com.yq.service.impl;

import com.yq.limit.RateLimits;
import com.yq.service.RateLimitSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Simple to Introduction
 * className: RateLimitSvcImpl
 *
 * @author EricYang
 * @version 2019/8/10 22:52
 */
@Service
@Slf4j
public class RateLimitSvcImpl implements RateLimitSvc {
    @Value("${rest.limits.tenant.enabled:false}")
    private boolean perTenantLimitsEnabled;
    @Value("${rest.limits.tenant.configuration:}")
    private String perTenantLimitsConfiguration;

    private ConcurrentMap<String, RateLimits> perTenantLimits = new ConcurrentHashMap<>();

    @Override
    public boolean execRateLimit(String tenantId) {
        if (perTenantLimitsEnabled) {
            RateLimits rateLimits = perTenantLimits.computeIfAbsent(tenantId, id -> new RateLimits(perTenantLimitsConfiguration));
            if (!rateLimits.tryConsume()) {
                log.info("tryConsume false, leftToken={}", rateLimits.getAvailableTokens());
                return false;
            } else {
                log.info("tryConsume true, leftToken={}", rateLimits.getAvailableTokens());
                return true;
            }
        }

        return true;
    }
}
