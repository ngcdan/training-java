package training.java.streams.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import training.java.streams.Category;
import training.java.streams.ExampleData;
import training.java.streams.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class AdvanceStreamsExampleUnitTest {
  
  @Test
  public void buildStreamUnitTest() {
    Stream<UUID> uuids = Stream.generate(UUID::randomUUID);
    uuids.limit(10).forEach(System.out::println);
    
    Stream<BigInteger> powersOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));
    powersOfTwo.limit(20).forEach(System.out::println);
    
    Stream<Character> alphabet = Stream.iterate('A', letter -> letter <= 'Z', letter -> (char) (letter + 1));
    alphabet.forEach(System.out::print);
    System.out.println();
    
    Stream.Builder<String> builder = Stream.builder();
    builder.add("one");
    builder.add("two");
    builder.add("three");
    Stream<String> stream = builder.build();
    stream.forEach(System.out::println);
  }
  
  @Test
  public void reducingStreamUnitTest() {
    List<Product> products = ExampleData.getProducts();
    
    // The first version of reduce() takes a BinaryOperator to accumulate elements into a final result
    // It returns an Optional; if the stream is empty, the result is an empty Optional
    Optional<BigDecimal> opt = products.stream()
      .map(Product::getPrice)
      .reduce((result, element) -> result.add(element)); // Can also be written with a method reference: BigDecimal::add
    
    opt.ifPresentOrElse(
      total -> System.out.printf("The total value of all products is: $ %.2f%n", total),
      () -> System.out.println("There are no products"));
    
    // The second version of reduce() takes a starting value and a BinaryOperator
    // It returns a value instead of an Optional; if the stream is empty, the result will be the starting value
    BigDecimal total = products.stream()
      .map(Product::getPrice)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.printf("The total value of all products is: $ %.2f%n", total);
    
    // The third version of reduce()
    // The type of its result value may be different than the type of the elements in the stream
    // The third parameter is a combiner function to combine intermediate results; this is useful for example for a parallel
    // stream, where different threads compute intermediate results that have to be combined into a final result
    BigDecimal total2 = products.stream()
      .reduce(BigDecimal.ZERO, (result, product) -> result.add(product.getPrice()), BigDecimal::add);
    System.out.printf("The total value of all products is: $ %.2f%n", total2);
  }
  
  @Test
  public void collectingAndReducingStreamUnitTest() {
    List<Product> products = ExampleData.getProducts();
    // You can reduce stream elements into an ArrayList with reduce(), but this is inefficient because reduce()
    // is designed for the result container to be immutable; so you need to create intermediate lists and
    // copy elements between them.
    List<String> productNames1 = products.stream().reduce(
      new ArrayList<>(),
      (list, product) -> {
        ArrayList<String> newList = new ArrayList<>(list);
        newList.add(product.getName());
        return newList;
      },
      (list1, list2) -> {
        ArrayList<String> newList = new ArrayList<>(list1);
        newList.addAll(list2);
        return newList;
      });
    
    // Collection is mutable reduction. This is much more efficient if you have a mutable result container
    // such as an ArrayList, avoiding most of the copying that needs to be done when using reduce().
    List<String> productNames2 = products.stream().collect(
      ArrayList::new,
      (list, product) -> list.add(product.getName()),
      ArrayList::addAll);
    
    productNames2.forEach(System.out::println);
    Assertions.assertEquals(productNames1.size(), productNames2.size());
  }
  
  @Test
  public void totalPerCategoryProductUnitTest() {
    List<Product> products = ExampleData.getProducts();
    // Using Collectors.toMap() to compute the total price of products per category.
    Map<Category, BigDecimal> totalPerCategory1 = products.stream()
      .collect(Collectors.toMap(
        Product::getCategory,   // Key mapper function
        Product::getPrice,      // Value mapper function
        BigDecimal::add));      // Merge function
    System.out.println(totalPerCategory1);
    
    Map<Category, BigDecimal> totalPerCategory2 = products.stream()
      .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getPrice, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    
    Assertions.assertEquals(totalPerCategory1.get(Category.FOOD), totalPerCategory2.get(Category.FOOD));
  }
  
  @Test
  public void groupByProductUnitTest() {
    List<Product> products = ExampleData.getProducts();
    
    // Group products by category.
    Map<Category, List<Product>> productsByCategory = products.stream()
      .collect(Collectors.groupingBy(Product::getCategory));
    
    // Suppose that we want to get a map of product names per category (instead of products).
    // Mapping products to product names with map(...) won't work, because the result of the map(...) operation is a stream of strings.
    // The information about the categories will have been thrown away, so you can't group on categories anymore in the collector.
    //        Map<Category, List<String>> productNamesByCategory = products.stream()
    //                .map(Product::getName)
    //                .collect(Collectors.groupingBy(...));
    
    // Create a Map of product names grouped by category.
    // We use a downstream collector, created by Collectors.mapping(...), to map grouped products to product names.
    // Note that Collectors.mapping(...) takes a mapping function and another downstream collector to determine how to collect its result.
    Map<Category, List<String>> productNamesByCategory = products.stream()
      .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())));
    
    productNamesByCategory.forEach((category, names) -> {
      System.out.println(category);
      names.forEach(name -> System.out.println("- " + name));
    });
  }
  
  @Test
  public void partitionedProductUnitTest() {
    List<Product> products = ExampleData.getProducts();
    BigDecimal priceLimit = new BigDecimal("5.00");
    
    Map<Boolean, List<Product>> partitionedProducts = products.stream()
      .collect(Collectors.partitioningBy(product -> product.getPrice().compareTo(priceLimit) < 0));
    
    System.out.println("Cheap products: ");
    partitionedProducts.get(true).forEach(System.out::println);
    
    System.out.println("Expensive products: ");
    partitionedProducts.get(false).forEach(System.out::println);
  }
  
  @Test
  public void specializedStreamsExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();
    // Compared to Stream<Double>, this avoids boxing and unboxing primitive double values into and out of Double wrapper objects.
    DoubleStream prices = products.stream()
      .mapToDouble(product -> product.getPrice().doubleValue());
    
    // There are some methods that are not available on regular streams, such as sum().
    double total = prices.sum();
    System.out.printf("The sum of all product prices is $ %.2f%n", total);
    
    // The method summaryStatistics() provides statistics about the elements in the stream.
    // See the API documentation of the specialized streams for more methods.
    DoubleSummaryStatistics statistics = products.stream()
      .mapToDouble(product -> product.getPrice().doubleValue())
      .summaryStatistics();
    
    System.out.printf("Count  : %d%n", statistics.getCount());
    System.out.printf("Sum    : $ %.2f%n", statistics.getSum());
    System.out.printf("Minimum: $ %.2f%n", statistics.getMin());
    System.out.printf("Maximum: $ %.2f%n", statistics.getMax());
    System.out.printf("Average: $ %.2f%n", statistics.getAverage());
  }
}
