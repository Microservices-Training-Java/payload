package org.aibles.header.constant;

public enum PayloadConstant {
  USER_ID("user_id"), LANGUAGE("language"),

  USER_ROLES("user_roles"), USERNAME("username");

  private final String value;

  PayloadConstant(String value) {
    this.value = value;
  }
  public String getValue() {
    return this.value;
  }

  }
