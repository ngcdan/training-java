package training.java.object.orented.domain;

import java.util.UUID;

public class Payment implements PaymentIntf {
  private final PaymentMethod paymentMethod;
  private final UUID transactionId;
  private double value;
  
  public Payment(PaymentMethod paymentMethod, double value, UUID transactionId) {
    this.paymentMethod = paymentMethod;
    this.value = value;
    this.transactionId = transactionId;
  }
  
  @Override
  public void execute() {}
  
  @Override
  public double getValue() {
    return value;
  }
  
  @Override
  public void setValue(double value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return "Payment{" +
      "paymentMethod=" + paymentMethod +
      ", value=" + value +
      ", transactionId=" + transactionId +
      '}';
  }
}
