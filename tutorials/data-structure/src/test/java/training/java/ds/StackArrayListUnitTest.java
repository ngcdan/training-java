package training.java.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import training.java.ds.stack.MonitorableStack;
import training.java.ds.stack.SimpleStack;

public class StackArrayListUnitTest {

  @Test
  public void stackArrayListUnitTest() {
    SimpleStack<String> stack = new SimpleStack<>();
    stack.push("One");
    stack.push("Two");
    stack.push("Three");
    stack.push("Four");
    stack.push("Five");

    assertEquals("Five", stack.pop());
    assertEquals("Four", stack.pop());
    assertEquals("Three", stack.pop());
    assertEquals("Two", stack.pop());
    assertEquals("One", stack.pop());
  }


  @Test
  public void stackArrayList1UnitTest() {
    MonitorableStack<String> stack = new MonitorableStack<>();
    stack.pushMany("One", "Two", "Three", "Four", "Five");

    assertEquals("Five", stack.pop());
    assertEquals("Four", stack.pop());
    assertEquals("Three", stack.pop());
    assertEquals("Two", stack.pop());
    assertEquals("One", stack.pop());
  }
}