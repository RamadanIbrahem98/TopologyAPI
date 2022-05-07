package io.RamadanIbrahem98.TopologyAPI.Component;

import java.util.HashMap;

public class Resistor extends Component{
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
