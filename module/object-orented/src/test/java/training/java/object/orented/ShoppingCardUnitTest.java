package training.java.object.orented;

import org.junit.jupiter.api.Test;
import training.java.object.orented.domain.*;

public class ShoppingCardUnitTest {
  
  @Test
  public void shoppingCardUnitTest() {
    ShoppingCard shoppingCard = new ShoppingCard();
    Product toothbrush = Catalogue.getProduct(Category.CLEANING, "Detergent");
    Product toothpaste = Catalogue.getProduct(Category.CLEANING, "Scourer");
    shoppingCard.addLineItem(new LineItem(toothbrush, 2));
    shoppingCard.addLineItem(new LineItem(toothpaste, 1));
    System.out.println(shoppingCard);
    System.out.println(shoppingCard.getTotalCost());
  }
}
