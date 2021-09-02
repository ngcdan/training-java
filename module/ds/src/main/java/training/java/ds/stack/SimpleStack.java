package training.java.ds.stack;

public class SimpleStack<T> implements Stack<T> {
  private int stackPointer = 0;
  private T[] contents = (T[]) new Object[1000];
  
  public void push(T item) {
    assert stackPointer < contents.length : "push to full stack!";
    contents[stackPointer++] = item;
  }
  
  public T pop() {
    assert  stackPointer > 0 : "pop from empty stack";
    return contents[--stackPointer];
  }
  
  public void pushMany(T... item) {
    assert  (stackPointer + item.length) <= contents.length : "To many items";
    System.arraycopy(item, 0, contents, stackPointer, item.length);
    stackPointer += item.length;
  }
  
  public int size() {
    return contents.length;
  }
}
