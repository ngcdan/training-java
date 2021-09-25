package training.java.ds.stack;

public class MonitorableStack<T> implements Stack<T>{
  private int highWaterMark = 0;
  private SimpleStack<T> stack = new SimpleStack<>();
  
  public int maximumsizeSoFar() {
    return highWaterMark;
  }
  
  @Override
  public void push(T item) {
    if(size() > highWaterMark) highWaterMark =size();
    stack.push(item);
  }
  
  @Override
  public T pop() { return stack.pop();}
  
  @Override
  public int size() { return stack.size();}
  
  @SafeVarargs
  @Override
  public final void pushMany(T... item) {
    stack.pushMany(item);
    if(stack.size() > highWaterMark) highWaterMark = stack.size();
  }
}
