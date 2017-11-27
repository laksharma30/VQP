package com.hp.gef.sqlmxvqp.views;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.ZoomContributionViewItem;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.*;

import com.hp.gef.sqlmxvqp.jdbc_connectivity.SqlMxExplain;
import com.hp.gef.sqlmxvqp.layoutalgorithm.gxtreelayoutalgorithm;
import com.hp.gef.sqlmxvqp.model.Nodes;
import com.hp.gef.sqlmxvqp.model.SerializableModel;
import com.hp.gef.sqlmxvqp.contentproviders.*;
import com.hp.gef.sqlmxvqp.dialogbox.Credentials;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class SampleView extends ViewPart implements IZoomableWorkbenchPart,
    ITabbedPropertySheetPageContributor {

  public SampleView() {
  }

  private SqlMxExplain explain_ = new SqlMxExplain();
  private GraphViewer viewer;
  private List<Nodes> nodes = null;
  private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
  private IFile vqpFile;
  private TreeViewer treeViewer;
  private Credentials credentials = Credentials.getInstance();
  private boolean PlanLocationFlag=true;
  private SerializableModel model;

  @Override
  public void createPartControl(final Composite parent) {

    parent.setLayout(new FormLayout());

    final CTabFolder tabFolder = new CTabFolder(parent, SWT.BOTTOM);
    FormData fd_tabFolder = new FormData();
    fd_tabFolder.bottom = new FormAttachment(100);
    fd_tabFolder.top = new FormAttachment(0);
    fd_tabFolder.right = new FormAttachment(100);
    fd_tabFolder.left = new FormAttachment(0);
    tabFolder.setLayoutData(fd_tabFolder);
    toolkit.adapt(tabFolder);
    toolkit.paintBordersFor(tabFolder);

    CTabItem tbtmQueryText_1 = new CTabItem(tabFolder, SWT.NONE);
    tbtmQueryText_1.setText("Query Text");

    Composite composite = new Composite(tabFolder, SWT.NONE);
    tbtmQueryText_1.setControl(composite);
    toolkit.paintBordersFor(composite);
    composite.setLayout(new FormLayout());

    final CTabItem tbtmTreeViewer = new CTabItem(tabFolder, SWT.NONE);
    tbtmTreeViewer.setText("Tree Viewer");

    Tree addressTree = new Tree(tabFolder, SWT.BORDER | SWT.H_SCROLL
        | SWT.V_SCROLL);
    addressTree.setHeaderVisible(true);
    treeViewer = new TreeViewer(addressTree);
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
    // viewer.setContentProvider(new ZestNodeContentProvider());
    treeViewer.setContentProvider(new TreeContentProvider());
    treeViewer.setLabelProvider(new TreeLabelProvider());
    treeViewer.expandAll();

    TextViewer textViewer = new TextViewer(composite, SWT.BORDER | SWT.V_SCROLL
        | SWT.H_SCROLL);
    final StyledText styledText = textViewer.getTextWidget();
    styledText.setAlwaysShowScrollBars(false);
    styledText.setFont(SWTResourceManager.getFont("Lucida Console", 10,
        SWT.NORMAL));
    // styledText.setText(GenerateExplain.getQueryText());
    FormData fd_styledText = new FormData();
    fd_styledText.left = new FormAttachment(0);
    fd_styledText.top = new FormAttachment(0);
    styledText.setLayoutData(fd_styledText);

    toolkit.paintBordersFor(styledText);

    final CTabItem tbtmVisualQueryPlan = new CTabItem(tabFolder, SWT.BORDER
        | SWT.H_SCROLL | SWT.V_SCROLL);
    tbtmVisualQueryPlan.setText("Visual Query Plan");
    Button btnNewButton = new Button(composite, SWT.NONE);
    if(!PlanLocationFlag)
    {
      btnNewButton.setEnabled(false);
      viewer.setInput(model.getNodeList());
      treeViewer.setInput(model.getNodeList().get(0));
      treeViewer.expandAll();
      tabFolder.setSelection(2);
      styledText.setText(model.getQueryText());
      
    }
    else
    {
    btnNewButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (styledText.getText() != null) {
          explain_.setQueryText(styledText.getText());
          try {
            explain_.generateExplain();
            PlanLocationFlag=true;
            
            
          } catch (SQLException e1) {
            MessageDialog.openError(parent.getShell(), "Error",
                "Cannot generate plan. " + e1.getMessage());
            return;
          } catch (ClassNotFoundException e2) {
            MessageDialog.openError(parent.getShell(), "Error",
                "Cannot generate plan. " + e2.getMessage());
            return;
          }

          if (credentials.loginSuccessful()&& PlanLocationFlag) {
            nodes = explain_.getNodes();
            viewer.setInput(nodes);
            treeViewer.setInput(nodes.get(0));
            treeViewer.expandAll();
            tabFolder.setSelection(2);
          } 
         
          else {
            MessageDialog.openError(
                parent.getShell(),
                "Error",
                "Cannot generate plan. Connect to the SQL/MX first before attempting to generate the visual query plan.");
            return;
          }
        }
      }
    });
    }

    fd_styledText.right = new FormAttachment(100);
    fd_styledText.bottom = new FormAttachment(100, -38);
    FormData fd_btnNewButton = new FormData();
    fd_btnNewButton.top = new FormAttachment(styledText, 6);
    fd_btnNewButton.right = new FormAttachment(styledText, 0, SWT.RIGHT);
    btnNewButton.setLayoutData(fd_btnNewButton);
    toolkit.adapt(btnNewButton, true, true);
    btnNewButton.setText("Generate Explain");

    ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
    FormData fd_toolBar = new FormData();
    fd_toolBar.top = new FormAttachment(styledText, 6);
    toolBar.setLayoutData(fd_toolBar);
    toolkit.adapt(toolBar);
    toolkit.paintBordersFor(toolBar);

    ToolItem tltmSave = new ToolItem(toolBar, SWT.NONE);
    tltmSave.setText("Save");
    tltmSave.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        System.out.println("Save Button Pressed...");
        SerializableModel serModel = new SerializableModel(nodes, vqpFile
            .getName(), styledText.getText());
        try {
          String fileLoc = Platform.getLocation()
              + vqpFile.getFullPath().toOSString();
          FileOutputStream fileOut = new FileOutputStream(fileLoc);

          ObjectOutputStream out = new ObjectOutputStream(fileOut);
          out.writeObject(serModel);
          out.flush();
          out.close();
          fileOut.close();
          System.out.printf("Serialized data is saved in "
              + vqpFile.getFullPath().toString());
          MessageDialog.openInformation(parent.getShell(), "Success", "File saved in " + vqpFile.getFullPath().toOSString());
        } catch (IOException i) {
          i.printStackTrace();
        }

      }
    });

    viewer = new GraphViewer(tabFolder, SWT.BORDER);
    tbtmVisualQueryPlan.setControl(viewer.getGraphControl());
    toolkit.paintBordersFor(viewer.getGraphControl());
    tbtmTreeViewer.setControl(treeViewer.getTree());
    toolkit.paintBordersFor(treeViewer.getTree());

    viewer.setContentProvider(new VqpContentProvider());
    viewer.setLabelProvider(new VqpLabelProvider());

    getSite().setSelectionProvider(treeViewer);
    getSite().setSelectionProvider(viewer);

    viewer.getGraphControl().setConnectionStyle(2);

    final Graph graph = viewer.getGraphControl();

    graph.addControlListener(new ControlAdapter() {
      @Override
      public void controlResized(final ControlEvent e) {
        viewer.refresh();
      }
    });

    graph.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDoubleClick(MouseEvent e) {
        // if (!((Graph) e.widget).getSelection().isEmpty()) {
        // if (((Graph) e.widget).getSelection().get(0) instanceof GraphNode) {
        // GraphNode node = (GraphNode) ((Graph) e.widget).getSelection().get(
        // 0);
        //
        // nodeData = (Nodes) node.getData();
        try {
          PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
              .showView("org.eclipse.ui.views.PropertySheet");
        } catch (PartInitException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        //
        // if (nodeData.getLeftChildSeqNum() != null) {
        // System.out.println("\n\nLeft Child : "
        // + nodes.get(Integer.parseInt(nodeData.getLeftChildSeqNum()))
        // .getName());
        // } else
        // System.out.println("\n\nLeft Child : null");
        // if (nodeData.getRightChildSeqNum() != null) {
        // System.out.println("Right Child : "
        // + nodes.get(Integer.parseInt(nodeData.getRightChildSeqNum()))
        // .getName());
        //
        // } else
        // System.out.println("Right Child : null");
        // System.out.println("Cardinality : " + nodeData.getCardinality()
        // + "\nModule Name: " + nodeData.getModuleName()
        // + "\nOperator Cost : " + nodeData.getOpCost()
        // + "\nTotal Cost : " + nodeData.getTotalCost());
        // }
        // }
        //
      }
    });

    new HorizontalShift(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
    gxtreelayoutalgorithm customLayout = new gxtreelayoutalgorithm(
        LayoutStyles.NO_LAYOUT_NODE_RESIZING);
    new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);

    viewer.setLayoutAlgorithm(customLayout, false);

    setFocus();
    fillToolBar();
  }

  public void setInitialInput() {
    viewer.setInput(model.getNodeList());
    treeViewer.setInput(model.getNodeList().get(0));
    treeViewer.expandAll();   
  }
  
  private void fillToolBar() {
    // ZoomContributionViewItem toolbarZoomContributionViewItem = new
    // ZoomContributionViewItem(
    // this);
    // IActionBars bars = getViewSite().getActionBars();
    // bars.getMenuManager().add(toolbarZoomContributionViewItem);

  }

  public AbstractZoomableViewer getZoomableViewer() {
    return viewer;

  }

  public void setFile(IFile file) {
    vqpFile = file;
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
    // TODO Auto-generated method stub
  }
  public void setSerializedModel(SerializableModel model)
  
  {
    
    this.model=model;
  }
  //
  @Override
  public String getContributorId() {
    // TODO Auto-generated method stub
    return getSite().getId();
  }

  public void dispose() {
    super.dispose();
  }

  public void refresh() {

  }
  public void setPlanLocationFlag(Boolean flag)
  {
    PlanLocationFlag=flag;
  }

  public Object getAdapter(Class adapter) {
    if (adapter == IPropertySheetPage.class)
      return new TabbedPropertySheetPage(this);
    return super.getAdapter(adapter);
  }
}