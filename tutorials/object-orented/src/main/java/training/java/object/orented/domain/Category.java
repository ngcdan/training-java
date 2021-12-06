package training.java.object.orented.domain;

public enum Category {
  FOOD(Catalogue.SHIPPING_RATE), UTENSILS(0), CLEANING(0), OFFICE(Catalogue.SHIPPING_RATE);

  private double shippingRate;

  Category(double shippingRate) {
    this.shippingRate = shippingRate;
  }

  public double getShippingCost(int weight) {
    return shippingRate * weight;
  }
}