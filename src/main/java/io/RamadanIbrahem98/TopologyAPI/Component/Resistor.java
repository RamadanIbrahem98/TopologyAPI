package io.RamadanIbrahem98.TopologyAPI.Component;

import java.util.HashMap;

public class Resistor extends Component {

  /**
   * A Constructor for Resistor class.
   *
   * @param id           the id of the resistor
   * @param name         the name of the specs for the resistor
   * @param defaultValue the default value of the specs for the resistor
   * @param min          the minimum value of the specs for the resistor
   * @param max          the maximum value of the specs for the resistor
   */
  @SuppressWarnings("unused")
  public Resistor(final String id,
                  final String name,
                  final double defaultValue,
                  final double min,
                  final double max
  ) {
    super(id, "resistor");
    setDeviceSpecs(new Specs(name, defaultValue, min, max));
  }

  /**
   * A Constructor for Resistor class.
   *
   * @param id           the id of the resistor
   * @param type         the type of the resistor
   * @param name         the name of the specs for the resistor
   * @param defaultValue the default value of the specs for the resistor
   * @param min          the minimum value of the specs for the resistor
   * @param max          the maximum value of the specs for the resistor
   */
  public Resistor(final String id,
                  final String type,
                  final String name,
                  final double defaultValue,
                  final double min,
                  final double max
  ) {
    super(id, type);
    setDeviceSpecs(new Specs(name, defaultValue, min, max));
  }

  /**
   * A Constructor for Resistor class.
   *
   * @param id           the id of the resistor
   * @param type         the type of the resistor
   * @param netList      the netlist of the resistor
   * @param name         the name of the specs for the resistor
   * @param defaultValue the default value of the specs for the resistor
   * @param min          the minimum value of the specs for the resistor
   * @param max          the maximum value of the specs for the resistor
   */
  public Resistor(final String id,
                  final String type,
                  final HashMap<String, String> netList,
                  final String name,
                  final double defaultValue,
                  final double min,
                  final double max
  ) {
    super(id, type, netList);
    setDeviceSpecs(new Specs(name, defaultValue, min, max));
  }
}
