package training.java.generic;

import org.junit.jupiter.api.Test;

public class DemoExample {
  
  @Test
  public void typeUnsafeUnitTest() {
    CircularBuffer buffer = new CircularBuffer(10);
    buffer.offer("a");
    buffer.offer("bc");
    buffer.offer("d");
    
    buffer.offer(1);
    
    String value = concatenate(buffer);
    System.out.println(value);
  }
  
  @Test
  public void typeSafeUnitTest() {
    StringCircularBuffer buffer = new StringCircularBuffer(10);
    buffer.offer("a");
    buffer.offer("bc");
    buffer.offer("d");
    //    buffer.offer(1);
    
    String value = concatenate(buffer);
    System.out.println(value);
  }
  
  @Test
  public void typeSafeWithGenericUnitTest() {
    GenericCircularBuffer<String> buffer = new GenericCircularBuffer<>(10);
    buffer.offer("a");
    buffer.offer("bc");
    buffer.offer("d");
    //    buffer.offer(1);
    
    String value = concatenate(buffer);
    System.out.println(value);
  }
  
  private static String concatenate(CircularBuffer buffer) {
    StringBuilder result = new StringBuilder();
    String value;
    while ((value = (String) buffer.poll()) != null) result.append(value);
    return result.toString();
  }
  
  private static String concatenate(StringCircularBuffer buffer) {
    StringBuilder result = new StringBuilder();
    String value;
    while ((value = buffer.poll()) != null) result.append(value);
    return result.toString();
  }
  
  private static String concatenate(GenericCircularBuffer<String> buffer) {
    StringBuilder result = new StringBuilder();
    String value;
    while ((value = buffer.poll()) != null) result.append(value);
    return result.toString();
  }
}
