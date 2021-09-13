package training.java.streams;

import java.math.BigDecimal;
import java.util.List;

public class MethodReferenceUnitTest {
  interface ProductFactory {
    Product create(Category category, String name, BigDecimal price);
  }
  
  static boolean isExpensive(Product product) {
    return product.getPrice().compareTo(new BigDecimal("5.00")) >= 0;
  }
  
  public static void main(String[] args) {
    List<Product> products = ExampleData.getProducts();
    
    // products.forEach(product -> System.out.println(product));
    products.forEach(System.out::println);
    
    // Method reference to a static method.
    products.removeIf(MethodReferenceUnitTest::isExpensive);
    
    products.stream().map(Product::getName).forEach(System.out::println);
    
    // A method reference with 'new' after the double colon refers to a constructor.
    ProductFactory factory = Product::new;
    Product blueberries = factory.create(Category.FOOD, "Blueberries", new BigDecimal("6.95"));
    System.out.println(blueberries);
  }
}