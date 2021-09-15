package training.java.object.orented.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ShoppingCart {
  private List<LineItem> lineItems = new ArrayList<>();
  
  public void addLineItem(LineItem lineItem) {
    this.lineItems.add(lineItem);
  }
  
  public double getTotalCost() {
    return lineItems.stream()
      .reduce(0.0, (result, lineItem) -> result + lineItem.getPrice(), Double::sum);
  }
  
  @Override
  public String toString() {
    return "ShoppingCard{" +
      "\nlineItems=" + lineItems +
      '}';
  }
}
