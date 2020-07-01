package com.ionos.cloud;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {
  DataParser<Stream<String>, Stream<YamlElement>> parser = new YamlStreamingParser();

  public static void main(String[] args) throws Exception {
    new ParserTest().testReadAll();
  }

  @Test
  public void testReadAll() throws IOException {
    final File file = new File("samples/sample-00.yaml");
    try (Stream<String> lines = new BufferedReader(new FileReader(file)).lines()) {

      List<YamlElement> allData = parser.readData(lines).collect(Collectors.toList());
      assertEquals(allData.get(allData.size() - 1).getLine(), 28);
      assertTrue(allData.size() < 28);

    }
  }

  @Test
  public void testReadAndFilter() throws IOException {
    final File file = new File("samples/sample-00.yaml");

    try (Stream<String> lines = new BufferedReader(new FileReader(file)).lines()) {

      List<YamlElement> filteredData = parser.readData(lines,
              ".version",
              ".services.db.volumes.0",
              ".services.wordpress.environment.WORDPRESS_DB_HOST")
              .collect(Collectors.toList());
      assertEquals(filteredData.get(0).getValue(), "3.3");
      assertEquals(filteredData.get(1).getValue(), "db_data:/var/lib/mysql");
      assertEquals(filteredData.get(2).getValue(), "db:3306");

    }
  }
}
