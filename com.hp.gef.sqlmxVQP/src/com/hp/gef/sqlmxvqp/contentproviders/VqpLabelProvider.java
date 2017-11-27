package com.hp.gef.sqlmxvqp.contentproviders;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.IEntityStyleProvider;

import com.hp.gef.sqlmxvqp.model.Nodes;
//import com.hp.gef.sqlmxvqp.model.Nodes.Op_Name;
import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class VqpLabelProvider extends LabelProvider implements
    IEntityStyleProvider {// implements IFigureProvider{

  Hashtable nodeAbbrev = new Hashtable();

  enum Op_Name {
    FILE_SCAN, INDEX_SCAN, SUBSET_DELETE, SUBSET_UPDATE, CURSOR_DELETE, CURSOR_UPDATE, FILE_SCAN_UNIQUE, INDEX_SCAN_UNIQUE, UNIQUE_DELETE, UNIQUE_UPDATE, SAMPLE, SEQUENCE, TRANSPOSE, ESP_EXCHANGE, PARTITION_ACCESS, SPLIT_TOP, HASH_GROUPBY, HASH_PARTIAL_GROUPBY_LEAF, HASH_PARTIAL_GROUPBY_ROOT, SHORTCUT_SCALAR_AGRR, SORT_GROUPBY, SORT_PARTIAL_AGGR_LEAF, SORT_PARTIAL_AGGR_ROOT, SORT_PARTIAL_GROUPBY_LEAF, SORT_PARTIAL_GROUPBY_ROOT, SORT_SCALAR_AGGR, INSERT, INSERT_VSBB, HYBRID_HASH_ANTI_SEMI_JOIN, HYBRID_HASH_JOIN, HYBRID_HASH_SEMI_JOIN, LEFT_HYBRID_HASH_JOIN, LEFT_MERGE_JOIN, LEFT_NESTED_JOIN, LEFT_ORDERED_HASH_JOIN, MERGE_ANTI_SEMI_JOIN, MERGE_JOIN, MERGE_SEMI_JOIN, NESTED_ANTI_SEMI_JOIN, NESTED_JOIN, NESTED_SEMI_JOIN, ORDERED_HASH_ANTI_SEMI_JOIN, ORDERED_HASH_JOIN, ORDERED_HASH_SEMI_JOIN, TUPLE_FLOW, MATERIALIZE, BLOCKED_UNION, MERGE_UNION, ORDERED_UNION, UNARY_UNION, ROOT, PACK, UNPACK, SORT, EXPLAIN, EXPR, TUPLELIST, VALUES
  };

  public VqpLabelProvider() {
    nodeAbbrev.put("BLOCKED_UNION", "BU");
    nodeAbbrev.put("CALL", "CALL");
    nodeAbbrev.put("CURSOR_DELETE", "CUR_DEL");
    nodeAbbrev.put("CURSOR_UPDATE", "CUR_UPD");
    nodeAbbrev.put("ESP_EXCHANGE", "ESP");
    nodeAbbrev.put("EXPLAIN", "EXP");
    nodeAbbrev.put("EXPR", "EXPR");
    nodeAbbrev.put("EXPLAIN_CMD", "EXP_CMD");
    nodeAbbrev.put("FILE_SCAN", "FS");
    nodeAbbrev.put("FILE_SCAN_UNIQUE", "FSU");
    nodeAbbrev.put("FirstN", "FRST_N");
    nodeAbbrev.put("HASH_GROUPBY", "HGB");
    nodeAbbrev.put("HASH_PARTIAL_GROUPBY_LEAF", "HPGBL");
    nodeAbbrev.put("HASH_PARTIAL_GROUPBY_ROOT", "HPGBR");
    nodeAbbrev.put("HYBRID_HASH_ANTI_SEMI_JOIN", "HH_ASJ");
    nodeAbbrev.put("HYBRID_HASH_JOIN", "HHJ");
    nodeAbbrev.put("HYBRID_HASH_SEMI_JOIN", "HH_SJ");
    nodeAbbrev.put("INDEX_SCAN", "IS");
    nodeAbbrev.put("INDEX_SCAN_UNIQUE", "ISU");
    nodeAbbrev.put("INSERT", "INSRT");
    nodeAbbrev.put("INSERT_VSBB", "INSRT_VSBB");
    nodeAbbrev.put("LEFT_HYBRID_HASH_JOIN", "LHHJ");
    nodeAbbrev.put("LEFT_MERGE_JOIN", "LMJ");
    nodeAbbrev.put("LEFT_NESTED_JOIN", "LNJ");
    nodeAbbrev.put("LEFT_ORDERED_HASH_JOIN", "LO_HJ");
    nodeAbbrev.put("MATERIALIZE", "MATR");
    nodeAbbrev.put("MERGE_ANTI_SEMI_JOIN", "MASJ");
    nodeAbbrev.put("MERGE_JOIN", "MJ");
    nodeAbbrev.put("MERGE_SEMI_JOIN", "MSJ");
    nodeAbbrev.put("MERGE_UNION", "MU");
    nodeAbbrev.put("MultiUnion", "MULTIU");
    nodeAbbrev.put("NESTED_ANTI_SEMI_JOIN", "N_ASJ");
    nodeAbbrev.put("NESTED_JOIN", "NJ");
    nodeAbbrev.put("NESTED_SEMI_JOIN", "NSJ");
    nodeAbbrev.put("ORDERED_HASH_ANTI_SEMI_JOIN", "OH_ASJ");
    nodeAbbrev.put("ORDERED_HASH_JOIN", "OHJ");
    nodeAbbrev.put("ORDERED_HASH_SEMI_JOIN", "OH_SJ");
    nodeAbbrev.put("ORDERED_UNION", "OU");
    nodeAbbrev.put("PACK", "PAK");
    nodeAbbrev.put("PARTITION_ACCESS", "PA");
    nodeAbbrev.put("PROBE_CACHE", "PC");
    nodeAbbrev.put("ROOT", "R");
    nodeAbbrev.put("SAMPLE", "SMPL");
    nodeAbbrev.put("SAMPLE_FILE_SCAN", "SMPL_FS");
    nodeAbbrev.put("SEQUENCE", "SEQ");
    nodeAbbrev.put("SHORTCUT_SCALAR_AGGR", "SSA");
    nodeAbbrev.put("SORT", "SRT");
    nodeAbbrev.put("SORT_GROUPBY", "SRT_GB");
    nodeAbbrev.put("SORT_PARTIAL_AGGR_LEAF", "SRT_PAGL");
    nodeAbbrev.put("SORT_PARTIAL_AGGR_ROOT", "SRT_PAGR");
    nodeAbbrev.put("SORT_PARTIAL_GROUPBY_LEAF", "SRT_PGBL");
    nodeAbbrev.put("SORT_PARTIAL_GROUPBY_ROOT", "SRT_PGBR");
    nodeAbbrev.put("SORT_SCALAR_AGGR", "SRT_SAGGR");
    nodeAbbrev.put("SPLIT_TOP", "S_TOP");
    nodeAbbrev.put("SUBSET_DELETE", "SUB_DEL");
    nodeAbbrev.put("SUBSET_UPDATE", "SUB_UPD");
    nodeAbbrev.put("TRANSPOSE", "TRNSP");
    nodeAbbrev.put("TUPLE_FLOW", "TPL_FLOW");
    nodeAbbrev.put("TUPLELIST", "TPL_LIST");
    nodeAbbrev.put("UNARY_UNION", "UU");
    nodeAbbrev.put("UNIQUE_DELETE", "UNIQ_DEL");
    nodeAbbrev.put("UNIQUE_DELETE", "UNIQ_UPD");
    nodeAbbrev.put("UNPACK", "UNPAK");
    nodeAbbrev.put("VALUES", "VALS");

  }

  @Override
  public String getText(Object element) {
    if (element instanceof Nodes) {
      Nodes node = (Nodes) element;
      return (" " + node.getSeqNum() + "  " + node.getName());
    }

    return "";
  }

  public Image getImage(Object element) {
    if (element instanceof Nodes) {
      Nodes node = (Nodes) element;
      return Return_Node_Image(node.getName());

    } else
      return null;
  }

  @Override
  public org.eclipse.swt.graphics.Color getNodeHighlightColor(Object entity) {
    if (entity instanceof Nodes) {
      Display display = Display.getCurrent();

      org.eclipse.swt.graphics.Color white = display
          .getSystemColor(SWT.COLOR_GRAY);
      return white;
    } else
      return null;
  }

  @Override
  public org.eclipse.swt.graphics.Color getBorderColor(Object entity) {

    return null;
  }

  @Override
  public org.eclipse.swt.graphics.Color getBorderHighlightColor(Object entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getBorderWidth(Object entity) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public org.eclipse.swt.graphics.Color getBackgroundColour(Object entity) {
    // TODO Auto-generated method stub
    if (entity instanceof Nodes) {
      Display display = Display.getCurrent();

      org.eclipse.swt.graphics.Color white = display
          .getSystemColor(SWT.COLOR_WHITE);
      return white;
    } else
      return null;
  }

  @Override
  public org.eclipse.swt.graphics.Color getForegroundColour(Object entity) {
    // TODO Auto-generated method stub
    if (entity instanceof Nodes) {
      Display display = Display.getCurrent();

      org.eclipse.swt.graphics.Color white = display
          .getSystemColor(SWT.COLOR_BLACK);
      return white;
    } else
      return null;
  }

  @Override
  public IFigure getTooltip(Object element) {

    // TODO Auto-generated method stub
    if (element instanceof Nodes) {
      // Nodes node=(Nodes) element;
      // //return node.getImage();
      // //
      Nodes node = (Nodes) element;
      // String[] s1 = node.getDescription().split(":");
      // String[] s2 = null;
      String tooltip = node.getName() + "\nCardinality: "
          + node.getCardinality() + "\nOperator Cost: " + node.getOpCost()
          + "\nTotal Cost: " + node.getTotalCost();
      // int i;
      // for (i = 1; i < s1.length - 1; i++) {
      // //
      // s2 = s1[i].split("\\s+");
      // int j = 0;
      //
      // for (j = 0; j < s2.length - 1; j++)
      //
      // {
      // tooltip += s2[j];
      // }
      //
      // tooltip += "\n" + s2[j] + " : ";

      // // else
      // s3+=s1[i] + " : ";
      // }
      // tooltip += s1[i];
      IFigure tip = new Label(tooltip);
      // figure.setSize(50, 32);
      return tip;
    }
    // else
    return null;
  }

  @Override
  public boolean fisheyeNode(Object entity) {
    // TODO Auto-generated method stub
    return false;
  }

  public Font getFont() {
    return null;
  }

  private Image Return_Node_Image(String nodeName) {
    Image Op_Image;
    Op_Name op = Op_Name.valueOf(nodeName);
    switch (op) {
    case FILE_SCAN:
    case INDEX_SCAN:
    case SUBSET_DELETE:
    case SUBSET_UPDATE:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("damsub.bmp"));
      break;

    case CURSOR_DELETE:
    case CURSOR_UPDATE:
    case FILE_SCAN_UNIQUE:
    case INDEX_SCAN_UNIQUE:
    case UNIQUE_DELETE:
    case UNIQUE_UPDATE:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("damuniq.bmp"));
      break;

    case SAMPLE:

    case SEQUENCE:

    case TRANSPOSE:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("data_mining.bmp"));
      break;
    case ESP_EXCHANGE:

    case PARTITION_ACCESS:
    case SPLIT_TOP:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("exchange.bmp"));
      break;
    case HASH_GROUPBY:

    case HASH_PARTIAL_GROUPBY_LEAF:
    case HASH_PARTIAL_GROUPBY_ROOT:

    case SHORTCUT_SCALAR_AGRR:
    case SORT_GROUPBY:
    case SORT_PARTIAL_AGGR_LEAF:
    case SORT_PARTIAL_AGGR_ROOT:
    case SORT_PARTIAL_GROUPBY_LEAF:
    case SORT_PARTIAL_GROUPBY_ROOT:
    case SORT_SCALAR_AGGR:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("groupby.bmp"));
      break;
    case INSERT:

    case INSERT_VSBB:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("insert.bmp"));
      break;
    case HYBRID_HASH_ANTI_SEMI_JOIN:
    case HYBRID_HASH_JOIN:
    case HYBRID_HASH_SEMI_JOIN:
    case LEFT_HYBRID_HASH_JOIN:
    case LEFT_MERGE_JOIN:
    case LEFT_NESTED_JOIN:
    case LEFT_ORDERED_HASH_JOIN:
    case MERGE_ANTI_SEMI_JOIN:
    case MERGE_JOIN:
    case MERGE_SEMI_JOIN:
    case NESTED_ANTI_SEMI_JOIN:
    case NESTED_JOIN:
    case NESTED_SEMI_JOIN:
    case ORDERED_HASH_ANTI_SEMI_JOIN:
    case ORDERED_HASH_JOIN:
    case ORDERED_HASH_SEMI_JOIN:
    case TUPLE_FLOW:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("join.bmp"));
      break;

    case MATERIALIZE:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("materialize.bmp"));
      break;

    case BLOCKED_UNION:
    case MERGE_UNION:
    case ORDERED_UNION:
    case UNARY_UNION:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("union.bmp"));
      break;

    case ROOT:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("root.bmp"));
      break;

    case PACK:
    case UNPACK:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("rowset.bmp"));
      break;

    case SORT:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("sort.bmp"));
      break;

    case EXPLAIN:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("storedfunction.bmp"));
      break;

    case EXPR:
    case TUPLELIST:
    case VALUES:
      Op_Image = new Image(Display.getCurrent(), getClass()
          .getResourceAsStream("tuple.bmp"));
      break;

    default:
      Op_Image = null;
    }
    return Op_Image;

  }

}
