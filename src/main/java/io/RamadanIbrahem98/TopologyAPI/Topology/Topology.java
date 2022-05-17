package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;

import java.util.ArrayList;

public class Topology {
  private final String id;
  private final ArrayList<Component> components;

  /**
   * A Constructor for Topology class.
   *
   * @param id         the id of the topology
   * @param components array list for the components of the topology
   */
  public Topology(String id, ArrayList<Component> components) {
    this.id = id;
    this.components = components;
  }

  /**
   * A Getter for the id of the topology.
   *
   * @return the id of the topology
   */
  public String getId() {
    return id;
  }

  /**
   * A Getter for the components of the topology.
   *
   * @return the components of the topology
   */
  public ArrayList<Component> getComponents() {
    return components;
  }

  /**
   * An Override for the toString method.
   *
   * @return a string representation of the topology
   */
  @Override
  public String toString() {
    return "{" +
            "\"id\"=\"" + id + '\"' +
            ", \"components\"=" + components +
            '}';
  }
}
