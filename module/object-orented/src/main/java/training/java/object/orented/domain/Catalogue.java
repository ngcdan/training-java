package training.java.object.orented.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Catalogue {
  public static final int SHIPPING_RATE = 5;
  
  private static Map<Category, List<Product>> productMap =
    ExampleData.getProducts().stream().collect(Collectors.groupingBy(Product::getCategory));
  
  public static Product getProduct(Category category, String productName) {
    Optional<Product> optionalProduct =
      productMap.get(category)
        .stream()
        .filter(product -> product.getName().equals(productName))
        .findFirst();
    
    if(optionalProduct.isPresent()) {
      return optionalProduct.get();
    };
    
    return null;
  }
}
