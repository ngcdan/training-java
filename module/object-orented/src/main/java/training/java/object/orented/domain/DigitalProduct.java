package training.java.object.orented.domain;

public class DigitalProduct extends Product {
  
  public DigitalProduct(Category category, String name, double price) {
    super(category, name, price);
  }
  
  @Override
  public int calculateShippingCost() {
    return 0;
  }
}
