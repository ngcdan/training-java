package training.java.object.orented.domain;

import java.util.UUID;

public class Payment {
  private final PaymentMethod paymentMethod;
  private final double value;
  private final UUID transactionID;
  
  public Payment(PaymentMethod paymentMethod, double value, UUID transactionID) {
    this.paymentMethod = paymentMethod;
    this.value = value;
    this.transactionID = transactionID;
  }
  
  @Override
  public String toString() {
    return "Payment{" +
      "paymentMethod=" + paymentMethod +
      ", value=" + value +
      ", transactionId=" + transactionID +
      '}';
  }
}
