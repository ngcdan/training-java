package training.java.object.orented.stack;


public interface Stack<T> {

  void push(T item);

  T pop();

  int size();

  void pushMany(T... item);

  class Implementation<T> implements Stack<T> {

    private int stackPointer = 0;
    private T[] contents = (T[]) new Object[1000];

    public void push(T item) {
      assert stackPointer < contents.length : "push to full stack!";
      contents[stackPointer++] = item;
    }

    public T pop() {
      assert stackPointer > 0 : "pop from empty stack";
      return contents[--stackPointer];
    }

    public void pushMany(T... item) {
      assert (stackPointer + item.length) <= contents.length : "To many items";
      System.arraycopy(item, 0, contents, stackPointer, item.length);
      stackPointer += item.length;
    }

    public int size() {
      return contents.length;
    }
  }
}

class MonitorableStack<T> implements Stack<T> {

  private int highWaterMark = 0;
  private Stack.Implementation<T> stack = new Stack.Implementation<>();

  public int maximumsizeSoFar() {
    return highWaterMark;
  }

  @Override
  public void push(T item) {
    if(size() > highWaterMark) {
      highWaterMark = size();
    }
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
    if(stack.size() > highWaterMark) {
      highWaterMark = stack.size();
    }
  }
}