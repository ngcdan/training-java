package training.java.ds.stack;


public interface Stack<T> {
  void push(T item);
  T pop();
  int size();
  void pushMany(T ... item);
}
