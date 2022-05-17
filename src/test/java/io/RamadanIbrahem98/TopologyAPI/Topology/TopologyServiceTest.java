package io.RamadanIbrahem98.TopologyAPI.Topology;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Component.Resistor;
import io.RamadanIbrahem98.TopologyAPI.Exception.BadRequestException;
import io.RamadanIbrahem98.TopologyAPI.IO.JsonIO;
import io.RamadanIbrahem98.TopologyAPI.IO.TopologyIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TopologyServiceTest {

  @Mock
  private JsonIO jsonIO;
  @Mock
  private TopologyIO topologyIO;
  private TopologyService underTest;

  @BeforeEach
  void Setup() {
    underTest = new TopologyService();
  }

  @Test
  void getTopologiesShouldReturnEmptyListAtFirst() {
    assertThat(underTest.getTopologies()).isEqualTo(new ArrayList<>());
  }

  @Test
  void getTopologiesWithOneTopology() {
    Topology topology = new Topology("top1", new ArrayList<>());
    ArrayList<Topology> topologies = new ArrayList<>();
    topologies.add(topology);
    underTest.AppendToTopologyList(topology);
    assertThat(underTest.getTopologies().toString()).isEqualTo(topologies.toString());
  }

  @Test
  void queryDevicesForNotFoundTopologyShouldThrow() {
    String topologyID = "top1";
    Throwable throwable = catchThrowable(() -> underTest.queryDevices(topologyID));

    assertThat(throwable).isInstanceOf(BadRequestException.class).hasMessageContaining("Topology with id = " + topologyID + " not found");
  }

  @Test
  void queryDevicesForOneTopology() {
    String topologyID = "top1";
    Resistor r = new Resistor("res1", "resistor", "resistance", 100, 10, 1_000);
    ArrayList<Component> components = new ArrayList<>();
    components.add(r);
    Topology topology = new Topology(topologyID, components);
    underTest.AppendToTopologyList(topology);

    assertThat(underTest.queryDevices(topologyID).toString()).isEqualTo(components.toString());
  }

  @Test
  void deleteTopologyThatDoesNotExistShouldThrow() {
    String topologyID = "top1";

    Throwable throwable = catchThrowable(() -> underTest.deleteTopology(topologyID));

    assertThat(throwable).isInstanceOf(BadRequestException.class).hasMessageContaining("No topology with id = " + topologyID);
  }

  @Test
  void deleteTopologyByID() {
    String topologyID = "top1";

    Topology topology = new Topology(topologyID, new ArrayList<>());

    underTest.AppendToTopologyList(topology);

    boolean isDeleted = underTest.deleteTopology(topologyID);

    assertThat(isDeleted).isTrue();
  }

  @Test
  void writeJsonShouldInvokeJsonIOWriteJson() throws IOException {
    underTest.setJsonIO(jsonIO);
    String topologyID = "top1";
    Topology topology = new Topology(topologyID, new ArrayList<>());
    underTest.AppendToTopologyList(topology);
    underTest.writeJson(topologyID);
    verify(jsonIO).writeJson(topologyID + ".json", topology);
  }

  @Test
  void readJsonShouldInvokeJsonIOReadJsonANDTopologyIO() throws IOException {
    underTest.setJsonIO(jsonIO);
    underTest.setTopologyIO(topologyIO);
    String topologyID = "top1";
    underTest.readJson(topologyID);
    verify(jsonIO).readJson(topologyID);
  }

  @Test
  void queryDevicesWithNetListNodeShouldThrowTopologyNotFound() {
    String topologyID = "top1";
    Throwable throwable = catchThrowable(() -> underTest.queryDevicesWithNetListNode(topologyID, "node1"));
    assertThat(throwable).isInstanceOf(BadRequestException.class).hasMessageContaining("No topology with id = " + topologyID);
  }

  @Test
  void queryDevicesWithNetListNodeShouldReturnEmptyList() {
    String topologyID = "top1";
    Topology topology = new Topology(topologyID, new ArrayList<>());
    underTest.AppendToTopologyList(topology);
    assertThat(underTest.queryDevicesWithNetListNode(topologyID, "node1")).isEqualTo(new ArrayList<>());
  }

  @Test
  void queryDevicesWithNetListNodeShouldReturnOneComponent() {
    String topologyID = "top1";
    Resistor r = new Resistor("res1", "resistor", "resistance", 100, 10, 1_000);
    r.addNetList("node1", "value1");
    ArrayList<Component> components = new ArrayList<>();
    components.add(r);
    Topology topology = new Topology(topologyID, components);
    underTest.AppendToTopologyList(topology);
    assertThat(underTest.queryDevicesWithNetListNode(topologyID, "value1").toString()).isEqualTo(components.toString());
  }
}