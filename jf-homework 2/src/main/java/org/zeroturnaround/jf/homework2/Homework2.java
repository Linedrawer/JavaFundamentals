package org.zeroturnaround.jf.homework2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Homework2 {

  public Map<String, Long> getWords(String filename) throws IOException {
      List<String> list = Files.lines(Paths.get(filename))
                      .flatMap(line -> Stream.of(line.replaceAll("[^a-zA-Z]"," ") // deletes all no-character symbols
                      .replaceAll("\\b\\w\\b\\s?", "")                           // deletes all one-character symbols
                      .toLowerCase()                                            // converts all characters to lowercase
                      .split("\\s+")))                                  // splits words by space
                      .filter(s -> !s.isEmpty())                              // filters out empty
                      .collect(Collectors.toList());                         // puts all words to list

       // maps list to map storing works as keys and number of their occurrences as value
      Map<String, Long> map = list.stream()
              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      Map<String, Long> result = new LinkedHashMap<>();                 // initialises a final map

      Stream<Map.Entry<String, Long>> stream = map.entrySet().stream(); // stream to precess unsorted map
        // sorts map by values in reversed order, if value is the same keys are sorted alphabetically
      stream.sorted((x, y) -> x.getValue().equals(y.getValue()) ? x.getKey().compareTo(y.getKey()) :
                                                                    y.getValue().compareTo(x.getValue()))
              .limit(5)                                                     // limits map to five first pairs
              .forEachOrdered(e -> result.put(e.getKey(), e.getValue()) ); // puts each pair to final map

      return result;
  }

}
