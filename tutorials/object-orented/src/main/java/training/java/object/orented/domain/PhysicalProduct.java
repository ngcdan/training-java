package training.java.object.orented.domain;

public class PhysicalProduct extends Product {

  private int weight;

  public PhysicalProduct(Category category, String name, double price) {
    super(category, name, price);
  }

  @Override
  public int calculateShippingCost() {
    return weight * Catalogue.SHIPPING_RATE;
  }
}