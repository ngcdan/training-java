package training.java.streams.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import training.java.streams.Category;
import training.java.streams.ExampleData;
import training.java.streams.Product;

public class BasicStreamsExampleUnitTest {

  @Test
  public void basicStreamUnitTest() {
    List<Product> products = ExampleData.getProducts();

    // Streams are lazy - without a terminal operation, no work is done
    // When you call intermediate operations on a stream, you're only building the pipeline;
    // no elements are flowing through it yet
    Stream<Product> stream = products.stream().map(product -> {
      System.out.println(product);
      return product;
    });

    // When you call a terminal operation, the stream will do the work
    stream.forEach(product -> {});
  }

  @Test
  public void basicInitStreamUnitTest() {
    List<Product> products = ExampleData.getProducts();
    Stream<Product> stream1 = products.stream();
    String[] array = new String[]{"one", "two", "three"};
    Stream<String> stream2 = Arrays.stream(array);
    Stream<String> stream3 = Stream.of("one", "two", "three");

    // Create a Stream with zero or one elements with ofNullable()
    Stream<String> stream4 = Stream.ofNullable("four");
    Stream<?> stream5 = Stream.empty();
    IntStream dice = new Random().ints(1, 7);

    // example BufferedReader.lines()
    try(BufferedReader in = new BufferedReader(new FileReader("README.md", StandardCharsets.UTF_8))) {
      in.lines().forEach(System.out::println);
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void filteringAndTransformingStreamUnitTest() {
    List<Product> products = ExampleData.getProducts();

    // The filter() intermediate operation filters elements from the stream
    products.stream().filter(product -> product.getCategory() == Category.FOOD).forEach(System.out::println);

    // The map() intermediate operation does a one-to-one transformation on each element
    products.stream().map(product -> String.format("The price of %s is $ %.2f", product.getName(), product.getPrice()))
      .forEach(System.out::println);

    Pattern spaces = Pattern.compile("\\s+");
    products.stream().flatMap(product -> spaces.splitAsStream(product.getName())).forEach(System.out::println);
  }

  @Test
  public void searchingStreamUnitTest() {
    List<Product> products = ExampleData.getProducts();

    // findFirst() will return an Optional with the first element in the stream, or an empty Optional
    Optional<Product> opt = products.stream().filter(product -> product.getCategory() == Category.OFFICE).findFirst();
    opt.ifPresent(System.out::println);

    // If you only want to check if the stream contains an element that matches your search criteria,
    // then you can use anyMatch(), which will return a boolean result
    // Unlike findFirst() and findAny(), you don't have to filter first - anyMatch() takes a Predicate
    boolean foundOfficeProduct = products.stream().anyMatch(product -> product.getCategory() == Category.OFFICE);
    System.out.println("Does the list contain at least one office product? " + foundOfficeProduct);

    // To check if all elements of the stream match specific criteria, use allMatch()
    BigDecimal priceLimit = new BigDecimal("10.00");
    boolean allProductsAreCheap = products.stream().allMatch(product -> product.getPrice().compareTo(priceLimit) < 0);
    System.out.println("Are all products cheap? " + allProductsAreCheap);

    // noneMatch() returns the opposite of anyMatch()
    boolean allProductsAreExpensive = products.stream()
      .noneMatch(product -> product.getPrice().compareTo(priceLimit) < 0);
    System.out.println("Are all products expensive? " + allProductsAreExpensive);
  }
}