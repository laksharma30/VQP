package com.hp.gef.sqlmxvqp.contentproviders;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.hp.gef.sqlmxvqp.model.Nodes;

public class TreeLabelProvider implements ITableLabelProvider {

  @Override
  public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public Image getColumnImage(Object arg0, int arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    if (element instanceof Nodes) {
      Nodes node = (Nodes) element;
      switch (columnIndex) {
      case 0:
        return (" " + node.getSeqNum() + "  " + node.getName());
      case 1:
        return (node.getCardinality());
      case 2:
        return (node.getOpCost());
      case 3:
        return (node.getTotalCost());
      }

    }
    // TODO Auto-generated method stub
    return null;
  }

}
