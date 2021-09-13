package training.java.object.orented.domain;

import java.util.UUID;

public class Payment {
  private CreditCard creditCard;
  private double value;
  private UUID randomUUID;
  
  public Payment(CreditCard creditCard, double value, UUID randomUUID) {
    this.creditCard = creditCard;
    this.value = value;
    this.randomUUID = randomUUID;
  }
  
  @Override
  public String toString() {
    return "Payment{" +
      "creditCard=" + creditCard +
      ", value=" + value +
      ", randomUUID=" + randomUUID +
      '}';
  }
}
