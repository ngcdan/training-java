package training.java.object.orented.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static training.java.object.orented.domain.Category.*;

public class ExampleData {
  private static final List<Product> PRODUCTS = Arrays.asList(
    new PhysicalProduct(FOOD, "Oranges",1.65).withDiscount(10),
    new PhysicalProduct(FOOD, "Gouda cheese",6.79).withDiscount(10),
    new PhysicalProduct(UTENSILS, "Plates",12.95).withDiscount(20),
    new PhysicalProduct(CLEANING, "Detergent",3.79),
    new PhysicalProduct(FOOD, "Soft drink",1.99),
    new PhysicalProduct(OFFICE, "Pencils",5.79),
    new PhysicalProduct(FOOD, "Rice",2.99),
    new PhysicalProduct(CLEANING, "Scourer",2.29),
    new PhysicalProduct(FOOD, "Milk",1.39),
    new PhysicalProduct(OFFICE, "Notebook",4.99),
    new PhysicalProduct(FOOD, "Tea",4.29),
    new PhysicalProduct(FOOD, "Tomato sauce",1.39),
    new PhysicalProduct(FOOD, "Peanut butter",2.39),
    new PhysicalProduct(FOOD, "Red bell pepper",0.99),
    new PhysicalProduct(UTENSILS, "Spoons",14.95),
    new PhysicalProduct(OFFICE, "Adhesive tape",5.39),
    new PhysicalProduct(CLEANING, "Dish brush",3.49),
    new PhysicalProduct(UTENSILS, "Knives",9.95),
    new PhysicalProduct(FOOD, "Brown bread",3.99),
    new PhysicalProduct(FOOD, "Potatoes",1.59),
    new PhysicalProduct(CLEANING, "Dishcloth",2.59),
    new PhysicalProduct(FOOD, "Apples",1.29),
    new PhysicalProduct(OFFICE, "Ballpoint pens",6.79),
    new PhysicalProduct(FOOD, "Spaghetti",2.79),
    new PhysicalProduct(UTENSILS, "Forks",14.95),
    new PhysicalProduct(UTENSILS, "Paper towel",3.69),
    new PhysicalProduct(FOOD, "Coffee",7.49),
    new PhysicalProduct(OFFICE, "Highlighter",2.29),
    new PhysicalProduct(FOOD, "Orange juice",3.49),
    new PhysicalProduct(FOOD, "Spring water",0.99));
  
  public static List<Product> getProducts() {
    return new ArrayList<>(PRODUCTS);
  }
}
