package training.java.object.orented.design2.solution;

public class Solution {
}

interface Payable {
  void pay();
  static class  Implementation implements Payable {
    public void pay() { /* Implement method here */}
  }
}

class Employee extends  Payable.Implementation {}

class Contractor implements Payable {
  private Payable delegate = new Payable.Implementation();
  public void pay() { delegate.pay();}
}

class Volunteer {}
class Vendor implements Payable {
  
  @Override
  public void pay() {}
}

