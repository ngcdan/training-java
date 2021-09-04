import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DeadLockUnitTest {
  static class A {
    private final Object key1 = new Object();
    private final Object key2 = new Object();
    
    public void a() {
      synchronized (key1) {
        System.out.println("[" + Thread.currentThread().getName() + "] + I am in a()");
        b();
      }
    }
    
    private void b() {
      synchronized (key2) {
        System.out.println("[" + Thread.currentThread().getName() + "] + I am in b()");
        c();
      }
    }
    
    private void c() {
      synchronized (key1) {
        System.out.println("[" + Thread.currentThread().getName() + "] + I am in c()");
      }
    }
  }
  
  @Test
  @Tag("tofix")
  public void deadLockUnitTest() throws InterruptedException {
    A a = new A();
    Runnable r1 = a::a;
    Runnable r2 = a::b;
    
    Thread t1 = new Thread(r1);
    t1.start();
    
    Thread t2 = new Thread(r2);
    t2.start();
    
    t1.join();
    t2.join();
  }
}

