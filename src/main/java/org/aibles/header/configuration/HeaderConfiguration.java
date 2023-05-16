package org.aibles.header.configuration;

import org.aibles.header.dto.Payload;
import org.aibles.header.util.PayloadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfiguration {

  @Bean
  public PayloadUtil payloadUtil(Payload payload) {
    return new PayloadUtil(payload);
  }

}
