package training.java.object.orented.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static training.java.object.orented.domain.Category.*;

public class ExampleData {
  private static final List<Product> PRODUCTS = Arrays.asList(
    new Product(FOOD, "Oranges",1.65),
    new Product(FOOD, "Gouda cheese",6.79),
    new Product(UTENSILS, "Plates",12.95),
    new Product(CLEANING, "Detergent",3.79),
    new Product(FOOD, "Soft drink",1.99),
    new Product(OFFICE, "Pencils",5.79),
    new Product(FOOD, "Rice",2.99),
    new Product(CLEANING, "Scourer",2.29),
    new Product(FOOD, "Milk",1.39),
    new Product(OFFICE, "Notebook",4.99),
    new Product(FOOD, "Tea",4.29),
    new Product(FOOD, "Tomato sauce",1.39),
    new Product(FOOD, "Peanut butter",2.39),
    new Product(FOOD, "Red bell pepper",0.99),
    new Product(UTENSILS, "Spoons",14.95),
    new Product(OFFICE, "Adhesive tape",5.39),
    new Product(CLEANING, "Dish brush",3.49),
    new Product(UTENSILS, "Knives",9.95),
    new Product(FOOD, "Brown bread",3.99),
    new Product(FOOD, "Potatoes",1.59),
    new Product(CLEANING, "Dishcloth",2.59),
    new Product(FOOD, "Apples",1.29),
    new Product(OFFICE, "Ballpoint pens",6.79),
    new Product(FOOD, "Spaghetti",2.79),
    new Product(UTENSILS, "Forks",14.95),
    new Product(UTENSILS, "Paper towel",3.69),
    new Product(FOOD, "Coffee",7.49),
    new Product(OFFICE, "Highlighter",2.29),
    new Product(FOOD, "Orange juice",3.49),
    new Product(FOOD, "Spring water",0.99));
  
  public static List<Product> getProducts() {
    return new ArrayList<>(PRODUCTS);
  }
}
