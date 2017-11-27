package com.hp.gef.sqlmxvqp.contentproviders;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.hp.gef.sqlmxvqp.model.Nodes;

public class TreeContentProvider implements ITreeContentProvider {
  int flag = 0;

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof Nodes) {
      Nodes node = (Nodes) inputElement;
      if (flag++ == 0) {

        Nodes[] tempArr = { node };
        return tempArr;
      } else
        return getChildren(node);

    }
    return null;
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof Nodes) {
      int count = 1;
      //
      Nodes node = (Nodes) parentElement;

      if (node.getLeftChild() != null && node.getRightChild() != null)
        count = 2;
      Nodes[] array = new Nodes[count];
      if (node.getRightChild() == null)
        array[0] = node.getLeftChild();
      else if (node.getLeftChild() == null)
        array[0] = node.getRightChild();
      else {
        array[0] = node.getLeftChild();
        array[1] = node.getRightChild();
      }

      return array;

    }
    return null;
  }

  @Override
  public Object getParent(Object element) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    // TODO Auto-generated method stub
    if (element instanceof Nodes) {
      Nodes node = (Nodes) element;
      if (node.getLeftChild() != null || node.getRightChild() != null)
        return true;
    }
    return false;
  }

}
