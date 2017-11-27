package com.hp.gef.sqlmxvqp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertySource;

public class CostDetails implements Serializable{

  List<PropertyValuePair> details;

  CostDetails() {
    details = new ArrayList<PropertyValuePair>();
  }

  public void addCost(PropertyValuePair pair) {
    details.add(pair);
  }

  public List<PropertyValuePair> getCostDetails() {
    return details;
  }

}
