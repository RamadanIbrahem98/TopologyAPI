package io.RamadanIbrahem98.TopologyAPI.Component;

import java.util.HashMap;

public class Component {
  private final String id;
  private final String type;
  private final HashMap<String, String> netList;
  private Specs deviceSpecs;

  public Component(final String id,
                   final String type
  ) {
    this.id = id;
    this.type = type;
    netList = new HashMap<>();
  }

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

  public Component(final String id,
                   final String type,
                   final HashMap<String, String> netList
  ) {
    this.id = id;
    this.type = type;
    this.netList = netList;
  }

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

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public HashMap<String, String> getNetList() {
    return netList;
  }

  public void addNetList(final String netName, final String netValue) {
    netList.put(netName, netValue);
  }

  public void setDeviceSpecs(final Specs deviceSpecs) {
    this.deviceSpecs = deviceSpecs;
  }

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
}
