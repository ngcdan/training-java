package training.java.design.pattern.adapter;

/**
 *
 * Adapter class, adapts Adaptee to the Target interface
 *
 */
public class Adapter extends Adaptee implements Target {
  
  private Adaptee adaptee;
  
  public Adapter(Adaptee adaptee) {
    this.adaptee = adaptee;
  }
  
  public String request() {
    return adaptee.specialRequest();
  }
}