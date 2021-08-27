package training.java.object.orented;

import org.junit.jupiter.api.Test;
import training.java.object.orented.design1.Currency;
import training.java.object.orented.design1.Money;

public class DesignUnitTest {
  public static void dispenseFunds(Money money) {/*...*/};
  
  @Test
  public void MoneyUnitTest() {
    Money balance = new Money(1.0, Currency.EURO);
    Money request = new Money(1.0, Currency.USD);
    
    if(balance.isGreaterThan(request)) {
      dispenseFunds(request);
    }
  }
}

