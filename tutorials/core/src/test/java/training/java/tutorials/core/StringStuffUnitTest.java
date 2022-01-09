package training.java.tutorials.core;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

public class StringStuffUnitTest {

  @Test
  public void stringJoiner() {
    StringJoiner sj = new StringJoiner(", ") ;
    sj.add("str1");
    sj.add("str2");
    sj.add("str3");
    System.out.println(sj.toString());

    sj = new StringJoiner(", ", "{ ", " }");
    sj.add("str1");
    sj.add("str2");
    sj.add("str3");
    System.out.println(sj.toString());

    sj = new StringJoiner("], [", "[", "]");
    sj.add("str1");
    sj.add("str2");
    sj.add("str3");
    System.out.println(sj.toString());
  }

  @Test
  public void concatString() {
   int anh = 13, tien = 15, thang = 18;
   String s2 = String.format("My friends are %d, %d and %d years old", anh, tien, thang);
    System.out.println(s2);
  }
}