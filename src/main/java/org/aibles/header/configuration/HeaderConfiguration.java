package org.aibles.header.configuration;

import org.aibles.header.helper.ContextHelper;
import org.aibles.header.util.PayloadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.aibles.header.dto",
        "org.aibles.header.interceptor",
        "org.aibles.header.helper",
        "org.aibles.header.configuration"
})
public class HeaderConfiguration {

  @Bean
  public PayloadUtil payloadUtil(ContextHelper contextHelper) {
    return new PayloadUtil(contextHelper);
  }

}
