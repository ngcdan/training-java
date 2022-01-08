package training.java.xlsx.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class Product {
  private String name;
  private List<String> features;
  private double price;
  private String imageFile;
}