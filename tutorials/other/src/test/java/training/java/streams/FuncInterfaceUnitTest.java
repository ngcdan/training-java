package training.java.streams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class FuncInterfaceUnitTest {

  static final List<Product> products = ExampleData.getProducts();

  // Go through a list of products, and return the first product for which the predicate returns true.
  static Optional<Product> findProduct(List<Product> products, Predicate<Product> predicate) {
    for(Product product : products) {
      if(predicate.test(product)) {
        return Optional.of(product);
      }
    }
    return Optional.empty();
  }

  /**
   * @param products List of products to filter.
   * @param f        Determines which products should be in the result.
   * @return A filtered list of products.
   */

  public List<Product> filterProducts(List<Product> products, Predicate<Product> f) {
    List<Product> result = new ArrayList<>();
    for(Product product : products) {
      if(f.test(product)) {
        result.add(product);
      }
    }
    return result;
  }

  @Test
  public void findProductsUnitTest() {
    String name = "Spaghetti";
    findProduct(products, product -> product.getName().equals(name)).map(Product::getPrice)
      .ifPresentOrElse(price -> System.out.printf("The price of %s is $ %.2f%n", name, price),
        () -> System.out.printf("%s is not available%n", name));
  }

  @Test
  public void productByCategoryUnitTest() {
    Map<Category, List<Product>> productsByCategory = new HashMap<>();

    for(Product product : products) {
      Category category = product.getCategory();
      // Check if the map already has a List for this category; if not, add one.
      if(!productsByCategory.containsKey(category)) {
        productsByCategory.put(category, new ArrayList<>());
      }

      productsByCategory.get(category).add(product);
    }

    for(Product product : products) {
      Category category = product.getCategory();

      // computeIfAbsent() gets the existing List for the category, or calls the given
      // Function<Category, List<Product>> to create the List if there is no entry in the Map for the category.
      productsByCategory.computeIfAbsent(category, c -> new ArrayList<>()).add(product);
    }

    productsByCategory.forEach((category, ps) -> {
      System.out.println("Category: " + category);
      ps.forEach(product -> System.out.println("- " + product.getName()));
    });
  }

  @Test
  public void composeToFunctionForMappingProductUnitTest() {
    String name = "Spaghetti";

    Function<Product, BigDecimal> productToPrice = Product::getPrice;
    Function<BigDecimal, String> priceToMessage = price -> String.format("The price of %s is $ %.2f%n", name, price);

    Function<Product, String> productToMessage = productToPrice.andThen(priceToMessage);
    //Function<Product, String> productToMessage = priceToMessage.compose(productToPrice);

    findProduct(products, product -> product.getName().equals(name)).map(productToMessage)
      .ifPresentOrElse(System.out::println, () -> System.out.printf("%s is not available%n", name));
  }

  @Test
  public void composeTwoFilterProductUnitTest() {
    BigDecimal priceLimit = new BigDecimal("2.00");

    // Two simple predicates that can be combined using the functional composition methods in interface Predicate.
    Predicate<Product> isFood = product -> product.getCategory() == Category.FOOD;
    Predicate<Product> isCheap = product -> product.getPrice().compareTo(priceLimit) < 0;

    findProduct(products, isFood.and(isCheap)) // Combining the predicates with and(...)
      .map(product -> String.format("%s for $ %.2f", product.getName(), product.getPrice()))
      .ifPresentOrElse(System.out::println, () -> System.out.println("There are no cheap food products"));
  }
}