package training.java.core.settings.currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class ExchangeRateConverter {

  private ExchangeRateDB exchangeRateDB = new ExchangeRateDB();

  @Getter
  private Map<String, ExchangeRate> exchangeRateAvailable = new HashMap<>();

  public ExchangeRateConverter(ExchangeRate... exchangeRates) {
    add(exchangeRates);
  }

  public ExchangeRateConverter(List<ExchangeRate> exchangeRates) {
    if(exchangeRates != null && exchangeRates.size() > 0) {
      for(ExchangeRate sel : exchangeRates) {
        exchangeRateAvailable.put(sel.getCurrency().toLowerCase(), sel);
      }
    }
  }

  public ExchangeRateConverter add(ExchangeRate... exchangeRates) {
    for(ExchangeRate sel : exchangeRates) {
      exchangeRateAvailable.put(sel.getCurrency().toLowerCase(), sel);
    }
    return this;
  }

  public double convertBid(double amount, String fromCurrency, String toCurrency) {
    if(fromCurrency == null) {
      throw new IllegalArgumentException("Currency must be not null");
    }
    if(toCurrency == null) {
      throw new IllegalArgumentException("Currency must be not null");
    }
    String fromCurrencyLower = fromCurrency.toLowerCase();
    String toCurrencyLower = toCurrency.toLowerCase();
    this.ensureExchangeRate(fromCurrencyLower);
    this.ensureExchangeRate(toCurrencyLower);
    ExchangeRate from = exchangeRateAvailable.get(fromCurrencyLower);
    ExchangeRate to = exchangeRateAvailable.get(toCurrencyLower);
    return from.convertBid(amount, to);
  }

  public double convertAsk(double amount, String fromCurrency, String toCurrency) {
    if(fromCurrency == null) {
      throw new IllegalArgumentException("Currency must be not null");
    }
    if(toCurrency == null) {
      throw new IllegalArgumentException("Currency must be not null");
    }
    String fromCurrencyLower = fromCurrency.toLowerCase();
    String toCurrencyLower = toCurrency.toLowerCase();
    this.ensureExchangeRate(fromCurrencyLower);
    this.ensureExchangeRate(toCurrencyLower);
    ExchangeRate from = exchangeRateAvailable.get(fromCurrencyLower);
    ExchangeRate to = exchangeRateAvailable.get(toCurrencyLower);
    return from.convertAsk(amount, to);
  }

  private void ensureExchangeRate(String currency) {
    if(!this.exchangeRateAvailable.containsKey(currency)) {
      ExchangeRate exchangeRate = this.exchangeRateDB.getExchangeRate(currency);
      exchangeRateAvailable.put(currency, exchangeRate);
    }
  }
}