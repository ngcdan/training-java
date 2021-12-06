package training.java.settings.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CurrencyConverterUnitTest {

  @Test
  @Tag("unit")
  public void test() {
    ExchangeRate USD = new ExchangeRate("USD", 1, 1);
    ExchangeRate VND = new ExchangeRate("VND", 22947.5, 22934.2);
    ExchangeRate EUR = new ExchangeRate("EUR", 0.83, 0.825);
    ExchangeRate YUAN = new ExchangeRate("¥", 6.4, 6.35);

    ExchangeRateConverter converter = new ExchangeRateConverter(USD, VND, EUR, YUAN);
    Map<String, ExchangeRate> exchangeRateAvailable = converter.getExchangeRateAvailable();
    final int originSize = exchangeRateAvailable.size();

    assertEquals(1, converter.convertBid(22947.5, "VND", "USD"), 0.01);
    assertEquals(22947.5, converter.convertBid(1, "USD", "VND"));
    assertEquals(1, converter.convertBid(27804.27, "VND", "EUR"), 0.01);
    assertEquals(1, converter.convertBid(1, "JPY", "JPY"));

    assertEquals(originSize + 1, converter.getExchangeRateAvailable().size());
  }
}