package io.RamadanIbrahem98.TopologyAPI.Component;

public class Specs {
  private final String name;
  final double defaultValue;
  final double min;
  final double max;

  public Specs(String name, double defaultValue, double min, double max) {
    this.name = name;
    this.defaultValue = defaultValue;
    this.min = min;
    this.max = max;
  }

  public String getName() {
    return name;
  }

  public double getDefaultValue() {
    return defaultValue;
  }

  public double getMin() {
    return min;
  }

  public double getMax() {
    return max;
  }

  @Override
  public String toString() {
    return name + "={" +
            "\"defaultValue\"=" + defaultValue +
            ", \"min\"=" + min +
            ", \"max\"=" + max +
            '}';
  }
}
