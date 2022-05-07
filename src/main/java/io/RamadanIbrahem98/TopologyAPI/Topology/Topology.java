package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;

import java.util.ArrayList;

public class Topology {
  private final String id;
  private final ArrayList<Component> components;

  public Topology(String id, ArrayList<Component> components) {
    this.id = id;
    this.components = components;
  }

  public String getId() {
    return id;
  }

  public ArrayList<Component> getComponents() {
    return components;
  }

  @Override
  public String toString() {
    return "{" +
            "\"id\"=\"" + id + '\"' +
            ", \"components\"=" + components +
            '}';
  }
}
