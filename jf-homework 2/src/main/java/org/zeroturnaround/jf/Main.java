package org.zeroturnaround.jf;

import org.zeroturnaround.jf.homework2.Homework2;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.err.println("Usage: java -jar target/jf-homework2.jar filename");
      System.exit(0);
    }
    Homework2 homework = new Homework2();
    System.out.println(homework.getWords(args[0]));
  }
}
