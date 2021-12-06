package training.java.object.orented.domain;

public interface PaymentIntf {

  void execute();

  void setValue(double value);

  double getValue();
}