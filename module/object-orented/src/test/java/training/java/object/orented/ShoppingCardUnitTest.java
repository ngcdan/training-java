package training.java.object.orented;

import org.junit.jupiter.api.Test;
import training.java.object.orented.domain.Catalogue;
import training.java.object.orented.domain.ProductDep;
import training.java.object.orented.domain.ShoppingCard;

public class ShoppingCardUnitTest {
  
  @Test
  public void shoppingCardUnitTest() throws Exception {
    ShoppingCard shoppingCard = new ShoppingCard();
    ProductDep toothbrush = Catalogue.getProduct("Toothbrush");
    ProductDep toothpaste = Catalogue.getProduct("Toothpaste");
    shoppingCard.addProduct(toothbrush);
    shoppingCard.addProduct(toothpaste);
    System.out.println(shoppingCard);
    System.out.println(shoppingCard.getTotalCost());
  }
}
