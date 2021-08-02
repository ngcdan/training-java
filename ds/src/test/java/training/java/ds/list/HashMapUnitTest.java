package training.java.ds.list;

import org.junit.jupiter.api.Test;
import training.java.ds.map.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapUnitTest {
  
  @Test
  public void testHashMap() throws Exception {
    HashMap<String, Integer> map = new HashMap<>();
    assertTrue(map.isEmpty());
    map.put("key1", 1);
    assertEquals(1, map.get("key1"));
    map.put("key1", 2);
    assertEquals(Integer.valueOf(2), map.get("key1"));
    map.put("key2", 12);
    map.put("key0", 0);
    map.put("key3", 3);
    map.put("key6", 6);
    
    assertEquals(5, map.size());
    assertEquals(Integer.valueOf(3), map.get("key3"));
    assertEquals(Integer.valueOf(6), map.get("key6"));
    assertNull(map.get("key4"));
    assertFalse(map.containsKey("key14"));
    assertTrue(map.containsKey("key3"));
    
    map.remove("key1");
    assertEquals(4, map.size());
    assertNull(map.get("key1"));
    map.remove("key3");
    assertFalse(map.containsKey("key3"));
    assertNull(map.get("key3"));
    assertEquals(Integer.valueOf(6), map.get("key6"));
    assertEquals(Integer.valueOf(0), map.get("key0"));
    assertTrue(map.containsValue(0));
    assertTrue(map.containsValue(6));
    assertTrue(map.containsValue(12));
    assertFalse(map.containsValue(2));
    
    map.clear();
    assertEquals(0, map.size());
    assertFalse(map.containsValue(0));
  }
}
