package training.java.design.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import training.java.design.pattern.adapter.Adaptee;
import training.java.design.pattern.adapter.Adapter;
import training.java.design.pattern.adapter.Target;

public class AdapterUnitTest {

  @Test
  public void testAdapter() {

    // creates Adaptee
    Adaptee adaptee = new Adaptee();

    // creates Adapter
    Target target = new Adapter(adaptee);

    assertEquals("specialRequest", target.request());
  }

}