package io.RamadanIbrahem98.TopologyAPI.IO;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class JsonIOTest {

  @Test
  void readNotFoundJsonShouldThrow() {
    Throwable throwable = catchThrowable(() -> JsonIO.readJson("notFound.json"));
    assertThat(throwable)
            .isInstanceOf(NoSuchFileException.class)
            .hasMessageContaining("No such file");
  }

  @Test
  void writeJson() {
    Throwable throwable = catchThrowable(() -> JsonIO.writeJson("ioexception*/.json", "{\"test field\":\"test value\"}"));

    assertThat(throwable)
            .isInstanceOf(IOException.class)
            .hasMessageContaining("Error Writing to File");
  }
}