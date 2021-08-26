package training.java.ds.stack;


import java.util.ArrayList;

public class Stack<T> extends ArrayList<T> {
  private int stackPointer = 0;
  
  public void push(T item) {
    add(stackPointer++, item);
  }
  
  public T pop() {
    return   remove(--stackPointer);
  }
}
