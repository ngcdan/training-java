package training.java.object.orented.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
  private List<LineItem> lineItems = new ArrayList<>();
  
  public void addLineItem(LineItem lineItem) {
    this.lineItems.add(lineItem);
  }
  
  public BigDecimal getTotalCost() {
    return lineItems.stream()
      .reduce(BigDecimal.ZERO, (result, lineItem) -> result.add(lineItem.getPrice()), BigDecimal::add);
  }
}
