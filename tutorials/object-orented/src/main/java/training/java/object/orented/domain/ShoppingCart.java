package training.java.object.orented.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ShoppingCart {

  private final List<LineItem> lineItems = new ArrayList<>();

  public void addLineItem(LineItem lineItem) {
    this.lineItems.add(lineItem);
  }

  public double getTotalCost() {
    return lineItems.stream().reduce(0.0, (result, lineItem) -> result + lineItem.getPrice(), Double::sum);
  }

  public List<LineItem> getLineItems() {
    return lineItems.stream().map(LineItem::new).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "ShoppingCart{" + "lineItems=" + lineItems + '}';
  }
}