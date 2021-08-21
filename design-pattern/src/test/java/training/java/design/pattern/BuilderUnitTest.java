package training.java.design.pattern;

import org.junit.jupiter.api.Test;
import training.java.design.pattern.builder.LunchOrderBean;
import training.java.design.pattern.builder.LunchOrderBean.Builder;

public class BuilderUnitTest {
  
  @Test
  public void lunchOrderUnitTest() throws  Exception {
    Builder builder = new Builder();
    builder.bread("Wheat").condiments("Lettuce").dressing("Mayo").meat("Turkey");
  
    LunchOrderBean lunchOrder = builder.build();
    System.out.println(lunchOrder);
  }
}
