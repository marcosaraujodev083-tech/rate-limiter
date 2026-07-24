package com.marcosaraujo.ratelimiter.filter;

import com.marcosaraujo.ratelimiter.dto.RateLimitEventDTO;
import com.marcosaraujo.ratelimiter.service.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimiterFilter extends OncePerRequestFilter {

    private final RateLimiterService rateLimiterService;
    private final SimpMessagingTemplate messagingTemplate;

    public RateLimiterFilter(RateLimiterService rateLimiterService, SimpMessagingTemplate messagingTemplate) {
        this.rateLimiterService = rateLimiterService;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Ignora requisições do próprio WebSocket para não gerar loop infinito de métricas
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/ws-rate-limiter")) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientIp = request.getRemoteAddr();
        boolean allowed = rateLimiterService.isAllowed(clientIp);

        // Dispara o evento via WebSocket para o Dashboard
        messagingTemplate.convertAndSend("/topic/metrics", new RateLimitEventDTO(clientIp, allowed));

        if (allowed) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429); // HTTP 429 Too Many Requests
            response.getWriter().write("Rate limit exceeded. Try again later.");
        }
    }
}