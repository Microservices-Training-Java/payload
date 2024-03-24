package org.aibles.header.util;

import lombok.RequiredArgsConstructor;
import org.aibles.header.dto.Payload;
import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
public class PayloadUtil {
  private static final String FIELD_NAME_USER_ID = "user_id";
  private static final String FIELD_NAME_LANGUAGE = "language";
  private static final String FIELD_NAME_USER_ROLES = "user_roles";
  private static final String FIELD_NAME_USER_NAME = "username";
  private final Payload payload;

  public HttpHeaders fromHeader() {
    HttpHeaders header = new HttpHeaders();
    header.add(FIELD_NAME_USER_ID, payload.getUserId());
    header.add(FIELD_NAME_LANGUAGE, payload.getLanguage());
    header.add(FIELD_NAME_USER_ROLES, payload.getUserRoles().toString());
    header.add(FIELD_NAME_USER_NAME, payload.getUsername());
    return header;
  }

}
