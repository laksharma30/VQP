package com.hp.gef.sqlmxvqp.jdbc_connectivity;

import java.sql.*;


import com.hp.gef.sqlmxvqp.dialogbox.Credentials;
import com.hp.gef.sqlmxvqp.dialogbox.PasswordDialog;
import com.hp.gef.sqlmxvqp.model.Connections;
import com.hp.gef.sqlmxvqp.model.Nodes;
import com.tandem.t4jdbc.*;//SQLMXPreparedStatement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class SqlMxExplain implements IAdaptable {// extends ViewPart {

  Credentials credentials = PasswordDialog.getCredentials();

  static final String JDBC_DRIVER = "com.hp.t4sqlmx";
  String DB_URL = "jdbc:t4sqlmx://";

  Connection conn = null;
  Statement stmt = null;
  PreparedStatement PStmt = null, PStmt1 = null;
  String exceptionMessage = null;
  private List<Nodes> nodes;
  private List<Connections> connections;
  private String queryText;

  public SqlMxExplain() {

  }

  public void setQueryText(String text) {
    this.queryText = text;
  }

  public void generateExplain() throws ClassNotFoundException, SQLException {

    if (credentials.getSysDetails() == null || credentials.getUsrname() == null
        || credentials.getPwd() == null) {
      return;
    }

    nodes = new ArrayList<Nodes>();
    connections = new ArrayList<Connections>();

    // STEP 2: Register JDBC driver
    Class.forName("com.tandem.t4jdbc.SQLMXDriver");

    // STEP 3: Open a connection
    // System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL + credentials.getSysDetails(),
        credentials.getUsrname(), credentials.getPwd());

    // connection to the database was successful
    credentials.setLoginSuccessful(true);

    // STEP 4: Execute a query
    // System.out.println("Creating statement...");
    stmt = conn.createStatement();
    stmt.executeUpdate("control query default generate_explain 'on'");
    String sql1 = queryText != null ? queryText : null;// sql2,sql3;

    PStmt = conn.prepareStatement(sql1);
    String stmtName = ((SQLMXPreparedStatement) PStmt).getStatementLabel();

    ResultSet rs = stmt.executeQuery("SELECT * FROM TABLE(EXPLAIN(NULL,'"
        + stmtName + "')) ORDER BY SEQ_NUM DESC");
    ResultSetMetaData rsMD = rs.getMetaData();

    System.out.println("");
    System.out.println("Printing ResultSetMetaData ...");
    System.out.println("No. of Columns " + rsMD.getColumnCount());

    for (int j = 1; j <= rsMD.getColumnCount(); j++) {
      System.out.println("Column " + j + " Data Type: "
          + rsMD.getColumnTypeName(j) + " Name: " + rsMD.getColumnName(j));
    }

    System.out.println("Fetching rows...");
    int rowNo = 0;

    while (rs.next()) {
      rowNo++;
      System.out.println("");
      System.out.println("Printing Row " + rowNo + " using getString()");
      for (int j = 1; j <= rsMD.getColumnCount(); j++) {
        System.out.println("Column " + j + " - " + rsMD.getColumnName(j) + "  "
            + rs.getString(j));
      }
      Nodes node = new Nodes(rs.getString(5), rs.getString(4), rs.getString(6),
          rs.getString(7), rs.getString(13), rs.getString(1), rs.getString(2),
          rs.getString(3), rs.getString(8), rs.getString(9), rs.getString(10),
          rs.getString(11), rs.getString(12));
      nodes.add(node);

    }

    for (int i = 0; i < nodes.size(); i++) {
      if (nodes.get(i).getLeftChildSeqNum() != null) {
        Connections connection = new Connections(Integer.toString(i),
            nodes.get(i), nodes.get(nodes.size()
                - Integer.parseInt(nodes.get(i).getLeftChildSeqNum())));
        nodes.get(i).setLeftChild(
            nodes.get(nodes.size()
                - Integer.parseInt(nodes.get(i).getLeftChildSeqNum())));
        connections.add(connection);
      }
      if (nodes.get(i).getRightChildSeqNum() != null) {
        Connections connection = new Connections(Integer.toString(i),
            nodes.get(i), nodes.get(nodes.size()
                - Integer.parseInt(nodes.get(i).getRightChildSeqNum())));
        nodes.get(i).setRightChild(
            nodes.get(nodes.size()
                - Integer.parseInt(nodes.get(i).getRightChildSeqNum())));
        connections.add(connection);
      }
    }
    System.out.println("");
    System.out.println("End of Data");
    rs.close();

    for (Connections connection : connections) {
      connection.getSource().getConnectedTo().add(connection.getDestination());
    }
  }

  public List<Nodes> getNodes() {
    return nodes;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  @Override
  public Object getAdapter(Class arg0) {
    return null;
  }
}
