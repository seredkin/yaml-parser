package de.ionos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.System.out;

public class YamlParser {
  public static void main(String[] args) throws FileNotFoundException {
    DataParser<Stream<String>, Stream<YamlElement>> parser = new YamlStreamingParser();
    final File file = new File(Objects.requireNonNull(args[0]));
    try (Stream<String> lines = new BufferedReader(new FileReader(file)).lines()) {

      String[] filters = Arrays.asList(args).subList(1, args.length).toArray(new String[]{});
      parser.readData(lines, filters).map(YamlElement::getValue).forEach(out::println);
    }
  }
}
