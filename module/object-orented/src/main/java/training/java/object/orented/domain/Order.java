package training.java.object.orented.domain;

public class Order {
  private Customer customer;
  private ShoppingCard card;
  private Payment payment;
  
  public Order(Customer customer, ShoppingCard card, Payment payment) {
    this.customer = customer;
    this.card = card;
    this.payment = payment;
  }
  
}
