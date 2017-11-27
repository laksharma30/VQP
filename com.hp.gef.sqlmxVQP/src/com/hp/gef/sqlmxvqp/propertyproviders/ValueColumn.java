package com.hp.gef.sqlmxvqp.propertyproviders;

import com.hp.gef.sqlmxvqp.model.PropertyValuePair;

public class ValueColumn extends OperatorPropertyColumn {

  @Override
  public String getText(Object element) {
    if (element instanceof PropertyValuePair) {
      return ((PropertyValuePair) element).getValue();
    } else {
      return "";
    }
  }

  @Override
  public String getTitle() {
    return "Value";
  }

}
