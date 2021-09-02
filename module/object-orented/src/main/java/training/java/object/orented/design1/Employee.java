package training.java.object.orented.design1;

interface TimeSheet{}
interface Invoice{};
interface Payable {
  void pay();
}

abstract class Worker {
  private String name;
  public Worker(String name) {
    this.name = name;
  }
  
  public void pay() {
    Money due = getAmountDue();
  }
  
  abstract protected Money getAmountDue();
}

public class Employee extends Worker {
  private TimeSheet[] unpaidTimesheets;
  
  public Employee(String name) {super(name);}
  
  @Override
  protected Money getAmountDue() {
    //...
    return new Money(12.34, Currency.USD);
  }
  
  public void attachTimesheet(TimeSheet i) {}
}

class Contractor extends Worker {
  public Contractor(String name) {super(name);}
  
  @Override
  protected Money getAmountDue() {
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
