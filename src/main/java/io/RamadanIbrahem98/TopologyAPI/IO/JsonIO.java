package io.RamadanIbrahem98.TopologyAPI.IO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Map;

public class JsonIO {

  /**
   * Reads a JSON file from disk from a default location and returns a Map object.
   *
   * @param fileName the name of the file to read from disk
   * @return a Map object representing the JSON file
   * @throws IOException if there was an error reading from disk
   */
  public Map<?, ?> readJson(String fileName) throws IOException {
    Map<?, ?> map;
    try {
      Gson gson = new Gson();
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      map = gson.fromJson(reader, Map.class);
      reader.close();
    } catch (NoSuchFileException e) {
      throw new NoSuchFileException("No such file");
    } catch (IOException e) {
      throw new IOException("Error Reading File");
    }
    return map;
  }

  /**
   * Reads a JSON file from disk to memory and returns a map object.
   *
   * @param filePath the path to the file to read from disk
   * @param fileName the name of the file to read from disk
   * @throws IOException if there was an error reading from disk
   */
  public Map<?, ?> readJson(String filePath, String fileName) throws IOException {
    Map<?, ?> map;
    try {
      Gson gson = new Gson();
      Reader reader = Files.newBufferedReader(Paths.get(filePath + fileName));
      map = gson.fromJson(reader, Map.class);
      reader.close();
    } catch (NoSuchFileException e) {
      throw new NoSuchFileException("No such file");
    } catch (IOException e) {
      throw new IOException("Error Reading File");
    }
    return map;
  }

  /**
   * Writes a JSON file to disk from a generic object.
   *
   * @param fileName the name of the file to write to disk
   * @param o        the object to write to disk
   * @throws IOException if there was an error writing to disk
   */
  public void writeJson(String fileName, Object o) throws IOException {
    JsonObject convertedObject = new Gson().fromJson(o.toString(), JsonObject.class);
    try {
      Writer writer = new FileWriter(fileName);
      new Gson().toJson(convertedObject, writer);
      writer.close();
    } catch (IOException e) {
      throw new IOException("Error Writing to File");
    }
  }
}
