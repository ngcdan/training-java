package training.java.object.orented.design;

public enum Currency {
  USD, EURO;
  public double conversionRateTo( Currency target) {
    return 1.0;
  }
}
