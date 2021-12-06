package training.java.object.orented.domain;

public class NonprofitCustomer extends Customer {

  public NonprofitCustomer(String name) { super(name);}

  @Override
  public int calculateDiscount() {
    return 15;
  }
}