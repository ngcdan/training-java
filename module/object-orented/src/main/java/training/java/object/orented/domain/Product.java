package training.java.object.orented.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Product {
  private Category category;
  private String name;
  private double price;
  private double discount;
  private int weight;
  
  public Product(Category category, String name, double price) {
    this.category = category;
    this.name = name;
    this.price = price;
  }
  
  public double getPrice() {
    double shippingCost = category.getShippingCost(weight);
    return price * (100 - discount) / 100.0;
  }
  
  public Product withDiscount(int discount) {
    this.discount = discount;
    return this;
  }
  
  //%[argument_index$][flags][width][.precision]conversion
  @Override
  public String toString() {
    return String.format("%-10s %-16s $ %6.2f", category, name, price);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return category == product.category && name.equals(product.name) && price == product.price;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(category, name, price);
  }
}
