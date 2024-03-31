package org.aibles.header.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-1) // Define the order if necessary
@Slf4j
public class ContextHelper {

    public Mono<String> setDataInContext(Map<String, String> params) {
        log.info("(setDataInContext) params: {}", params);
        return Mono.just("Initial Data")
                .contextWrite(context -> context.putAllMap(params));
    }

    public Mono<String> getDataFromContext(String key, String defaultValue) {
        return Mono.subscriberContext()
                .map(context -> {
                    String value = context.getOrDefault(key, defaultValue);
                    return  value;
                });
    }
}
