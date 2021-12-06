package training.java.executive;

import java.util.Arrays;

public class AppDemo {

  public static void main(String[] args) {
    if(args.length > 0) {
      Arrays.stream(args).forEach(System.out::println);
    } else {
      System.out.println("No arguments pass in");
    }
  }
}