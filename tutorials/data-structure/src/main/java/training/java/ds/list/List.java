package training.java.ds.list;

public interface List<T> {

  int size();

  void clear();

  void add(T obj);

  void addAll(T[] obj);

  boolean remove(T obj);

  T removeAt(int pos);

  T get(int pos);

  void set(int pos, T obj);

  int findPos(T obj);
}