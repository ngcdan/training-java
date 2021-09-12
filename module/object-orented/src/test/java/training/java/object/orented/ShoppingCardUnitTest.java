package training.java.object.orented;

import org.junit.jupiter.api.Test;
import training.java.object.orented.domain.*;

import java.util.Optional;

public class ShoppingCardUnitTest {
  
  @Test
  public void shoppingCardUnitTest() {
    ShoppingCard shoppingCard = new ShoppingCard();
    Product toothbrush = Catalogue.getProduct(Category.CLEANING, "Detergent");
    Product toothpaste = Catalogue.getProduct(Category.CLEANING, "Scourer");
    shoppingCard.addLineItem(new LineItem(toothbrush, 2));
    shoppingCard.addLineItem(new LineItem(toothpaste, 1));
    Customer dan = new Customer("dan", 123456);
    Optional<Order> order = dan.checkout(shoppingCard);
    System.out.println(shoppingCard.getTotalCost());
    System.out.println(order);
  }
}
