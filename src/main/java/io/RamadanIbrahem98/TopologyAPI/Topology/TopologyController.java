package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Exception.ExceptionResponse;
import io.RamadanIbrahem98.TopologyAPI.IO.TopologyIO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/topologies", produces = "application/json")
@Tag(name = "Topology", description = "The Topology API")
public class TopologyController {

  private final TopologyService topologyService;

  @Autowired
  public TopologyController(TopologyService topologyService) {
    this.topologyService = topologyService;
  }

  @GetMapping
  @Operation(summary = "Get All Topologies", description = "Query All Topologies from Memory", tags = {"Topology"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(array = @ArraySchema(schema = @Schema(implementation = Topology.class))))})
  public ArrayList<Topology> getTopologies() {
    return topologyService.getTopologies();
  }

  @GetMapping("/{topologyID}/components")
  @Operation(summary = "Get Topology Devices by ID", description = "Query all the Devices from a specific topology by topology id", tags = {"Topology"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(array = @ArraySchema(schema = @Schema(implementation = Component.class)))),
          @ApiResponse(responseCode = "400", description = "No Topology Found with Provided ID",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
  public ArrayList<Component> getDevices(@PathVariable String topologyID) {
    return topologyService.queryDevices(topologyID);
  }

  @GetMapping("/read/{fileName}")
  @Operation(summary = "Read a Topology From Disk", description = "Read a Topology from Disk Providing the File Name", tags = {"Topology"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(schema = @Schema(implementation = Topology.class))),
          @ApiResponse(responseCode = "400", description = "No Topology Found On Disk with the Provided Name",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  public Topology readJSON(@PathVariable String fileName) {
    Map<?, ?> topologyMap = topologyService.readJson(fileName);
    return new TopologyIO().getTopologyByJson(topologyMap);
  }

  @GetMapping("/write/{topologyID}")
  @Operation(summary = "Write a Topology To Disk", description = "Write a Topology from Memory to Disk Providing the Topology ID", tags = {"Topology"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(schema = @Schema(implementation = String.class))),
          @ApiResponse(responseCode = "400", description = "No Topology Found On Memory with the Provided ID",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  public String writeJSON(@PathVariable String topologyID) {
    topologyService.writeJson(topologyID);
    return "Topology written to file successfully";
  }

  @GetMapping("/{topologyID}/netlist/{netListNode}")
  @Operation(summary = "Get All Devices Connected to A Netlist Node", description = "Query All Devices of a Specific Topology which are Connected to the Same Netlist Node", tags = {"Topology"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(array = @ArraySchema(schema = @Schema(implementation = Component.class)))),
          @ApiResponse(responseCode = "400", description = "No Topology Found On Memory with the Provided ID",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
  })
  public ArrayList<Component> getDevicesWithNetListNode(@PathVariable String topologyID, @PathVariable String netListNode) {
    return topologyService.queryDevicesWithNetListNode(topologyID, netListNode);
  }

  @DeleteMapping("/{topologyID}")
  @Operation(summary = "Delete a Topology From Memory By ID", description = "Delete a Topology from Memory Providing the ID", tags = {"Topology"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(schema = @Schema(implementation = String.class))),
          @ApiResponse(responseCode = "400", description = "No Topology Found On Memory with the Provided ID",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
  })
  public String deleteJSON(@PathVariable String topologyID) {
    topologyService.deleteTopology(topologyID);
    return "Topology with id: " + topologyID + " deleted successfully";
  }
}
