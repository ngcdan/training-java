package training.java.object.orented.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LineItem {

  private Product product;
  private int quantity;

  public LineItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public LineItem(LineItem li) {
    this(li.product, li.quantity);
  }

  public double getPrice() {
    return product.getPrice() * quantity;
  }

  @Override
  public String toString() {
    return "\n\t" + "LineItem{" + "product=" + product + ", quantity=" + quantity + ", price=" + getPrice() + '}';
  }

  public int calculateShippingCost() {
    return product.calculateShippingCost() * quantity;
  }
}