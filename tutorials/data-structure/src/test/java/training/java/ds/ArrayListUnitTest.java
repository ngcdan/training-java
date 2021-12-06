package training.java.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import training.java.ds.list.ArrayList;

public class ArrayListUnitTest {

  @Test
  public void testArrayListUnitTest() throws Exception {

    ArrayList<String> arrayList = new ArrayList<String>();
    arrayList.add("One");
    arrayList.add("Two");
    arrayList.add("Three");
    arrayList.add("Four");
    arrayList.add("Five");

    assertEquals(5, arrayList.size());

    //test retrieve by position
    assertEquals("One", arrayList.get(0));
    assertEquals("Four", arrayList.get(3));
    assertEquals("Five", arrayList.get(arrayList.size() - 1));

    //test find by position
    assertEquals(-1, arrayList.findPos("Six"));

    // test remove
    arrayList.remove("Two");
    assertEquals(4, arrayList.size());
    assertEquals(-1, arrayList.findPos("Two"));
    assertEquals("Three", arrayList.get(1));

    // test removeAt
    arrayList.removeAt(2);  //now element is Four
    assertEquals(3, arrayList.size());
    assertEquals(-1, arrayList.findPos("Four"));
    assertEquals(2, arrayList.findPos("Five"));

    // test add
    arrayList.add("Eleven");
    assertEquals(4, arrayList.size());
    assertEquals("Eleven", arrayList.get(arrayList.size() - 1));

    // test set
    arrayList.set(0, "Twelve");
    arrayList.set(2, "Thirteen");
    assertEquals(4, arrayList.size());
    assertEquals("Twelve", arrayList.get(0));
    assertEquals("Thirteen", arrayList.get(2));

    // test clear
    arrayList.clear();
    assertEquals(0, arrayList.size());
    assertNull(arrayList.get(1));
  }
}