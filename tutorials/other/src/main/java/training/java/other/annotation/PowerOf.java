package training.java.other.annotation;

@CommandKeyword("power")
public class PowerOf {

  public double calculate(double leftVal, double rightVal) {
    return Math.pow(leftVal, rightVal);
  }
}