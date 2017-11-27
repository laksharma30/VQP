package com.hp.gef.sqlmxvqp.model;

public class Connections {
  String id;
  Nodes source;
  Nodes destination;

  public Connections(String id, Nodes source, Nodes destination) {
    this.id = id;
    this.source = source;
    this.destination = destination;
  }

  public Nodes getSource() {
    return source;
  }

  public Nodes getDestination() {
    return destination;
  }
}
