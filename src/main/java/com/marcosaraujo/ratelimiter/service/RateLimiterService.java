package com.marcosaraujo.ratelimiter.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    private static final int MAX_REQUESTS = 10;
    private static final int WINDOW_SECONDS = 1;

    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isAllowed(String clientKey) {
        String key = "rate_limit:" + clientKey;

        Long currentRequests = redisTemplate.opsForValue().increment(key);

        if (currentRequests != null && currentRequests == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SECONDS));
        }

        return currentRequests != null && currentRequests <= MAX_REQUESTS;
    }
}