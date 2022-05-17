package io.RamadanIbrahem98.TopologyAPI.IO;

import io.RamadanIbrahem98.TopologyAPI.Component.Component;
import io.RamadanIbrahem98.TopologyAPI.Component.Resistor;
import io.RamadanIbrahem98.TopologyAPI.Topology.Topology;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TopologyIOTest {

  static Map<Object, Object> underTest;
  static Topology topology;
  private final TopologyIO topologyIO = new TopologyIO();

  @BeforeAll
  static void beforeAll() {
    Resistor r1 = new Resistor("res1", "resistor", "resistance", 100, 10, 1_000);
    r1.addNetList("t1", "vdd");
    r1.addNetList("t2", "gnd");

    ArrayList<Component> components = new ArrayList<>();

    components.add(r1);

    topology = new Topology("top1", components);

    underTest = new HashMap<>();
    underTest.put("id", "top1");

    ArrayList<Map<Object, Object>> componentsUnderTest = new ArrayList<>();

    Map<Object, Object> resistorUnderTest = new HashMap<>();
    resistorUnderTest.put("id", "res1");
    resistorUnderTest.put("type", "resistor");

    Map<String, String> netlist = new HashMap<>();
    netlist.put("t1", "vdd");
    netlist.put("t2", "gnd");
    resistorUnderTest.put("netlist", netlist);

    HashMap<String, Double> specs = new HashMap<>();

    specs.put("default", 100.0);
    specs.put("min", 10.0);
    specs.put("max", 1000.0);

    resistorUnderTest.put("resistance", specs);
    componentsUnderTest.add(resistorUnderTest);
    underTest.put("components", componentsUnderTest);
  }

  @Test
  void getTopologyByJsonReturnsATopology() {
    assertThat(topologyIO.getTopologyByJson(underTest)).isInstanceOf(Topology.class);
  }

  @Test
  void getTopologyByJson() {
    assertThat(topologyIO.getTopologyByJson(underTest)).isEqualTo(topology);
  }

  @Test
  void getTopologyByJsonComponentClassShouldMatch() {
    assertThat(topologyIO.getTopologyByJson(underTest).getComponents().get(0)).isInstanceOf(Resistor.class);
  }
}