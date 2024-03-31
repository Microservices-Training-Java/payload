package org.aibles.header.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.header.configuration.FieldNameConfiguration;
import org.aibles.header.constant.PayloadConstant;
import org.aibles.header.helper.ContextHelper;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class PayloadUtil {

    private final ContextHelper contextHelper;

    public Mono<HttpHeaders> fromHeader() {
        Mono<String> userIdMono = contextHelper.getDataFromContext(FieldNameConfiguration.getFieldName(), "");
        Mono<String> languageMono = contextHelper.getDataFromContext(PayloadConstant.LANGUAGE.getValue(), "");
        Mono<String> userRolesMono = contextHelper.getDataFromContext(PayloadConstant.USER_ROLES.getValue(), "");
        Mono<String> userNameMono = contextHelper.getDataFromContext(PayloadConstant.USERNAME.getValue(), "");

        log.info("fromHeader: userIdMono: {}", userIdMono.toString());
        return Mono.zip(userIdMono, languageMono, userRolesMono, userNameMono).map(tuple -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add(FieldNameConfiguration.getFieldName(), tuple.getT1());
            headers.add(PayloadConstant.LANGUAGE.getValue(), tuple.getT2());
            headers.add(PayloadConstant.USER_ROLES.getValue(), tuple.getT3());
            headers.add(PayloadConstant.USERNAME.getValue(), tuple.getT4());
            return headers;
        });
    }

}
