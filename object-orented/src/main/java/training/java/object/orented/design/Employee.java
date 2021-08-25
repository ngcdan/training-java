package training.java.object.orented.design;

import java.util.Currency;

public class Employee {
}

interface TimeSheet{}
interface Invoice{};
class Money1 {
  public Money1(double val, Currency currency) {}
}

class Worker {
  private String name;
  public Worker(String name) {
    this.name = name;
  }
  
  public void pay() {/*...*/}
}

class AccountsPayable {
  Worker[] workers;
  
  public void payEveryBody() {
    for(Worker worker: workers) {
      worker.pay();
    }
  }
}
