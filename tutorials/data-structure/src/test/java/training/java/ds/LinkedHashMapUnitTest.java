package training.java.ds;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedHashMapUnitTest {

  @Test
  public void givenLinkedHashMap_whenGetsOrderedKeySet_thenCorrect() {
    LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
    map.put(1, null);
    map.put(2, null);
    map.put(3, null);
    map.put(4, null);
    map.put(5, null);

    Set<Integer> keys = map.keySet();
    Integer[] arr = keys.toArray(new Integer[0]);

    for (int i = 0; i < arr.length; i++) {
      assertEquals(Integer.valueOf(i + 1), arr[i]);
    }
  }

  @Test
  public void givenLinkedHashMap_whenAccessOrderWorks_thenCorrect() {
    LinkedHashMap<Integer, String> map
      = new LinkedHashMap<>(16, .75f, true);
    map.put(1, null);
    map.put(2, null);
    map.put(3, null);
    map.put(4, null);
    map.put(5, null);

    Set<Integer> keys = map.keySet();
    assertEquals("[1, 2, 3, 4, 5]", keys.toString());

    map.get(4);
    assertEquals("[1, 2, 3, 5, 4]", keys.toString());

    map.get(1);
    assertEquals("[2, 3, 5, 4, 1]", keys.toString());

    map.get(3);
    assertEquals("[2, 5, 4, 1, 3]", keys.toString());
  }

}