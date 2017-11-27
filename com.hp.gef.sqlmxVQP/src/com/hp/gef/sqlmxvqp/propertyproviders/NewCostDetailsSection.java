package com.hp.gef.sqlmxvqp.propertyproviders;

import javax.swing.text.TabExpander;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.hp.gef.sqlmxvqp.model.Nodes;

public class NewCostDetailsSection extends AbstractPropertySection {
  private TableViewer tableViewer;
  private PropertyColumn propertyColumn;
  private ValueColumn valueColumn;

  private Nodes node;

  /**
   * Creates the main window's contents
   * 
   * @param parent
   *          the main window
   * @return Control
   */

  public void setInput(IWorkbenchPart part, ISelection selection) {
    super.setInput(part, selection);
    Object input = ((IStructuredSelection) selection).getFirstElement();
    this.node = (Nodes) input;
    tableViewer.setInput(this.node.getCostProperties().getCostDetails());
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  public void createControls(Composite parent,
      TabbedPropertySheetPage tabbedPropertySheetPage) {

    tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
    tableViewer.getTable().setHeaderVisible(true);
    tableViewer.setContentProvider(ArrayContentProvider.getInstance());

    super.createControls(parent, tabbedPropertySheetPage);

    Composite composite = getWidgetFactory().createFlatFormComposite(parent);

    propertyColumn = new PropertyColumn();
    valueColumn = new ValueColumn();

    propertyColumn.addColumnTo(tableViewer);
    valueColumn.addColumnTo(tableViewer);

    Table table = tableViewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
  }

  public void refresh() {
    tableViewer.refresh();
  }

}
