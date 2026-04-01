package com.riddhi.api_gateway.filter;

import com.riddhi.api_gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // 🔓 Allow public endpoints
        if (path.startsWith("/api/auth")) {
            return chain.filter(exchange);
        }

        // 🔐 Get Authorization header
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        // 🔍 DEBUG (VERY IMPORTANT)
        System.out.println("AUTH HEADER: " + authHeader);

        // ❌ If header missing
        if (authHeader == null || authHeader.isEmpty()) {
            return unauthorized(exchange);
        }

        // ❌ If not Bearer token
        if (!authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        // Extract token
        String token = authHeader.substring(7);

        try {
            // ❌ Validate token
            if (!jwtUtil.validateToken(token)) {
                return unauthorized(exchange);
            }
        } catch (Exception e) {
            System.out.println("JWT ERROR: " + e.getMessage());
            return unauthorized(exchange);
        }

        // ✅ Token valid → forward request
        return chain.filter(exchange);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}