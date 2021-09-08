package training.java.streams;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class LambdaExample012UnitTest {
  
  // Print the products that cost less than the price limit.
  static void printProducts(List<Product> products, BigDecimal priceLimit) {
    for(Product product : products) {
      if(product.getPrice().compareTo(priceLimit) < 0) {
        System.out.println(product);
      }
    }
  }
  
  @FunctionalInterface
  interface ProductFilter { boolean accept(Product product);}
  
  // Print the products that cost less than the price limit.
  static void printProducts(List<Product> products, ProductFilter filter) {
    for (Product product : products) {
      if (filter.accept(product)) {
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
