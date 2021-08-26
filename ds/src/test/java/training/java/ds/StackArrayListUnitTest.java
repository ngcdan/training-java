package training.java.ds;

import org.junit.jupiter.api.Test;
import training.java.ds.stack.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackArrayListUnitTest {
  
  @Test
  public void stackArrayListUnitTest() {
    Stack<String> stack = new Stack<String>();
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
}
