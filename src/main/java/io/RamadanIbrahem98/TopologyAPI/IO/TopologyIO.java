package io.RamadanIbrahem98.TopologyAPI.IO;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Component.Resistor;
import io.RamadanIbrahem98.TopologyAPI.Component.Transistor;
import io.RamadanIbrahem98.TopologyAPI.Topology.Topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopologyIO {

  @SuppressWarnings("unchecked")
  public static Topology getTopologyByJson(Map<?, ?> json) {
    Topology topology;
    ArrayList<Component> components = new ArrayList<>();
    String topologyID = (String) json.get("id");
    String componentName = "";
    double min = 0;
    double max = 0;
    double defaultValue = 0;

    for (Map<?, ?> component : (ArrayList<Map<?, ?>>) json.get("components")) {
      HashMap<String, String> deviceNetList = new HashMap<>();

      for (Object key : component.keySet()) {
        if (!(key.equals("id") || key.equals("type"))) {
          if (key.equals("netlist")) {
            for (Object netListKey : ((Map<?, ?>) component.get(key)).keySet()) {
              deviceNetList.put((String) netListKey, (String) ((Map<?, ?>) component.get(key)).get(netListKey));
            }
          } else {
            componentName = (String) key;
            min = ((Map<String, Double>) component.get(key)).get("min");
            max = ((Map<String, Double>) component.get(key)).get("max");
            defaultValue = ((Map<String, Double>) component.get(key)).get("default");
          }
        }
      }
      if (component.get("type").equals("resistor")) {
        Resistor resistor = new Resistor((String) component.get("id"), (String) component.get("type"), deviceNetList,
                componentName, defaultValue, min, max);
        components.add(resistor);
      } else if (component.get("type").equals("transistor")) {
        Transistor transistor = new Transistor((String) component.get("id"), (String) component.get("type"), deviceNetList,
                componentName, defaultValue, min, max);
        components.add(transistor);
      }
    }
    topology = new Topology(topologyID, components);
    return topology;
  }
}
