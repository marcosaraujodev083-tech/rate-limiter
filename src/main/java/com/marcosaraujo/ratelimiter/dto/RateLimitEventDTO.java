package com.marcosaraujo.ratelimiter.dto;

import java.time.Instant;

public class RateLimitEventDTO {

    private  String clientIp;
    private boolean allowed;
    private String timestamp;

    public RateLimitEventDTO() {
    }

    public  RateLimitEventDTO(String clientIp, boolean allowed) {
        this.clientIp = clientIp;
        this.allowed = allowed;
        this.timestamp = Instant.now().toString();
    }

    public String GetClientIp() {
        return clientIp;
    }

    public  void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}