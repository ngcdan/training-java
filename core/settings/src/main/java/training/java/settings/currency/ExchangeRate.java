package training.java.settings.currency;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRate {

  private String currency;
  private double bid;
  private double ask;
  private String[] alias;

  public ExchangeRate(String currency, double bid, double ask) {
    this.currency = currency;
    this.bid = bid;
    this.ask = ask;
  }

  public double convertBid(double amount, ExchangeRate other) {
    return (other.bid / bid) * amount;
  }

  public double convertAsk(double amount, ExchangeRate other) {
    return (other.ask / ask) * amount;
  }

  public void sync(ExchangeRate other) {
    if(currency.equals(other.getCurrency())) {
      throw new IllegalArgumentException();
    }
    bid = other.bid;
    ask = other.ask;
  }

  public ExchangeRate clone() {
    return new ExchangeRate(currency, bid, ask);
  }
}