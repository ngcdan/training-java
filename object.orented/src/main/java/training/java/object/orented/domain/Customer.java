package training.java.object.orented.domain;

public class Customer {
  private final String name;
  private CreditCard creditCard;
  
  
  public Customer(String name, long creditNumber) {
    this.name = name;
    this.creditCard = new CreditCard(creditNumber);
  }
}
