package training.java.object.orented.domain;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {
  private static Map<String, Product> productMap = new HashMap<>();
  
  public static Product getProduct(String productName) {
    return productMap.get(productName);
  }
  
  static  {
    productMap.put("Toothbrush", new Product("Toothbrush", 20000));
    productMap.put("Toothpaste", new Product("Toothpaste", 21000));
  }
}
