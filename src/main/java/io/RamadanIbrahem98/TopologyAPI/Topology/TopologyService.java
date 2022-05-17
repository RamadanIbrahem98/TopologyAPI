package io.RamadanIbrahem98.TopologyAPI.Topology;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Component.Resistor;
import io.RamadanIbrahem98.TopologyAPI.Component.Transistor;
import io.RamadanIbrahem98.TopologyAPI.Exception.BadRequestException;
import io.RamadanIbrahem98.TopologyAPI.Exception.InternalServerError;
import io.RamadanIbrahem98.TopologyAPI.Exception.TopologyNotFoundException;
import io.RamadanIbrahem98.TopologyAPI.IO.JsonIO;
import io.RamadanIbrahem98.TopologyAPI.IO.TopologyIO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

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

  /**
   * Get all topologies.
   * <p>
   * Query about all the topologies stored in memory.
   *
   * @return Topology list from memory.
   */
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

  /**
   * Get topology by id.
   * <p>
   * Query about a specific topology by id.
   *
   * @param topologyID Topology id
   * @return A list of all the components in the topology
   * @throws TopologyNotFoundException if topology id is not found in memory
   */
  public ArrayList<Component> queryDevices(String topologyID) {
    ArrayList<Component> devices = new ArrayList<>();
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        devices = topology.getComponents();
      }
    }
    return devices;
  }

  /**
   * Delete a topology from memory by id.
   *
   * @param topologyID Topology id
   * @return true if topology is deleted, or else throws TopologyNotFoundException
   * @throws TopologyNotFoundException if topology id is not found in memory
   */
  public boolean deleteTopology(String topologyID) throws TopologyNotFoundException {
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        topologyList.remove(topology);
        return true;
      }
    }
    throw new TopologyNotFoundException("No topology with id = " + topologyID);
  }

  /**
   * Find a topology by id.
   * <p>
   * Iterate over the topology list currently in memory and find a topology by id.
   *
   * @param topologyID Topology id
   * @return the found topology object or null if not found
   */
  private Topology getTopologyByIdOrNull(String topologyID) {
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        return topology;
      }
    }
    return null;
  }

  /**
   * Write a topology to file.
   * <p>
   * Save a topology from memory to a file on disk.
   *
   * @param topologyID Topology id
   * @throws TopologyNotFoundException if topology id is not found in memory
   * @throws InternalServerError       if there was an error during writing to file
   */
  public void writeJson(String topologyID) throws TopologyNotFoundException, InternalServerError {
    Topology topology = getTopologyByIdOrNull(topologyID);

    if (topology == null) {
      throw new TopologyNotFoundException("No topology with id = " + topologyID);
    }

    try {
      JsonIO.writeJson(topologyID + ".json", topology);
    } catch (IOException e) {
      throw new InternalServerError("Failed to write json file");
    }
  }

  /**
   * Read a topology from file.
   * <p>
   * Read a topology from a file on disk to memory.
   *
   * @param fileName Topology file name
   * @return Topology object
   * @throws BadRequestException if the topology was already in memory
   * @throws InternalServerError if there was an error during reading from file
   */
  public Map<?, ?> readJson(String fileName) throws BadRequestException, InternalServerError {
    Map<?, ?> map;
    try {
      map = JsonIO.readJson(fileName);
    } catch (IOException e) {
      throw new InternalServerError("Failed to read json file");
    }

    Topology topology = TopologyIO.getTopologyByJson(map);

    for (Topology top : topologyList) {
      if (top.getId().equals(topology.getId())) {
        throw new BadRequestException("Topology with id = " + topology.getId() + " already exists");
      }
    }

    topologyList.add(topology);

    return map;
  }

  /**
   * Query devices that are connected to a specific netlist node.
   *
   * @param topologyID  Topology id
   * @param netListNode Netlist node
   * @return A list of all the components connected to that netlist node
   * @throws TopologyNotFoundException if topology id is not found in memory
   */
  public ArrayList<Map<?, ?>> queryDevicesWithNetListNode(String topologyID, String netListNode) throws TopologyNotFoundException {
    ArrayList<Map<?, ?>> devices = new ArrayList<>();
    boolean foundTopology = false;
    Type type = new TypeToken<Map<?, ?>>() {
    }.getType();

    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        foundTopology = true;
        for (Component component : topology.getComponents()) {
          if (component.getNetList().containsValue(netListNode)) {
            Map<?, ?> map = new Gson().fromJson(component.toString(), type);
            devices.add(map);
          }
        }
        break;
      }
    }
    if (!foundTopology) {
      throw new TopologyNotFoundException("No topology with id = " + topologyID);
    }
    return devices;
  }
}