package com.hp.gef.sqlmxvqp.contentproviders;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.hp.gef.sqlmxvqp.model.Nodes;

public class NodesAdaptor implements IPropertySource {
  private final Nodes node;

  public NodesAdaptor(Nodes node) {
    this.node = node;
  }

  @Override
  public Object getEditableValue() {
    return false;
  }

  @Override
  public IPropertyDescriptor[] getPropertyDescriptors() {
    return new IPropertyDescriptor[] {
        new TextPropertyDescriptor("stmtName", "Statement Name"),
        new TextPropertyDescriptor("planID", "Plan ID"),
        // new TextPropertyDescriptor("modName","Module Name"),
        new TextPropertyDescriptor("OpName", "Operator Name"),
        new TextPropertyDescriptor("seqNum", "Sequence Number"),
        new TextPropertyDescriptor("L_Child", "Left Child"),
        new TextPropertyDescriptor("R_Child", "Right Child"),
        new TextPropertyDescriptor("T_Name", "Table Name"),
        new TextPropertyDescriptor("cardinality", "Cardinality"),
        new TextPropertyDescriptor("opCost", "Operator Cost"),
        new TextPropertyDescriptor("totalCost", "Total Cost"),
    // new TextPropertyDescriptor("detailCost","Detail Cost")

    };
  }

  @Override
  public Object getPropertyValue(Object id) {
    // TODO Auto-generated method stub
    // if (id.equals("modName")) {
    // return node.getModuleName();
    // }
    if (id.equals("stmtName")) {
      return node.getStatementName();
    }
    if (id.equals("planID")) {
      return node.getPlanID();
    }
    if (id.equals("seqNum")) {
      return node.getSeqNum();
    }
    if (id.equals("OpName")) {
      return node.getName();
    }
    if (id.equals("L_Child")) {
      return node.getLeftChildSeqNum();
    }
    if (id.equals("R_Child")) {
      return node.getRightChildSeqNum();
    }
    if (id.equals("T_Name")) {
      return node.getTName();
    }
    if (id.equals("cardinality")) {
      return node.getCardinality();
    }
    if (id.equals("opCost")) {
      return node.getOpCost();
    }
    // if (id.equals("detailCost")) {
    // return node.getDetailCost();
    // }
    if (id.equals("totalCost")) {
      return node.getTotalCost();
    }
    return null;
  }

  @Override
  public boolean isPropertySet(Object arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void resetPropertyValue(Object arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setPropertyValue(Object arg0, Object arg1) {
    // TODO Auto-generated method stub

  }
}
