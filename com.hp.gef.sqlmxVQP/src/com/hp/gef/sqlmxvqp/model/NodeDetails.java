package com.hp.gef.sqlmxvqp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NodeDetails implements Serializable{
  List<PropertyValuePair> details;

  NodeDetails() {
    details = new ArrayList<PropertyValuePair>();
  }

  public void addNodeDetail(PropertyValuePair pair) {
    details.add(pair);
  }

  public List<PropertyValuePair> getNodeDetail() {
    return details;
  }
}
