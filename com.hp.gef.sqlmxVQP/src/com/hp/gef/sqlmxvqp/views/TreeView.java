package com.hp.gef.sqlmxvqp.views;

import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

import com.hp.gef.sqlmxvqp.contentproviders.TreeContentProvider;
import com.hp.gef.sqlmxvqp.contentproviders.TreeLabelProvider;
import com.hp.gef.sqlmxvqp.contentproviders.VqpContentProvider;
import com.hp.gef.sqlmxvqp.contentproviders.VqpLabelProvider;
import com.hp.gef.sqlmxvqp.jdbc_connectivity.SqlMxExplain;
import com.hp.gef.sqlmxvqp.model.Nodes;

public class TreeView extends ViewPart {

  private TreeViewer viewer;
  private Nodes nodeData;
  private List<Nodes> nodes = null;

  @Override
  public void createPartControl(Composite parent) {
    Tree addressTree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL
        | SWT.V_SCROLL);
    addressTree.setHeaderVisible(true);
    viewer = new TreeViewer(addressTree);
    addressTree.setLinesVisible(true);
    TreeColumn column1 = new TreeColumn(addressTree, SWT.LEFT);
    column1.setAlignment(SWT.LEFT);
    column1.setText("Operator");
    column1.setWidth(500);
    TreeColumn column2 = new TreeColumn(addressTree, SWT.RIGHT);
    column2.setAlignment(SWT.LEFT);
    column2.setText("Cardinality");
    column2.setWidth(100);
    TreeColumn column3 = new TreeColumn(addressTree, SWT.RIGHT);
    column3.setAlignment(SWT.LEFT);
    column3.setText("Operator Cost");
    column3.setWidth(100);
    TreeColumn column4 = new TreeColumn(addressTree, SWT.RIGHT);
    column4.setAlignment(SWT.LEFT);
    column4.setText("Total Cost");
    column4.setWidth(100);

    viewer.setContentProvider(new TreeContentProvider());
    viewer.setLabelProvider(new TreeLabelProvider());
    SqlMxExplain model = new SqlMxExplain();
//    model.generateExplain();
//    nodes = model.getNodes();
//    viewer.setInput(nodes.get(0));
//    viewer.expandAll();
  }
  
  public void setNodes(List<Nodes> nodes) {
    this.nodes = nodes;
  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub

  }
}