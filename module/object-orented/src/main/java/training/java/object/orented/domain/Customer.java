package training.java.object.orented.domain;

import java.util.Optional;

public class Customer {
  private final String name;
  private CreditCard creditCard;
  
  public Customer(String name, long creditNumber) {
    this.name = name;
    this.creditCard = new CreditCard(creditNumber);
  }
  
  public Optional<Order> checkout(ShoppingCard card) {
    Optional<Payment> payment = creditCard.mkPayment(card.getTotalCost());
    return payment.map(value -> new Order(this, card, value));
  }
  
  @Override
  public String toString() {
    return "Customer{" +
      "\nname='" + name + '\'' +
      ", \ncreditCard=" + creditCard +
      '}';
  }
}
