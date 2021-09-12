package training.java.object.orented.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
  private List<ProductDep> products = new ArrayList<>();
  
  public void addProduct(ProductDep product) {
    this.products.add(product);
  }
  
  public int getTotalCost() {
    return products.stream().mapToInt(ProductDep::getPrice).sum();
  }
  
  @Override
  public String toString() {
    return "ShoppingCard{" +
      "products=" + products +
      '}';
  }
}
