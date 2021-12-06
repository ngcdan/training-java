package training.java.ds;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import training.java.ds.list.LinkedList;

public class LinkedListUnitTest {

  @Test
  public void testLinkedList() throws Exception {
    testList(new LinkedList<Integer>());
  }

  void testList(LinkedList<Integer> linkedList) {
    Integer[] list = new Integer[]{1, 2, 3, 4};
    // test Size empty
    assertEquals(0, linkedList.size());
    // test addAll
    linkedList.addAll(list);
    assertEquals(4, linkedList.size());
    assertEquals(-1, linkedList.findPos(5));

    // test remove element 
    linkedList.remove(3); // linked list expected [1, 2, 4]
    assertEquals(list.length - 1, linkedList.size());
    assertEquals(Integer.valueOf(1), linkedList.get(0));
    assertEquals(Integer.valueOf(2), linkedList.get(1));
    assertEquals(Integer.valueOf(4), linkedList.get(2));

    //test remove element not have in the linked list
    try {
      linkedList.remove(5);
      fail("Exception not thrown");
    } catch(IllegalArgumentException e) {
      assertSame("Could not find element in the Linked List", e.getMessage());
    }

    // test removeAt
    linkedList.removeAt(1); // linked list expected [1, 4]
    assertEquals(2, linkedList.size());
    assertEquals(Integer.valueOf(1), linkedList.get(0));
    assertEquals(Integer.valueOf(4), linkedList.get(1));

    // test add ( add a element in last of List )
    linkedList.add(2);
    assertEquals(3, linkedList.size());
    assertEquals(Integer.valueOf(2), linkedList.get(linkedList.size() - 1));

    // test set
    linkedList.set(1, 24);
    linkedList.set(2, 25);
    assertEquals(3, linkedList.size());
    assertEquals(Integer.valueOf(24), linkedList.get(1));
    assertEquals(Integer.valueOf(25), linkedList.get(2));

    // test clear
    linkedList.clear();
    assertEquals(0, linkedList.size());
  }

}