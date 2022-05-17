package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Exception.BadRequestException;
import io.RamadanIbrahem98.TopologyAPI.Exception.InternalServerError;
import io.RamadanIbrahem98.TopologyAPI.IO.JsonIO;
import io.RamadanIbrahem98.TopologyAPI.IO.TopologyIO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class TopologyService {
  private final ArrayList<Topology> topologyList = new ArrayList<>();
  private JsonIO jsonIO = new JsonIO();
  private TopologyIO topologyIO = new TopologyIO();

  public void AppendToTopologyList(Topology topology) {
    topologyList.add(topology);
  }

  public void setJsonIO(JsonIO jsonIO) {
    this.jsonIO = jsonIO;
  }

  public void setTopologyIO(TopologyIO topologyIO) {
    this.topologyIO = topologyIO;
  }

  /**
   * Get all topologies.
   * <p>
   * Query about all the topologies stored in memory.
   *
   * @return Topology list from memory.
   */
  public ArrayList<Topology> getTopologies() {
    return topologyList;
  }

  /**
   * Get topology by id.
   * <p>
   * Query about a specific topology by id.
   *
   * @param topologyID Topology id
   * @return A list of all the components in the topology
   * @throws BadRequestException if topology id is not found in memory
   */
  public ArrayList<Component> queryDevices(String topologyID) {
    Topology topology = getTopologyByIdOrNull(topologyID);
    if (topology == null) {
      throw new BadRequestException("Topology with id = " + topologyID + " not found");
    }

    return topology.getComponents();
  }

  /**
   * Delete a topology from memory by id.
   *
   * @param topologyID Topology id
   * @return true if topology is deleted, or else throws BadRequestException
   * @throws BadRequestException if topology id is not found in memory
   */
  public boolean deleteTopology(String topologyID) throws BadRequestException {
    for (Topology topology : topologyList) {
      if (topology.getId().equals(topologyID)) {
        topologyList.remove(topology);
        return true;
      }
    }
    throw new BadRequestException("No topology with id = " + topologyID);
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
   * @throws BadRequestException if topology id is not found in memory
   * @throws InternalServerError if there was an error during writing to file
   */
  public void writeJson(String topologyID) throws BadRequestException, InternalServerError {
    Topology topology = getTopologyByIdOrNull(topologyID);

    if (topology == null) {
      throw new BadRequestException("No topology with id = " + topologyID);
    }

    try {
      jsonIO.writeJson(topologyID + ".json", topology);
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
      map = jsonIO.readJson(fileName);
    } catch (IOException e) {
      throw new InternalServerError("Failed to read json file");
    }

    Topology topology = topologyIO.getTopologyByJson(map);

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
   * @throws BadRequestException if topology id is not found in memory
   */
  public ArrayList<Component> queryDevicesWithNetListNode(String topologyID, String netListNode) throws BadRequestException {
    ArrayList<Component> devices = new ArrayList<>();
    boolean foundTopology = false;

    Topology topology = getTopologyByIdOrNull(topologyID);

    if (topology == null) {
      throw new BadRequestException("Topology with id = " + topologyID + " not found");
    }

    for (Component component : topology.getComponents()) {
      if (component.getNetList().containsValue(netListNode)) {
        devices.add(component);
      }
    }
    return devices;
  }
}