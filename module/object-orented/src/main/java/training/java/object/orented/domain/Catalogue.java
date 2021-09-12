package training.java.object.orented.domain;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {
  private static Map<String, ProductDep> productMap = new HashMap<>();
  
  public static ProductDep getProduct(String productName) {
    return productMap.get(productName);
  }
  
  static  {
    productMap.put("Toothbrush", new ProductDep("Toothbrush", 20000));
    productMap.put("Toothpaste", new ProductDep("Toothpaste", 21000));
  }
  
}
