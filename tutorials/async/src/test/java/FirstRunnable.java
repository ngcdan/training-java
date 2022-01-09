import org.junit.jupiter.api.Test;

public class FirstRunnable {

  @Test
  public void test() {
    Runnable runnable = () -> {
      System.out.println("I am running in " + Thread.currentThread().getName());
    };
    Thread t = new Thread(runnable);
    t.setName("My Thread");
    t.start();
    //    t.run(); //NO using
  }

}