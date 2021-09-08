package training.java.streams;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class LambdaExample01UnitTest {
  
  @Test
  public void sortProductExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();
    
    products.sort(new Comparator<Product>() {
      @Override
      public int compare(Product p1, Product p2) {
        return p1.getPrice().compareTo(p2.getPrice());
      }
    });
    
    for(Product product: products) {
      System.out.println(product);
    }
  }
  
  @Test
  public void sortProductExampleUsingLambdaUnitTest() {
    List<Product> products = ExampleData.getProducts();
    products.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
    
    for(Product product: products) {
      System.out.println(product);
    }
  }
}
