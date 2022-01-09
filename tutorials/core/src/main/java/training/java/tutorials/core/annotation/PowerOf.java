package training.java.tutorials.core.annotation;

@CommandKeyword("power")
public class PowerOf {

  public double calculate(double leftVal, double rightVal) {
    return Math.pow(leftVal, rightVal);
  }
}