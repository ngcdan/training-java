package training.java.tutorials.core.annotation;

@CommandKeyword(value = "multiply")
public class Multiplier implements MathProcessing {

  @Override
  public double doCalculation(double leftVal, double rightVal) {
    return leftVal * rightVal;
  }
}