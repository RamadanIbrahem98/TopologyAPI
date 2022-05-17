package io.RamadanIbrahem98.TopologyAPI.Component;

public class Specs {
  private final String name;
  final double defaultValue;
  final double min;
  final double max;

  /**
   * A Constructor for Specs class.
   *
   * @param name         the name of the spec
   * @param defaultValue the default value of the spec
   * @param min          the minimum value of the spec
   * @param max          the maximum value of the spec
   */
  public Specs(String name, double defaultValue, double min, double max) {
    this.name = name;
    this.defaultValue = defaultValue;
    this.min = min;
    this.max = max;
  }

  /**
   * A Getter for the name of the spec.
   *
   * @return the name of the spec
   */
  public String getName() {
    return name;
  }

  /**
   * A Getter for the default value of the spec.
   *
   * @return the default value of the spec
   */
  public double getDefaultValue() {
    return defaultValue;
  }

  /**
   * A Getter for the minimum value of the spec.
   *
   * @return the minimum value of the spec
   */
  public double getMin() {
    return min;
  }

  /**
   * A Getter for the maximum value of the spec.
   *
   * @return the maximum value of the spec
   */
  public double getMax() {
    return max;
  }

  /**
   * An Override for the toString method.
   *
   * @return a string representation of the spec
   */
  @Override
  public String toString() {
    return name + "={" +
            "\"defaultValue\"=" + defaultValue +
            ", \"min\"=" + min +
            ", \"max\"=" + max +
            '}';
  }
}
