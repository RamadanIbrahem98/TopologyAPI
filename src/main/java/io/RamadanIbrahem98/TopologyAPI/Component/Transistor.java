package io.RamadanIbrahem98.TopologyAPI.Component;

import java.util.HashMap;

public class Transistor extends Component {
  /**
   * A Constructor for Transistor class.
   *
   * @param id           the id of the transistor
   * @param name         the name of the specs for the transistor
   * @param defaultValue the default value of the specs for the transistor
   * @param min          the minimum value of the specs for the transistor
   * @param max          the maximum value of the specs for the transistor
   */
  @SuppressWarnings("unused")
  public Transistor(final String id,
                    final String name,
                    final double defaultValue,
                    final double min,
                    final double max
  ) {
    super(id, "transistor");
    setDeviceSpecs(new Specs(name, defaultValue, min, max));
  }

  /**
   * A Constructor for Transistor class.
   *
   * @param id           the id of the transistor
   * @param type         the type of the transistor
   * @param name         the name of the specs for the transistor
   * @param defaultValue the default value of the specs for the transistor
   * @param min          the minimum value of the specs for the transistor
   * @param max          the maximum value of the specs for the transistor
   */
  public Transistor(final String id,
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
   * A Constructor for Transistor class.
   *
   * @param id           the id of the transistor
   * @param type         the type of the transistor
   * @param netList      the netlist of the transistor
   * @param name         the name of the specs for the transistor
   * @param defaultValue the default value of the specs for the transistor
   * @param min          the minimum value of the specs for the transistor
   * @param max          the maximum value of the specs for the transistor
   */
  public Transistor(final String id,
                    final String type,
                    HashMap<String, String> netList,
                    final String name,
                    final double defaultValue,
                    final double min,
                    final double max
  ) {
    super(id, type, netList);
    setDeviceSpecs(new Specs(name, defaultValue, min, max));
  }
}
