package training.java.object.orented.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
  private List<Product> products = new ArrayList<>();
  
  public void addProduct(Product product) {
    this.products.add(product);
  }
  
  public BigDecimal getTotalCost() {
    return products.stream()
      .reduce(BigDecimal.ZERO, (result, product) -> result.add(product.getPrice()), BigDecimal::add);
  }
  
  @Override
  public String toString() {
    return "ShoppingCard{" +
      "products=" + products +
      '}';
  }
}
