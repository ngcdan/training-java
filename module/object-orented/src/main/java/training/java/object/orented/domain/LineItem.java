package training.java.object.orented.domain;

import java.math.BigDecimal;

public class LineItem {
  private Product product;
  private int quantity;
  
  public LineItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }
  
  public BigDecimal getPrice() {
    return product.getPrice().multiply(BigDecimal.valueOf(quantity));
  }
  
  @Override
  public String toString() {
    return "\nLineItem{" +
      "product=" + product +
      ", quantity=" + quantity +
      '}';
  }
}
