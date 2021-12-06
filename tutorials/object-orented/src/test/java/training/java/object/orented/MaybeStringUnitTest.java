package training.java.object.orented;

import org.junit.jupiter.api.Test;

public class MaybeStringUnitTest {

  public void showIt1(String data) {
    String upper;

    if(data == null) {
      upper = null;
    } else {
      upper = data.toUpperCase();
    }

    System.out.println(upper);
  }
  
  /*
  public void showIt2(MaybeString data) {
    MaybeString upper = data.toUpperCase();
    String toPrint = upper.orElse("");
    System.out.println(toPrint);
  }
   */

  @Test
  public void test() throws Exception {

  }
}