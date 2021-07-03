package training.java.design.pattern.decorator;

public class DressingDecorator extends SandwichDecorator {
  
  public DressingDecorator(Sandwich customSandwich) {
    super(customSandwich);
  }
  
  @Override
  public String make() {
    return customSandwich.make() + addDressing();
  }
  
  public String addDressing() {
    return " + mustard";
  }
}
