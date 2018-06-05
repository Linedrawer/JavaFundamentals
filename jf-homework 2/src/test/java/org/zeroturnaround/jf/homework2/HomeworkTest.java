package org.zeroturnaround.jf.homework2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HomeworkTest {

  @Test
  public void testREADMEmd() throws IOException {
    Map<String, Long> expected = new LinkedHashMap<>();
    expected.put("the", 28L);
    expected.put("of", 21L);
    expected.put("lorem", 19L);
    expected.put("ipsum", 19L);
    expected.put("it", 11L);

    Map<String, Long> actual = new Homework2().getWords("lorem-ipsum.txt");
    assertEquals(expected, actual);
  }

  @Test
  public void testCompustist() throws IOException {
    Map<String, Long> expected = new LinkedHashMap<>();
    expected.put("the", 34L);
    expected.put("of", 22L);
    expected.put("was", 15L);
    expected.put("and", 12L);
    expected.put("in", 12L);

    Map<String, Long> actual = new Homework2().getWords("computist.txt");
    assertEquals(expected, actual);
  }

  @Test
  public void testPassports() throws IOException {
    Map<String, Long> expected = new LinkedHashMap<>();
    expected.put("the", 133L);
    expected.put("of", 101L);
    expected.put("to", 48L);
    expected.put("in", 47L);
    expected.put("and", 26L);

    Map<String, Long> actual = new Homework2().getWords("passports.txt");
    assertEquals(expected, actual);
  }

}
