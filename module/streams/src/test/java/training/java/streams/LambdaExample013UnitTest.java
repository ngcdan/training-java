package training.java.streams;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class LambdaExample013UnitTest {
  
  @Test
  public void countProductExampleUnitTest() {
    List<Product> products = ExampleData.getProducts();
    
    BigDecimal priceLimit = new BigDecimal("5.00");
    
    int numberOfCheapProducts = 0;
    
    for (Product product : products) {
      if (product.getPrice().compareTo(priceLimit) < 0) {
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
}
