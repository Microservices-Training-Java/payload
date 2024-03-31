package org.aibles.header.interceptor;

import lombok.RequiredArgsConstructor;
import org.aibles.header.util.PayloadUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(0)
@RequiredArgsConstructor
public class TransferHeaderWebFilter implements WebFilter {

    private final PayloadUtil payloadUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return payloadUtil.fromHeader()
                .flatMap(headers -> {
                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .headers(httpHeaders -> httpHeaders.addAll(headers))
                            .build();

                    ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

                    return chain.filter(mutatedExchange);
                });
    }
}
