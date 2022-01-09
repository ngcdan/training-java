package training.java.design.pattern.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Manager implements Employee {

  private String name;
  private String position;

  @Override
  public void showEmployeeDetails() {
    System.out.println(name + " - " + position);
  }
}