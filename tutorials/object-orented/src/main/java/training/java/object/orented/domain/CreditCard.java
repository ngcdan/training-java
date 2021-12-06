package training.java.object.orented.domain;

import java.util.Optional;
import java.util.UUID;

public class CreditCard implements PaymentMethod {

  private final long cardNumber;

  public CreditCard(long cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public Optional<Payment> mkPayment(double value) {
    if(Math.random() > 0.3) {
      return Optional.of(new Payment(this, value, UUID.randomUUID()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public String toString() {
    return "CreditCard{" + "cardNumber=" + cardNumber + '}';
  }
}