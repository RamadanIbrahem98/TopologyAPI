package io.RamadanIbrahem98.TopologyAPI.Topology;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Component.Resistor;
import io.RamadanIbrahem98.TopologyAPI.Component.Transistor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class TopologyService {
  ArrayList<Topology> topologyList = new ArrayList<>();

  {
    Resistor r1 = new Resistor("res1", "resistor", "resistance", 100, 10, 1_000);
    r1.addNetList("t1", "vdd");
    r1.addNetList("t2", "gnd");

    Transistor t1 = new Transistor("t1", "transistor", "m(l)", 1.5, 1, 2);
    t1.addNetList("drain", "n1");
    t1.addNetList("gate", "vin");
    t1.addNetList("source", "vss");

    ArrayList<Component> components = new ArrayList<>();

    components.add(r1);
    components.add(t1);

    Topology topology = new Topology("top1", components);

    topologyList.add(topology);

    Resistor r2 = new Resistor("res2", "resistor", "resistance", 100, 20, 2_000);
    r2.addNetList("t1", "vcc");
    r2.addNetList("t2", "gnd");

    Transistor t2 = new Transistor("t1", "transistor", "m(l)", 3, 1.5, 5);
    t2.addNetList("drain", "n2");
    t2.addNetList("gate", "vin");
    t2.addNetList("source", "vss");

    ArrayList<Component> components2 = new ArrayList<>();

    components2.add(r2);
    components2.add(t2);

    Topology topology2 = new Topology("top2", components2);

    topologyList.add(topology2);
  }

  public ArrayList<Map<?, ?>> getTopologies() {
    ArrayList<Map<?, ?>> topologies = new ArrayList<>();
    Type type = new TypeToken<Map<?, ?>>() {
    }.getType();
    for (Topology topology : topologyList) {
      Map<?, ?> map = new Gson().fromJson(topology.toString(), type);
      topologies.add(map);
    }
    return topologies;
  }

  public ArrayList<Component> queryDevices(String topologyID) {
    ArrayList<Component> devices = new ArrayList<>();
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        devices = topology.getComponents();
      }
    }
    return devices;
  }

  public boolean deleteTopology(String topologyID) throws NoSuchElementException {
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        topologyList.remove(topology);
        return true;
      }
    }
    throw new NoSuchElementException("No topology with id = " + topologyID);
  }

  private Topology getTopologyByIdOrNull(String topologyID) {
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        return topology;
      }
    }
    return null;
  }

  public void writeJson(String topologyID) throws IOException, NoSuchElementException {
    Topology topology = getTopologyByIdOrNull(topologyID);

    if (topology != null) {
      JsonObject convertedObject = new Gson().fromJson(topology.toString(), JsonObject.class);
      try {
        Writer writer = new FileWriter(topologyID + ".json");
        new Gson().toJson(convertedObject, writer);
        writer.close();
      } catch (IOException e) {
        throw new IOException("Error Writing to File");
      }
    } else {
      throw new NoSuchElementException("No topology with id = " + topologyID);
    }
  }

  @SuppressWarnings("unchecked")
  private Topology getTopologyByJson(Map<?, ?> json) {
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

  public Map<?, ?> readJson(String fileName) throws IOException {
    Map<?, ?> map;
    try {
      Gson gson = new Gson();

      Reader reader = Files.newBufferedReader(Paths.get(fileName));

      map = gson.fromJson(reader, Map.class);

      Topology topology = getTopologyByJson(map);

      reader.close();

      topologyList.add(topology);
    } catch (NoSuchFileException e) {
      throw new NoSuchFileException("No such file");
    } catch (IOException e) {
      throw new IOException("Error Reading File");
    }
    return map;
  }

  public ArrayList<Map<?, ?>> queryDevicesWithNetListNode(String topologyID, String netListNode) {
    ArrayList<Map<?, ?>> devices = new ArrayList<>();
    Type type = new TypeToken<Map<?, ?>>() {
    }.getType();

    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        for (Component component : topology.getComponents()) {
          if (component.getNetList().containsValue(netListNode)) {
            Map<?, ?> map = new Gson().fromJson(component.toString(), type);
            devices.add(map);
          }
        }
      }
    }

    return devices;
  }
}