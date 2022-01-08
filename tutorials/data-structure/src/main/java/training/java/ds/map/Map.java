package training.java.ds.map;

import lombok.Getter;
import lombok.Setter;

public interface Map<Key, Value> {

  void clear();

  boolean containsKey(Key key);

  boolean containsValue(Value value);

  Value get(Key key);

  boolean isEmpty();

  void put(Key key, Value value);

  Value remove(Key key);

  int size();

  @Getter @Setter
  class Entry<Key, Value> {

    Key key;
    Value value;

    public Entry(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }
}