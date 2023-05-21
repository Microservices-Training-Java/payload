package org.aibles.header.constant;

public enum PayloadConstant {
  USER_ID("userId"), LANGUAGE("language"),

  USER_ROLES("userRoles"), USERNAME("username");

  private final String value;

  PayloadConstant(String value) {
    this.value = value;
  }
  public String getValue() {
    return this.value;
  }

  }
