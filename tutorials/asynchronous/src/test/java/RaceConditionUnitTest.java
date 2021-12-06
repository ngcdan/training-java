import org.junit.jupiter.api.Test;

public class RaceConditionUnitTest {

  static class LongWrapper {

    private long l;

    public long getValue() { return this.l;}

    public LongWrapper(long l) { this.l = l;}

    public void incrementValue() { l = l + 1;}
  }

  static class FixedLongWrapper {

    final Object key = new Object();
    private long l;

    public long getValue() { return this.l;}

    public FixedLongWrapper(long l) { this.l = l;}

    public void incrementValue() {
      synchronized (key) {
        l = l + 1;
      }
    }
  }

  @Test
  public void raceConditionUnitTest() throws InterruptedException {
    LongWrapper longWrapper = new LongWrapper(0L);

    Runnable runnable = () -> {
      for(int i = 0; i < 1_000; i++) {
        longWrapper.incrementValue();
      }
    };
    Thread[] threads = new Thread[1_000];
    for(int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(runnable);
      threads[i].start();
    }

    for(Thread thread : threads) {
      thread.join();
    }

    // value < 1000000
    System.out.println("Value :" + longWrapper.getValue());
  }

  @Test
  public void fixedRaceConditionUnitTest() throws InterruptedException {
    FixedLongWrapper longWrapper = new FixedLongWrapper(0L);

    Runnable runnable = () -> {
      for(int i = 0; i < 1_000; i++) {
        longWrapper.incrementValue();
      }
    };
    Thread[] threads = new Thread[1_000];
    for(int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(runnable);
      threads[i].start();
    }

    for(Thread thread : threads) {
      thread.join();
    }

    // value  = 1000000
    System.out.println("Value :" + longWrapper.getValue());
  }
}