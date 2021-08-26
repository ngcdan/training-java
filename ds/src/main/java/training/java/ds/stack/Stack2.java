package training.java.ds.stack;


import java.util.ArrayList;

public class Stack2<T> {
  private int stackPointer = 0;
  private ArrayList<T> contents = new ArrayList<>();
  
  public void push(T item) {
    contents.add(stackPointer++, item);
  }
  
  public T pop() {
    return contents.remove(--stackPointer);
  }
}
