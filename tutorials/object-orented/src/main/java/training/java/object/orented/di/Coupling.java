package training.java.object.orented.di;

public class Coupling {}

/*
class AccountsPayable {
  private static AccountsPayable instance = new AccountsPayable();
  
  static AccountsPayable getInstance() {return instance;}
  
  public void amPaying(Payable employee) {}
}

interface Payable {
  
  public void pay();
  
  static class  Implementation implements Payable {
    @Override
    public void pay() {
      //TOFIX: Coupling
      AccountsPayable accountsPayable = AccountsPayable.getInstance();
      accountsPayable.amPaying(this);
    }
  }
}
*/


interface PayableObserver {

  public void amPaying(Payable employee);
}

class AccountsPayable implements PayableObserver {

  private static AccountsPayable instance = new AccountsPayable();

  static AccountsPayable getInstance() {return instance;}

  public void amPaying(Payable employee) {}
}

interface Payable {

  public void pay();

  static class Implementation implements Payable {

    PayableObserver observer;

    public void addObserver(PayableObserver observer) {
      this.observer = observer;
    }

    @Override
    public void pay() {
      observer.amPaying(this);
    }
  }
}

class Peon extends Payable.Implementation {}

class Demo {

  AccountsPayable accountsPayable = new AccountsPayable();
  Peon worker = new Peon();

  public void demo() {
    worker.addObserver(accountsPayable);
    worker.pay();
  }
}