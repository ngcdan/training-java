package training.java.object.orented.domain;

public class BusinessCustomer extends Customer {
  private BusinessSize size;
  
  public enum BusinessSize { SMALL, MEDIUM, LARGE }
  
  public BusinessCustomer(String name, BusinessSize size) {
    super(name);
    this.size = size;
  }
  
  @Override
  public int calculateDiscount() {
    return switch (size) {
      case SMALL -> 5;
      case MEDIUM -> 10;
      case LARGE -> 20;
    };
  }
}
