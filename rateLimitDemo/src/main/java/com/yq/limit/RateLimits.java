package com.yq.limit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.local.LocalBucket;
import io.github.bucket4j.local.LocalBucketBuilder;

import java.time.Duration;

/**
 * Simple to Introduction
 * className: RateLimits
 *
 * @author EricYang
 * @version 2019/8/10 22:39
 */

public class RateLimits {
    private final LocalBucket bucket;

    public RateLimits(String limitsConfiguration) {
        LocalBucketBuilder builder = Bucket4j.builder();
        boolean initialized = false;
        for (String limitSrc : limitsConfiguration.split(",")) {
            long capacity = Long.parseLong(limitSrc.split(":")[0]);
            long duration = Long.parseLong(limitSrc.split(":")[1]);
            builder.addLimit(Bandwidth.simple(capacity, Duration.ofSeconds(duration)));
            initialized = true;
        }
        if (initialized) {
            bucket = builder.build();
        } else {
            throw new IllegalArgumentException("Failed to parse rate limits configuration: " + limitsConfiguration);
        }
    }

    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }

    public long getAvailableTokens() {
        return bucket.getAvailableTokens();
    }
}