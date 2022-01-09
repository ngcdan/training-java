package training.java.core.settings.currency;

import static java.util.Map.entry;

import java.util.Map;

public class ExchangeRateDB {

  private Map<String, ExchangeRate> exchangeRate;

  public ExchangeRateDB() {
    this.exchangeRate = Map
      .ofEntries(entry("usd", new ExchangeRate("usd", 1, 1)), entry("vnd", new ExchangeRate("vnd", 22896, 22934.2)),
        entry("eur", new ExchangeRate("eur", 0.8518, 0.865)), entry("cyp", new ExchangeRate("cyp", 0.504, 0.507)),
        entry("hkd", new ExchangeRate("hkd", 7.781, 7.881)), entry("sgd", new ExchangeRate("sgd", 1.3573, 1.3673)),
        entry("aud", new ExchangeRate("aud", 1.3579, 1.3679)), entry("chf", new ExchangeRate("chf", 0.926, 0.946)),
        entry("myr", new ExchangeRate("myr", 4.179, 4.189)), entry("sek", new ExchangeRate("sek", 8.724, 8.734)),
        entry("znd", new ExchangeRate("znd", 1.434, 1.444)), entry("jpy", new ExchangeRate("jpy", 110.39, 110.79)),
        entry("gbp", new ExchangeRate("gbp", 0.7211, 0.7511)), entry("dkk", new ExchangeRate("dkk", 6.411, 6.441)),
        entry("cny", new ExchangeRate("cny", 6.477, 6.677)), entry("cad", new ExchangeRate("cad", 1.251, 1.271)),
        entry("khr", new ExchangeRate("khr", 4083.45, 4084.45)),
        entry("idr", new ExchangeRate("idr", 14238.01, 14239.01)),
        entry("inr", new ExchangeRate("inr", 74.289, 74.789)),
        entry("krw", new ExchangeRate("krw", 1163.283, 1263.283)));
  }

  public ExchangeRate getExchangeRate(String currency) {
    return this.exchangeRate.get(currency);
  }

  public void add(ExchangeRate exchangeRate) {
    this.exchangeRate.put(exchangeRate.getCurrency(), exchangeRate);
  }
}