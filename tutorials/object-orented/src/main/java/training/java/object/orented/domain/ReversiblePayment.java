package training.java.object.orented.domain;

public class ReversiblePayment implements PaymentIntf {
  private final PaymentIntf payment;
  
  public ReversiblePayment(PaymentIntf payment) {
    this.payment = payment;
  }
  
  @Override
  public void execute() {
    payment.execute();
  }
  
  @Override
  public double getValue() {
    return payment.getValue();
  }
  
  @Override
  public void setValue(double value) {
    payment.setValue(value);
  }
  
  public void reverse() {
    setValue(-getValue());
    execute();
  }
  
  @Override
  public String toString() {
    return "ReversiblePayment{" +
      "payment=" + payment +
      '}';
  }
}
