package training.java.design.pattern;

import org.junit.jupiter.api.Test;
import training.java.design.pattern.decorator.DressingDecorator;
import training.java.design.pattern.decorator.MeatDecorator;
import training.java.design.pattern.decorator.Sandwich;
import training.java.design.pattern.decorator.SimpleSandwich;

public class DecoratorUnitTest {
  
  @Test
  public void sandwichUnitTest() throws Exception {
    Sandwich sandwich = new DressingDecorator(new MeatDecorator(new SimpleSandwich()));
    System.out.println(sandwich.make());
    
  }
}
