package training.java.object.orented.domain;

import lombok.Getter;

@Getter
public class LineItem {
  private Product product;
  private int quantity;
  
  public LineItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }
  
  public double getPrice() {
    return product.getPrice() * quantity;
  }
  
  @Override
  public String toString() {
    return "\nLineItem{" +
      "product=" + product +
      ", quantity=" + quantity +
      '}';
  }
}
