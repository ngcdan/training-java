package training.java.object.orented.branching;

import java.math.BigDecimal;

public class Account {

  private boolean isVerified;
  private boolean isClosed;
  private BigDecimal balance;
  private boolean isFrozen;

  public Account() {
    this.balance = BigDecimal.ZERO;
  }

  public void holderVerified() {
    this.isVerified = true;
  }

  public void closeAccount() {
    this.isClosed = true;
  }

  public void freezeAccount() {
    if(this.isClosed) {
      return; // account must not be close;
    }
    if(!this.isVerified) {
      return; // Account must be verified first
    }
    this.isFrozen = true;
  }

  public void deposit(BigDecimal amount) {
    if(this.isClosed) {
      return;
    }
    balance = balance.add(amount);
  }

  public void withdraw(BigDecimal amount) {
    if(!this.isVerified) {
      return; // or do something more meaningful
    }
    if(this.isClosed) {
      return;
    }
    balance = balance.subtract(amount);
  }
}