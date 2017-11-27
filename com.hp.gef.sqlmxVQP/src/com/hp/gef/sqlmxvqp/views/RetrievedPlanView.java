package com.hp.gef.sqlmxvqp.views;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.ZoomContributionViewItem;
import org.eclipse.zest.layouts.LayoutStyles;

import com.hp.gef.sqlmxvqp.contentproviders.TreeContentProvider;
import com.hp.gef.sqlmxvqp.contentproviders.TreeLabelProvider;
import com.hp.gef.sqlmxvqp.contentproviders.VqpContentProvider;
import com.hp.gef.sqlmxvqp.contentproviders.VqpLabelProvider;
import com.hp.gef.sqlmxvqp.layoutalgorithm.gxtreelayoutalgorithm;
import com.hp.gef.sqlmxvqp.model.SerializableModel;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Button;

public class RetrievedPlanView extends ViewPart implements
    IZoomableWorkbenchPart, ITabbedPropertySheetPageContributor {

  public RetrievedPlanView() {
    // TODO Auto-generated constructor stub
  }

  private GraphViewer graphViewer;
  private TreeViewer treeViewer;
  private SerializableModel model;
  private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

  @Override
  public void createPartControl(final Composite parent) {
    // TODO Auto-generated method stub

    try {
      String fileLoc = null;
      ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
          parent.getShell(), new WorkbenchLabelProvider(),
          new WorkbenchContentProvider());
      dialog.setTitle("title");
      dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
      dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));
      if (dialog.open() == IDialogConstants.OK_ID) {
        IResource resource = (IResource) dialog.getFirstResult();
        fileLoc = resource.getFullPath().toOSString(); 
        fileLoc = Platform.getLocation() + fileLoc;
      }

      FileInputStream fileIn = new FileInputStream(fileLoc);
      
      ObjectInputStream in = new ObjectInputStream(fileIn);
      model = (SerializableModel) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("Employee class not found");
      c.printStackTrace();
      return;
    }
    parent.setLayout(new FormLayout());
    graphViewer = new GraphViewer(parent, SWT.BORDER);
    Control control = graphViewer.getControl();
    FormData fd_control = new FormData();
    fd_control.top = new FormAttachment(0);
    fd_control.left = new FormAttachment(0);
    fd_control.right = new FormAttachment(100);
    control.setLayoutData(fd_control);
     parent.setLayout(new FormLayout());
    
     final CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM);
    // FormData fd_tabFolder = new FormData();
    // fd_tabFolder.bottom = new FormAttachment(100);
    // fd_tabFolder.top = new FormAttachment(0);
    // fd_tabFolder.right = new FormAttachment(100);
    // fd_tabFolder.left = new FormAttachment(0);
    // tabFolder.setLayoutData(fd_tabFolder);
    // toolkit.adapt(tabFolder);
    // toolkit.paintBordersFor(tabFolder);
    //
    // CTabItem tbtmQueryText_1 = new CTabItem(tabFolder, SWT.NONE);
    // tbtmQueryText_1.setText("Query Text");
    //
    // Composite composite = new Composite(tabFolder, SWT.NONE);
    // tbtmQueryText_1.setControl(composite);
    // toolkit.paintBordersFor(composite);
    // composite.setLayout(new FormLayout());
    //
    // final CTabItem tbtmTreeViewer = new CTabItem(tabFolder, SWT.NONE);
    // tbtmTreeViewer.setText("Tree graphViewer");
    //
    // Tree addressTree = new Tree(tabFolder, SWT.BORDER | SWT.H_SCROLL
    // | SWT.V_SCROLL);
    // addressTree.setHeaderVisible(true);
    // treeViewer = new TreeViewer(addressTree);
    // addressTree.setLinesVisible(true);
    // TreeColumn column1 = new TreeColumn(addressTree, SWT.LEFT);
    // column1.setAlignment(SWT.LEFT);
    // column1.setText("Operator");
    // column1.setWidth(500);
    // TreeColumn column2 = new TreeColumn(addressTree, SWT.RIGHT);
    // column2.setAlignment(SWT.LEFT);
    // column2.setText("Cardinality");
    // column2.setWidth(100);
    // TreeColumn column3 = new TreeColumn(addressTree, SWT.RIGHT);
    // column3.setAlignment(SWT.LEFT);
    // column3.setText("Operator Cost");
    // column3.setWidth(100);
    // TreeColumn column4 = new TreeColumn(addressTree, SWT.RIGHT);
    // column4.setAlignment(SWT.LEFT);
    // column4.setText("Total Cost");
    // column4.setWidth(100);
    // // graphViewer.setContentProvider(new ZestNodeContentProvider());
    // treeViewer.setContentProvider(new TreeContentProvider());
    // treeViewer.setLabelProvider(new TreeLabelProvider());
    // treeViewer.expandAll();

    // TextViewer textViewer = new TextViewer(composite, SWT.BORDER |
    // SWT.V_SCROLL
    // | SWT.H_SCROLL);
    // final StyledText styledText = textViewer.getTextWidget();
    // styledText.setAlwaysShowScrollBars(false);
    // styledText.setFont(SWTResourceManager.getFont("Lucida Console", 10,
    // SWT.NORMAL));
    // // styledText.setText(GenerateExplain.getQueryText());
    // FormData fd_styledText = new FormData();
    // fd_styledText.left = new FormAttachment(0);
    // fd_styledText.top = new FormAttachment(0);
    // styledText.setLayoutData(fd_styledText);
    //
    // toolkit.paintBordersFor(styledText);
    //
    // final CTabItem tbtmVisualQueryPlan = new CTabItem(tabFolder, SWT.BORDER
    // | SWT.H_SCROLL | SWT.V_SCROLL);
    // tbtmVisualQueryPlan.setText("Visual Query Plan");
    //
    // graphViewer.setInput(model.getNodeList());
    // treeViewer.setInput(model.getNodeList().get(0));
    // styledText.setText(model.getQueryText());
    //
    // graphViewer = new GraphViewer(tabFolder, SWT.BORDER);
    // tbtmVisualQueryPlan.setControl(graphViewer.getGraphControl());
    // toolkit.paintBordersFor(graphViewer.getGraphControl());
    // tbtmTreeViewer.setControl(treeViewer.getTree());
    // toolkit.paintBordersFor(treeViewer.getTree());

    graphViewer.setContentProvider(new VqpContentProvider());
    graphViewer.setLabelProvider(new VqpLabelProvider());
    graphViewer.setInput(model.getNodeList());

    getSite().setSelectionProvider(graphViewer);
    gxtreelayoutalgorithm customLayout = new gxtreelayoutalgorithm(
        LayoutStyles.NO_LAYOUT_NODE_RESIZING);
    graphViewer.setLayoutAlgorithm(customLayout);

    Button btnOpenFile = new Button(parent, SWT.NONE);
    fd_control.bottom = new FormAttachment(91);
    FormData fd_btnOpenFile = new FormData();
    fd_btnOpenFile.top = new FormAttachment(control, 6);
    fd_btnOpenFile.right = new FormAttachment(100, -10);
    btnOpenFile.setLayoutData(fd_btnOpenFile);
    toolkit.adapt(btnOpenFile, true, true);
    btnOpenFile.setText("Open File ...");
    setFocus();
    fillToolBar();

  }

  private void fillToolBar() {
    // ZoomContributionViewItem toolbarZoomContributionViewItem = new
    // ZoomContributionViewItem(
    // this);
    // IActionBars bars = getViewSite().getActionBars();
    // bars.getMenuManager().add(toolbarZoomContributionViewItem);

  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub
//    graphViewer.getControl().setFocus();
  }

  @Override
  public String getContributorId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AbstractZoomableViewer getZoomableViewer() {
    // TODO Auto-generated method stub
    return graphViewer;
  }

  public void setSerializedModel(SerializableModel model) {
    this.model = model;
  }
}
