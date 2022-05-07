package io.RamadanIbrahem98.TopologyAPI.Component;

import java.util.HashMap;

public class Transistor extends Component{
  public Transistor(final String id,
                    final String name,
                    final double defaultValue,
                    final double min,
                    final double max
  ) {
    super(id, "transistor");
    setDeviceSpecs(new Specs(name, defaultValue, min, max));
  }

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
