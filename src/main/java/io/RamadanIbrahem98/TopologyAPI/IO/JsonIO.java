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

  public static Map<?, ?> readJson(String fileName) throws IOException {
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

  public static Map<?, ?> readJson(String filePath, String fileName) throws IOException {
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

  public static void writeJson(String fileName, Object o) throws IOException {
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
