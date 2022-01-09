package training.java.tutorials.core;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import training.java.tutorials.core.streams.ExampleData;
import training.java.tutorials.core.streams.Product;

public class LambdaExampleUnitTest {

  @Test
  public void sortProductExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();

    products.sort(new Comparator<Product>() {
      @Override
      public int compare(Product p1, Product p2) {
        return p1.getPrice().compareTo(p2.getPrice());
      }
    });

    for(Product product : products) {
      System.out.println(product);
    }
  }

  @Test
  public void sortProductExampleUsingLambdaUnitTest() {
    List<Product> products = ExampleData.getProducts();
    products.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));

    for(Product product : products) {
      System.out.println(product);
    }
  }

  @Test
  public void countProductExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();
    BigDecimal priceLimit = new BigDecimal("5.00");

    int numberOfCheapProducts = 0;

    for(Product product : products) {
      if(product.getPrice().compareTo(priceLimit) < 0) {
        numberOfCheapProducts++;
      }
    }

    // Because local variables are effectively final, you cannot modify them inside a lambda expression.
    //        products.forEach(product -> {
    //            if (product.getPrice().compareTo(priceLimit) < 0) {
    //                numberOfCheapProducts++; // Error: Variable must be effectively final
    //            }
    //        });

    System.out.println("There are " + numberOfCheapProducts + " cheap products");
  }

  @Test
  public void printCheapProductExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();

    BigDecimal priceLimit = new BigDecimal("5.00");

    List<Product> cheapProducts = new ArrayList<>();

    // BAD PRACTICE! Modifying cheapProducts inside the body of the lambda expression.
    // In general, avoid side effects such as modifying objects from captured variables in lambda expressions.
    products.forEach(product -> {
      if(product.getPrice().compareTo(priceLimit) < 0) {
        cheapProducts.add(product);
      }
    });

    // Print the cheap products.
    cheapProducts.forEach(System.out::println);
  }

  @Test
  public void writingProductsUnitTest() {
    List<Product> products = ExampleData.getProducts();

    try(FileWriter writer = new FileWriter("products.txt")) {
      for(Product product : products) {
        writer.write(product.toString() + "\n");
      }

      // the lambda expression is not allowed to throw any checked exceptions.
      /*
      products.forEach(product -> {
        try {
          writer.write(product.toString() + "\n");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
       */
    } catch(IOException | RuntimeException e) {
      System.err.println("An exception occurred: " + e.getMessage());
    }
  }

  // Print the products that cost less than the price limit.
  static void printProducts(List<Product> products, BigDecimal priceLimit) {
    for(Product product : products) {
      if(product.getPrice().compareTo(priceLimit) < 0) {
        System.out.println(product);
      }
    }
  }

  @FunctionalInterface
  interface ProductFilter {

    boolean accept(Product product);
  }

  // Print the products that cost less than the price limit.
  static void printProducts(List<Product> products, ProductFilter filter) {
    for(Product product : products) {
      if(filter.accept(product)) {
        System.out.println(product);
      }
    }
  }

  @Test
  public void filterProductExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();
    BigDecimal priceLimit = new BigDecimal("5.00");
    printProducts(products, priceLimit);
  }

  @Test
  public void filterProductExampleUsingLambdaUnitTest() {
    List<Product> products = ExampleData.getProducts();
    BigDecimal priceLimit = new BigDecimal("5.00");

    ProductFilter filter = product -> product.getPrice().compareTo(priceLimit) < 0;
    printProducts(products, filter);
  }
}