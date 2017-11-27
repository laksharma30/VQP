package com.hp.gef.sqlmxvqp.contentproviders;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import com.hp.gef.sqlmxvqp.model.Nodes;

public class VqpContentProvider extends ArrayContentProvider implements
    IGraphEntityContentProvider {

  @Override
  public Object[] getConnectedTo(Object entity) {
    if (entity instanceof Nodes) {
      Nodes node = (Nodes) entity;
      return node.getConnectedTo().toArray();
      // return null;
    }
    throw new RuntimeException("Type not supported");
  }
}
