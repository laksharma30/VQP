package com.hp.gef.sqlmxvqp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DescriptionDetails implements Serializable{
  List<PropertyValuePair> details;

  DescriptionDetails() {
    details = new ArrayList<PropertyValuePair>();
  }

  public void addDescription(PropertyValuePair pair) {
    details.add(pair);
  }

  public List<PropertyValuePair> getDescriptionDetails() {
    return details;
  }
}
