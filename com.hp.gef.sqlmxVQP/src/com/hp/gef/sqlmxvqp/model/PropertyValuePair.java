package com.hp.gef.sqlmxvqp.model;

import java.io.Serializable;

public class PropertyValuePair implements Serializable {
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  String key;
  String value;
}
