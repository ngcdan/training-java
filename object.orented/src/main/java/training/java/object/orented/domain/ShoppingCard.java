package training.java.object.orented.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
  private List<Product> products = new ArrayList<>();
  
  public void addProduct(Product product) {
    this.products.add(product);
  }
  
  public int getTotalCost() {
    return products.stream().mapToInt(Product::getPrice).sum();
  }
  
  @Override
  public String toString() {
    return "ShoppingCard{" +
      "products=" + products +
      '}';
  }
}
