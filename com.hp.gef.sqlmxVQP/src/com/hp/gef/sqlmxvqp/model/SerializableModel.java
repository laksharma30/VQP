package com.hp.gef.sqlmxvqp.model;

import java.io.Serializable;
import java.util.List;

public class SerializableModel implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  List<Nodes> nodes;
  String fileName;
  String queryText;

  public SerializableModel(List<Nodes> nodes, String name, String text) {
    this.nodes = nodes;
    this.fileName = name;
    this.queryText = text;
  }

  public void setNodeList(List<Nodes> nodes) {
    this.nodes = nodes;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setQueryText(String queryText) {
    this.queryText = queryText;
  }
  
  public List<Nodes> getNodeList()
  {
   return this.nodes;
  }
  
  public String getQueryText()
  {
    return this.queryText;
  }
}
