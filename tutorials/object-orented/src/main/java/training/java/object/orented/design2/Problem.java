package training.java.object.orented.design2;

public class Problem {}

class Worker {
  public void pay() { /* .. */}
}

class Employee extends Worker {}
class Contractor extends Worker {}

//Problem: Interface Segregation Principal
class Volunteer extends Worker {
  public void pay() {
    throw new UnsupportedOperationException();
  }
}

class Vendor extends Worker {
  public void pay() {
    // Worker's implementation doesn't work here!
  }
}
