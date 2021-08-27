package training.java.object.orented.design2;

public class Problem {
}

class Worker {
  public void pay() { /* .. */}
}

class Employee extends training.java.object.orented.design2.solution.Worker {}
class Contractor extends training.java.object.orented.design2.solution.Worker {}

//Problem: Interface Segregation Principal
class Volunteer extends training.java.object.orented.design2.solution.Worker {
  public void pay() {
    throw new UnsupportedOperationException();
  }
}

class Vendor extends training.java.object.orented.design2.solution.Worker {
  public void pay() {
    // Worker's implementation doesn't work here!
  }
}
