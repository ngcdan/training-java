package training.java.object.orented;

import org.junit.jupiter.api.Test;
import training.java.object.orented.domain.Catalogue;
import training.java.object.orented.domain.Category;
import training.java.object.orented.domain.Product;
import training.java.object.orented.domain.ShoppingCard;

public class ShoppingCardUnitTest {
  
  @Test
  public void shoppingCardUnitTest() {
    ShoppingCard shoppingCard = new ShoppingCard();
    Product toothbrush = Catalogue.getProduct(Category.CLEANING, "Detergent");
    Product toothpaste = Catalogue.getProduct(Category.CLEANING, "Scourer");
    shoppingCard.addProduct(toothbrush);
    shoppingCard.addProduct(toothpaste);
    System.out.println(shoppingCard);
    System.out.println(shoppingCard.getTotalCost());
  }
}
