package org.aibles.header.interceptor;

import org.aibles.header.configuration.FieldNameConfiguration;
import org.aibles.header.constant.PayloadConstant;
import org.aibles.header.helper.ContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(-1)
public class CustomHeaderWebFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomHeaderWebFilter.class);

    private final ContextHelper contextHelper;

    public CustomHeaderWebFilter(ContextHelper contextHelper) {
        this.contextHelper = contextHelper;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            // Example of retrieving a header value
            String userRoles = exchange.getAttribute(PayloadConstant.USER_ROLES.getValue());
            String username = exchange.getAttribute(PayloadConstant.USERNAME.getValue());
            String id = exchange.getAttribute(FieldNameConfiguration.getFieldName());
            String language = exchange.getAttribute(PayloadConstant.LANGUAGE.getValue());
            log.info("userRoles: {}, username: {}, id: {}, language: {}, nameField: {}", userRoles, username, id, language, FieldNameConfiguration.getFieldName());

            Map<String, String> params = new HashMap<>();
            params.put(PayloadConstant.USER_ROLES.getValue(), userRoles);
            params.put(PayloadConstant.USERNAME.getValue(), username);
            params.put(FieldNameConfiguration.getFieldName(), id);
            params.put(PayloadConstant.LANGUAGE.getValue(), language);
            log.info("done catch header: ------->");

            return contextHelper.setDataInContext(params).then(chain.filter(exchange)) // Proceed with the filter chain
                    .contextWrite(ctx -> {
                        // If you need to further modify the context based on params
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            ctx = ctx.put(entry.getKey(), entry.getValue());
                        }
                        return ctx;
                    });

        } catch (Exception ex) {
            log.error("catch header have problem: ", ex);
        }

        return chain.filter(exchange);
    }
}

