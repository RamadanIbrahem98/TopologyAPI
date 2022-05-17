package io.RamadanIbrahem98.TopologyAPI.Component;

import java.util.HashMap;

public class Component {
  private final String id;
  private final String type;
  private final HashMap<String, String> netList;
  private Specs deviceSpecs;

  /**
   * Constructor for Component class.
   *
   * @param id   the id of the component
   * @param type the type of the component
   */
  public Component(final String id,
                   final String type
  ) {
    this.id = id;
    this.type = type;
    netList = new HashMap<>();
  }

  /**
   * Constructor for Component class.
   *
   * @param id           the id of the component
   * @param type         the type of the component
   * @param name         the name of the specification of the component
   * @param defaultValue the default value of the specification of the component
   * @param min          the minimum value of the specification of the component
   * @param max          the maximum value of the specification of the component
   */
  @SuppressWarnings("unused")
  public Component(final String id,
                   final String type,
                   final String name,
                   final double defaultValue,
                   final double min,
                   final double max
  ) {
    this.id = id;
    this.type = type;
    netList = new HashMap<>();
    deviceSpecs = new Specs(name, defaultValue, min, max);
  }

  /**
   * Constructor for Component class.
   *
   * @param id      the id of the component
   * @param type    the type of the component
   * @param netList a netlist object of the component
   */
  public Component(final String id,
                   final String type,
                   final HashMap<String, String> netList
  ) {
    this.id = id;
    this.type = type;
    this.netList = netList;
  }

  /**
   * Constructor for Component class.
   *
   * @param id           the id of the component
   * @param type         the type of the component
   * @param netList      a netlist object of the component
   * @param name         the name of the specification of the component
   * @param defaultValue the default value of the specification of the component
   * @param min          the minimum value of the specification of the component
   * @param max          the maximum value of the specification of the component
   */
  @SuppressWarnings("unused")
  public Component(final String id,
                   final String type,
                   final HashMap<String, String> netList,
                   final String name,
                   final double defaultValue,
                   final double min,
                   final double max
  ) {
    this.id = id;
    this.type = type;
    this.netList = netList;
    deviceSpecs = new Specs(name, defaultValue, min, max);
  }

  /**
   * Getter for the id of the component.
   *
   * @return the id of the component
   */
  public String getId() {
    return id;
  }

  /**
   * Getter for the type of the component.
   *
   * @return the type of the component
   */
  public String getType() {
    return type;
  }

  /**
   * Getter for the netlist of the component.
   *
   * @return the netlist of the component
   */
  public HashMap<String, String> getNetList() {
    return netList;
  }

  /**
   * Append a new netlist entry to the netlist of the component.
   */
  public void addNetList(final String netName, final String netValue) {
    netList.put(netName, netValue);
  }

  /**
   * Setter for the device specification of the component.
   *
   * @param deviceSpecs the device specification of the component
   */
  public void setDeviceSpecs(final Specs deviceSpecs) {
    this.deviceSpecs = deviceSpecs;
  }

  /**
   * Override the toString method of the Object class.
   *
   * @return a string representation of the component
   */
  @Override
  public String toString() {
    return "{" +
            "id=\"" + getId() + "\"" +
            ", type=" + getType() +
            ", " + deviceSpecs.getName() + "={" +
            "default=" + deviceSpecs.getDefaultValue() +
            ", min=" + deviceSpecs.getMin() +
            ", max=" + deviceSpecs.getMax() +
            '}' +
            ", netlist=" + getNetList() +
            '}';
  }

  /**
   * Override the equals method of the Object class.
   *
   * @param o the object to be compared against
   * @return true if the object is equal to the component, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Component component = (Component) o;

    if (!id.equals(component.id)) return false;
    if (!type.equals(component.type)) return false;
    if (!netList.equals(component.netList)) return false;
    return deviceSpecs.equals(component.deviceSpecs);
  }
}
