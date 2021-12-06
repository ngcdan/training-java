package training.java.object.orented.domain;

import lombok.Getter;

@Getter
public class Order {

  private Customer customer;
  private ShoppingCart card;
  private Payment payment;

  public Order(Customer customer, ShoppingCart card, Payment payment) {
    this.customer = customer;
    this.card = card;
    this.payment = payment;
  }

  @Override
  public String toString() {
    return "Order{" + "\ncustomer=" + customer + ", \ncard=" + card + ", \npayment=" + payment + '}';
  }
}