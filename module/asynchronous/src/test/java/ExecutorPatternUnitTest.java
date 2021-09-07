import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPatternUnitTest {
  
  @Test
  public void singleThreadWithExecutorUnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    Runnable task1 = () -> System.out.println("Executed task1");
    Runnable task2 = () -> System.out.println("Executed task2");
    
    //we have a Thread, so like a queue, which task execute first that run first
    service.execute(task1);
    service.execute(task2);
    
    service.shutdown();
  }
  
  @Test
  public void singleThreadWithExecutorAndRunnablesUnitTest() {
//    ExecutorService service = Executors.newSingleThreadExecutor();
    ExecutorService service = Executors.newFixedThreadPool(4);
    Runnable task1 = () -> System.out.println("Executed task1 " + "[" + Thread.currentThread().getName() + "]");
    
    //    IntStream.range(0, 10).mapToObj(i -> task1).forEach(service::execute);
    
    for(int i = 0; i < 10; i++) {
      //      new Thread(task1).start();
      service.execute(task1);
    }
    
    service.shutdown();
  }
}
