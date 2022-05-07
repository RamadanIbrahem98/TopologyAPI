package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/v1/topologies")
public class TopologyController {

  private final TopologyService topologyService;

  @Autowired
  public TopologyController(TopologyService topologyService) {
    this.topologyService = topologyService;
  }

  @GetMapping
  public ArrayList<Map<?, ?>> getTopologies() {
    return topologyService.getTopologies();
  }

  @GetMapping("/{topologyID}/components")
  public ArrayList<Component> getDevices(@PathVariable String topologyID) {
    return topologyService.queryDevices(topologyID);
  }

  @GetMapping("/read/{fileName}")
  public Map<?, ?> readJSON(@PathVariable String fileName) {
    try{
      return topologyService.readJson(fileName);
    } catch (IOException ex) {
      HashMap<String, String> error = new HashMap<>();
      error.put("error", "IOException: " + ex.getMessage());
      return error;
    }
  }

  @GetMapping("/write/{topologyID}")
  public String writeJSON(@PathVariable String topologyID) {
    try {
      topologyService.writeJson(topologyID);
      return "Topology written to file successfully";
    } catch (NoSuchElementException ex) {
      return "Topology with id: " + topologyID + " does not exist";
    } catch (IOException ex) {
      return "IOException: " + ex.getMessage();
    }
  }

  @GetMapping("/{topologyID}/netlist/{netListNode}")
  public ArrayList<Map<?, ?>> getDevicesWithNetListNode(@PathVariable String topologyID, @PathVariable String netListNode) {
    return topologyService.queryDevicesWithNetListNode(topologyID, netListNode);
  }

  @DeleteMapping("/{topologyID}")
  public String deleteJSON(@PathVariable String topologyID) {
    try {
      topologyService.deleteTopology(topologyID);
      return "Topology with id: " + topologyID + " deleted successfully";
    } catch (NoSuchElementException ex) {
      return "Topology with id: " + topologyID + " not found";
    }
  }
}
