package training.java.object.orented.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Product {
  private Category category;
  private String name;
  private double price;
  private double productDiscount;
  
  public Product(Category category, String name, double price) {
    this.category = category;
    this.name = name;
    this.price = price;
  }
  
  public double getPrice() {
    int shippingCost = calculateShippingCost();
    return Math.round((1 - productDiscount) * price) + shippingCost;
  }
  
  public Product withProductDiscount(int discount) {
    this.productDiscount = discount;
    return this;
  }
  
  public abstract int calculateShippingCost();
  
  @Override
  public String toString() {
    return "Product{" +
      "name='" + name + '\'' +
      ", price=" + price +
      ", discount=" + productDiscount +
      '}';
  }
}
