package training.java.object.orented;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import training.java.object.orented.domain.BankAccount;
import training.java.object.orented.domain.BusinessCustomer;
import training.java.object.orented.domain.BusinessCustomer.BusinessSize;
import training.java.object.orented.domain.Catalogue;
import training.java.object.orented.domain.Category;
import training.java.object.orented.domain.CreditCard;
import training.java.object.orented.domain.Customer;
import training.java.object.orented.domain.EftAccount;
import training.java.object.orented.domain.HighValuePayment;
import training.java.object.orented.domain.LineItem;
import training.java.object.orented.domain.NonprofitCustomer;
import training.java.object.orented.domain.Order;
import training.java.object.orented.domain.Payment;
import training.java.object.orented.domain.PaymentIntf;
import training.java.object.orented.domain.PaymentMethod;
import training.java.object.orented.domain.Product;
import training.java.object.orented.domain.ReversiblePayment;
import training.java.object.orented.domain.ShoppingCart;

public class ShoppingCardUnitTest {

  @Test
  public void shoppingCardUnitTest() {
    Customer dan = new Customer("dan");
    PaymentMethod eftAccount = new EftAccount("jane@janedoe.com");
    dan.addPaymentMethod("Dan's credit card", new CreditCard(11111));
    dan.addPaymentMethod("Dan's bank account", new BankAccount(12121212, 1234));
    dan.addPaymentMethod("jane@janedoe.com", eftAccount);

    ShoppingCart cart = new ShoppingCart();
    Product toothbrush = Catalogue.getProduct(Category.CLEANING, "Detergent");
    Product toothpaste = Catalogue.getProduct(Category.CLEANING, "Scourer");
    cart.addLineItem(new LineItem(toothbrush, 2));
    cart.addLineItem(new LineItem(toothpaste, 1));
    Optional<Order> order = dan.checkout(cart, "Dan's credit card");
    System.out.println(order);
  }

  @Test
  public void shoppingCard1UnitTest() {
    PaymentMethod eftAccount = new EftAccount("jane@janedoe.com");

    // create a new payment using that EFT account 
    PaymentIntf payment = new Payment(eftAccount, 100, UUID.randomUUID());

    // create a payment with the same characteristics but with added verification
    PaymentIntf highValuePayment = new HighValuePayment(payment);

    // make a reversible payment from either one 
    ReversiblePayment reversiblePayment = new ReversiblePayment(payment);
    System.out.println(reversiblePayment);
    ReversiblePayment reversibleHighValuePayment = new ReversiblePayment(highValuePayment);
    System.out.println(reversibleHighValuePayment);
  }

  @Test
  public void shoppingCard2UnitTest() {
    Customer customer1 = new Customer("customer1");
    Customer customer2 = new BusinessCustomer("customer2", BusinessSize.LARGE);
    Customer customer3 = new BusinessCustomer("customer3", BusinessSize.LARGE);
    Customer customer4 = new NonprofitCustomer("customer4");

    // count how many customers are on each discount rate
    List<Customer> customers = List.of(customer1, customer2, customer3, customer4);
    Map<Integer, Long> discountMap = customers.stream()
      .collect(Collectors.groupingBy(Customer::calculateDiscount, Collectors.counting()));
    System.out.println(discountMap);

    // alternative way to count
    discountMap = new HashMap<>();
    for(Customer customer : customers) {
      discountMap.merge(customer.calculateDiscount(), 1L, Long::sum);
    }
    System.out.println(discountMap);
  }

  public static void fulfil(Order order) {
    ShoppingCart card = order.getCard();
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

    } while(shippingUnfinished);
  }
}