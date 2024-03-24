package org.aibles.header.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@RequestScope
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {
  private String userId;
  private String language;
  private Set<String> userRoles;
  private String username;
}
