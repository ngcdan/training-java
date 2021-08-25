package training.java.object.orented.design;

import java.util.Currency;

interface TimeSheet{}
interface Invoice{};
class Money1 {
  public Money1(double val, Currency currency) {}
}

interface Payable {
  void pay();
}

abstract class Worker implements Payable {
  private String name;
  public Worker(String name) {
    this.name = name;
  }
  
  public void pay() {
    Money1 due = getAmountDue();
  }
  
  abstract protected Money1 getAmountDue();
}

public class Employee extends Worker {
  private TimeSheet[] unpaidTimesheets;
  
  public Employee(String name) {super(name);}
  
  @Override
  protected Money1 getAmountDue() {
    //...
    return new Money1(12.34, Currency.getInstance("USD"));
  }
  
  public void attachTimesheet(TimeSheet i) {}
}

class Contractor extends Worker {
 public Contractor(String name) {super(name);}
  
  @Override
  protected Money1 getAmountDue() {
    return null;
  }
  
  public Invoice[] invoiceDue;
  public void attachInvoice(Invoice i) {}
}

class AccountsPayable {
  Payable[] creators;
  
  public void payEveryBody() {
    for(Payable payable: creators) {
      payable.pay();
    }
  }
}
