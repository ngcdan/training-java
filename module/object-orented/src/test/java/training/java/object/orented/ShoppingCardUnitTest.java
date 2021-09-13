package training.java.object.orented;

import org.junit.jupiter.api.Test;
import training.java.object.orented.domain.*;

import java.util.Iterator;
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
    
    order.ifPresent(ShoppingCardUnitTest::fulfil);
    System.out.println(order);
  }
  
  public static void fulfil(Order order) {
    ShoppingCard card = order.getCard();
    boolean shippingUnfinished;
    
    do {
      shippingUnfinished = false;
      for(Iterator<LineItem> it = card.getLineItems().iterator(); it.hasNext(); ) {
        LineItem li = it.next();
        if(Math.random() > 0.7) {
          shippingUnfinished = true;
          System.out.println(li.getProduct() + " is out of stock!");
        } else {
          it.remove();
        }
      }
      
    } while (shippingUnfinished);
  }
}
