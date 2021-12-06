package training.java.object.orented.design1;

public class Money {

  private double value;
  private Currency currency;

  public Money(double value, Currency currency) {
    this.value = value;
    this.currency = currency;
  }

  public double normalized() {
    return currency == Currency.USD ? value : value * currency.conversionRateTo(Currency.USD);
  }

  public boolean isGreaterThan(Money op) {
    return (normalized() > op.normalized());
  }
}