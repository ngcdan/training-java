package training.java.tutorials.core.annotation;

@CommandKeyword(value = "divide")
public class Divider implements MathProcessing {

  @Override
  public double doCalculation(double leftVal, double rightVal) {
    return leftVal / rightVal;
  }
}