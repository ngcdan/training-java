package training.java.tutorials.core.streams;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Product {

  private Category category;
  private String name;
  private BigDecimal price;

  public Product(Category category, String name, BigDecimal price) {
    this.category = category;
    this.name = name;
    this.price = price;
  }

  //%[argument_index$][flags][width][.precision]conversion
  @Override
  public String toString() {
    return String.format("%-10s %-16s $ %6.2f", category, name, price);
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return category == product.category && name.equals(product.name) && price.equals(product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, name, price);
  }
}