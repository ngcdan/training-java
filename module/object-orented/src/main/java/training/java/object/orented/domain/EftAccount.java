package training.java.object.orented.domain;

import java.util.Optional;

public class EftAccount implements PaymentMethod {
  private final String emailAddress;
  
  public EftAccount(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  
  @Override
  public Optional<Payment> mkPayment(double amount) {
    return Optional.empty();
  }
  
  @Override
  public String toString() {
    return "EftAccount{" +
      "e-mail address: " + emailAddress +
      '}';
  }
  
}
