package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/topologies")
public class TopologyController {

  private final TopologyService topologyService;

  @Autowired
  public TopologyController(TopologyService topologyService) {
    this.topologyService = topologyService;
  }

  @GetMapping
  public ArrayList<Topology> getTopologies() {
    return topologyService.getTopologies();
  }

  @GetMapping("/{topologyID}/components")
  public ArrayList<Component> getDevices(@PathVariable String topologyID) {
    return topologyService.queryDevices(topologyID);
  }

  @GetMapping("/read/{fileName}")
  public Map<?, ?> readJSON(@PathVariable String fileName) {
    return topologyService.readJson(fileName);
  }

  @GetMapping("/write/{topologyID}")
  public String writeJSON(@PathVariable String topologyID) {
    topologyService.writeJson(topologyID);
    return "Topology written to file successfully";
  }

  @GetMapping("/{topologyID}/netlist/{netListNode}")
  public ArrayList<Component> getDevicesWithNetListNode(@PathVariable String topologyID, @PathVariable String netListNode) {
    return topologyService.queryDevicesWithNetListNode(topologyID, netListNode);
  }

  @DeleteMapping("/{topologyID}")
  public String deleteJSON(@PathVariable String topologyID) {
    topologyService.deleteTopology(topologyID);
    return "Topology with id: " + topologyID + " deleted successfully";
  }
}
