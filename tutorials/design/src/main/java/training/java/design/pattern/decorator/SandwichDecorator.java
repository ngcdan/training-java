package training.java.design.pattern.decorator;

public abstract class SandwichDecorator implements Sandwich {

  protected Sandwich customSandwich;

  public SandwichDecorator(Sandwich customSandwich) {
    this.customSandwich = customSandwich;
  }

  public String make() {
    return this.customSandwich.make();
  }

}