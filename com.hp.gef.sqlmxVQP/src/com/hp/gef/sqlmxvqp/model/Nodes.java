package com.hp.gef.sqlmxvqp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;

import com.hp.gef.sqlmxvqp.contentproviders.NodesAdaptor;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Nodes implements IAdaptable, IWorkbenchAdapter, Serializable {
  String Module_Name;
  String Statement_Name;
  String Plan_ID;
  String T_Name;
  String Cardinality;
  String Op_Cost;
  String Total_Cost;
  String Detail_Cost;
  String Node_Name;
  String Seq_Num;
  String L_Seq_Num;
  String R_Seq_Num;
  String Description;
  // Image Op_Image;
  CostDetails costDetails;
  NodeDetails nodeDetails;
  DescriptionDetails descriptionDetails;
  Nodes LeftChild;
  Nodes RightChild;

  private List<Nodes> connections;

  // enum Op_Name {
  // FILE_SCAN, INDEX_SCAN, SUBSET_DELETE,
  // SUBSET_UPDATE, CURSOR_DELETE, CURSOR_UPDATE,
  // FILE_SCAN_UNIQUE, INDEX_SCAN_UNIQUE, UNIQUE_DELETE,
  // UNIQUE_UPDATE, SAMPLE, SEQUENCE, TRANSPOSE,
  // ESP_EXCHANGE, PARTITION_ACCESS, SPLIT_TOP,
  // HASH_GROUPBY, HASH_PARTIAL_GROUPBY_LEAF,
  // HASH_PARTIAL_GROUPBY_ROOT, SHORTCUT_SCALAR_AGRR,
  // SORT_GROUPBY, SORT_PARTIAL_AGGR_LEAF,
  // SORT_PARTIAL_AGGR_ROOT, SORT_PARTIAL_GROUPBY_LEAF,
  // SORT_PARTIAL_GROUPBY_ROOT, SORT_SCALAR_AGGR,
  // INSERT, INSERT_VSBB, HYBRID_HASH_ANTI_SEMI_JOIN,
  // HYBRID_HASH_JOIN, HYBRID_HASH_SEMI_JOIN,
  // LEFT_HYBRID_HASH_JOIN, LEFT_MERGE_JOIN,
  // LEFT_NESTED_JOIN, LEFT_ORDERED_HASH_JOIN,
  // MERGE_ANTI_SEMI_JOIN, MERGE_JOIN, MERGE_SEMI_JOIN,
  // NESTED_ANTI_SEMI_JOIN, NESTED_JOIN, NESTED_SEMI_JOIN,
  // ORDERED_HASH_ANTI_SEMI_JOIN, ORDERED_HASH_JOIN,
  // ORDERED_HASH_SEMI_JOIN, TUPLE_FLOW, MATERIALIZE,
  // BLOCKED_UNION, MERGE_UNION, ORDERED_UNION,
  // UNARY_UNION, ROOT, PACK, UNPACK, SORT,
  // EXPLAIN, EXPR, TUPLELIST, VALUES
  // };

  public Nodes() {

  }

  public Nodes(String Node_Name, String Seq_Num, String L_Seq_Num,
      String R_Seq_Num, String Description, String Module_Name,
      String Statement_Name, String Plan_ID, String T_Name, String Cardinality,
      String Op_Cost, String Total_Cost, String Detail_Cost) {
    String[] temp = Node_Name.split("\\s+");
    this.Node_Name = temp[0];
    this.Seq_Num = Seq_Num;
    this.connections = new ArrayList<Nodes>();
    this.L_Seq_Num = L_Seq_Num;
    this.R_Seq_Num = R_Seq_Num;
    this.Description = Description;
    this.Module_Name = Module_Name;
    this.Statement_Name = Statement_Name;
    this.Plan_ID = Plan_ID;
    this.T_Name = T_Name;
    this.Cardinality = Cardinality;
    this.Op_Cost = Op_Cost;
    this.Detail_Cost = Detail_Cost;
    this.Total_Cost = Total_Cost;
    // Return_Node_Image();
  }

  public CostDetails getCostProperties() {
    if (costDetails == null) {
      return parseCostDetails();
    }

    return costDetails;
  }

  private CostDetails parseCostDetails() {

    costDetails = new CostDetails();

    String[] costTokens = Detail_Cost.split(" ");

    for (int i = 0; i < costTokens.length; i++) {
      PropertyValuePair pair = new PropertyValuePair();
      pair.setKey(costTokens[i++]);
      pair.setValue(costTokens[i]);
      costDetails.addCost(pair);
    }

    return costDetails;
  }

  public DescriptionDetails getDescriptionProperties() {
    if (descriptionDetails == null) {
      descriptionDetails = new DescriptionDetails();
      String[] s1 = Description.split(": ");
      String[] s2 = null;

      for (int i = 1, j = 0; i < s1.length; i++) {

        PropertyValuePair pair = new PropertyValuePair();
        if (i == 1) {
          pair.setKey(s1[0]);
        } else
          pair.setKey(s2[j]);

        s2 = s1[i].split("\\s+");
        String s3 = "";
        // int j;
        for (j = 0; j < s2.length - 1; j++)
          if (s2[j] != null)
            s3 += s2[j];
        if (i == s1.length - 1)
          s3 += s2[j];
        pair.setValue(s3);

        descriptionDetails.addDescription(pair);
        // }
        // else
        // {
        // s2=s1[i].split("\\s+");
        // String s3="";
        // // int j;
        // for( j=0;j<s2.length-1;j++)
        // if(s2[j]!=null)
        // s3+=s2[j];
        // if (s3!=null)
        // pair.setKey(s3);
        // if (s2[j]!=null)
        // pair.setValue(s2[j]);
        // descriptionDetails.addDescription(pair);
        //
        // }

      }
      return descriptionDetails;

    }
    return descriptionDetails;
  }

  public NodeDetails getNodeProperties() {
    if (nodeDetails == null) {
      nodeDetails = new NodeDetails();
      for (int i = 0; i < 10; i++) {
        PropertyValuePair pair = new PropertyValuePair();
        pair.setKey(getNodeProperty(i));
        pair.setValue(getValue(i));
        nodeDetails.addNodeDetail(pair);

      }
      return nodeDetails;
    }
    return nodeDetails;
  }

  private String getNodeProperty(int i) {
    switch (i) {
    case 0:
      return ("Statement Name");
    case 1:
      return ("Plan ID");
    case 2:
      return ("Node Name");
    case 3:
      return ("Node Seq. Num");
    case 4:
      return ("Left Child Seq. Num");
    case 5:
      return ("Right Child Seq. Num");
    case 6:
      return ("Table Name");
    case 7:
      return ("Cardinality");
    case 8:
      return ("Operator Cost");
    case 9:
      return ("Total Cost");

    }
    return "";
  }

  private String getValue(int i) {
    switch (i) {
    case 0:
      return (Statement_Name);
    case 1:
      return (Plan_ID);
    case 2:
      return (Node_Name);
    case 3:
      return (Seq_Num);
    case 4:
      return (L_Seq_Num);
    case 5:
      return (R_Seq_Num);
    case 6:
      return (T_Name);
    case 7:
      return (Cardinality);
    case 8:
      return (Op_Cost);
    case 9:
      return (Total_Cost);

    }
    return "";
  }

  public String getSeqNum() {
    return Seq_Num;
  }

  public String getName() {

    return Node_Name;
  }

  public String getLeftChildSeqNum() {
    return L_Seq_Num;
  }

  public String getRightChildSeqNum() {
    return R_Seq_Num;
  }

  public String getDescription() {
    return Description;
  }

  public String getModuleName() {
    return Module_Name;
  }

  public String getStatementName() {
    return Statement_Name;
  }

  public String getPlanID() {
    return Plan_ID;
  }

  public String getTName() {
    return T_Name;
  }

  public String getCardinality() {
    return Cardinality;
  }

  public String getOpCost() {
    return Op_Cost;
  }

  public String getTotalCost() {
    return Total_Cost;
  }

  public String getDetailCost() {
    return Detail_Cost;
  }

  public void setLeftChild(Nodes node) {
    this.LeftChild = node;
  }

  public void setRightChild(Nodes node) {
    this.RightChild = node;
  }

  public Nodes getLeftChild() {
    return LeftChild;

  }

  public Nodes getRightChild() {
    return RightChild;
  }

  // public Image getImage() {
  // return Op_Image;
  // }

  public List<Nodes> getConnectedTo() {
    return connections;
  }

  // private void Return_Node_Image() {
  // Op_Name op = Op_Name.valueOf(Node_Name);
  // switch (op) {
  // case FILE_SCAN:
  // case INDEX_SCAN:
  // case SUBSET_DELETE:
  // case SUBSET_UPDATE:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("damsub.bmp"));
  // break;
  //
  // case CURSOR_DELETE:
  // case CURSOR_UPDATE:
  // case FILE_SCAN_UNIQUE:
  // case INDEX_SCAN_UNIQUE:
  // case UNIQUE_DELETE:
  // case UNIQUE_UPDATE:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("damuniq.bmp"));
  // break;
  //
  // case SAMPLE:
  //
  // case SEQUENCE:
  //
  // case TRANSPOSE:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("data_mining.bmp"));
  // break;
  // case ESP_EXCHANGE:
  //
  // case PARTITION_ACCESS:
  // case SPLIT_TOP:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("exchange.bmp"));
  // break;
  // case HASH_GROUPBY:
  //
  // case HASH_PARTIAL_GROUPBY_LEAF:
  // case HASH_PARTIAL_GROUPBY_ROOT:
  //
  // case SHORTCUT_SCALAR_AGRR:
  // case SORT_GROUPBY:
  // case SORT_PARTIAL_AGGR_LEAF:
  // case SORT_PARTIAL_AGGR_ROOT:
  // case SORT_PARTIAL_GROUPBY_LEAF:
  // case SORT_PARTIAL_GROUPBY_ROOT:
  // case SORT_SCALAR_AGGR:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("groupby.bmp"));
  // break;
  // case INSERT:
  //
  // case INSERT_VSBB:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("insert.bmp"));
  // break;
  // case HYBRID_HASH_ANTI_SEMI_JOIN:
  // case HYBRID_HASH_JOIN:
  // case HYBRID_HASH_SEMI_JOIN:
  // case LEFT_HYBRID_HASH_JOIN:
  // case LEFT_MERGE_JOIN:
  // case LEFT_NESTED_JOIN:
  // case LEFT_ORDERED_HASH_JOIN:
  // case MERGE_ANTI_SEMI_JOIN:
  // case MERGE_JOIN:
  // case MERGE_SEMI_JOIN:
  // case NESTED_ANTI_SEMI_JOIN:
  // case NESTED_JOIN:
  // case NESTED_SEMI_JOIN:
  // case ORDERED_HASH_ANTI_SEMI_JOIN:
  // case ORDERED_HASH_JOIN:
  // case ORDERED_HASH_SEMI_JOIN:
  // case TUPLE_FLOW:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("join.bmp"));
  // break;
  //
  // case MATERIALIZE:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("materialize.bmp"));
  // break;
  //
  // case BLOCKED_UNION:
  // case MERGE_UNION:
  // case ORDERED_UNION:
  // case UNARY_UNION:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("union.bmp"));
  // break;
  //
  // case ROOT:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("root.bmp"));
  // break;
  //
  // case PACK:
  // case UNPACK:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("rowset.bmp"));
  // break;
  //
  // case SORT:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("sort.bmp"));
  // break;
  //
  // case EXPLAIN:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("storedfunction.bmp"));
  // break;
  //
  // case EXPR:
  // case TUPLELIST:
  // case VALUES:
  // Op_Image = new Image(Display.getCurrent(), getClass()
  // .getResourceAsStream("tuple.bmp"));
  // break;
  //
  // default:
  // Op_Image = null;
  // }
  //
  // }
  //
  //
  @Override
  public Object getAdapter(Class adapter) {
    // if (adapter == IPropertySource.class) {
    // return new NodesAdaptor(this);
    // }
    // return null;
    if (adapter == IWorkbenchAdapter.class)
      return this;
    if (adapter == IPropertySource.class)
      return new NodesAdaptor(this);
    return null;
  }

  @Override
  public Object[] getChildren(Object arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ImageDescriptor getImageDescriptor(Object arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getLabel(Object arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getParent(Object arg0) {
    // TODO Auto-generated method stub
    return null;
  }
}
