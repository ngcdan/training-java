package training.java.ds.stack;

public class MonitorableStack<T> extends Stack<T>{
  private int highWaterMark = 0;
  
  public int maximumsizeSoFar() {
    return highWaterMark;
  }
  
  @Override
  public void push(T item) {
    if(size() > highWaterMark) highWaterMark =size();
    super.push(item);
  }
}
