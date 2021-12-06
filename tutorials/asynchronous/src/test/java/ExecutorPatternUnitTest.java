import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Test;

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
  public void threadWithExecutorAndRunnablesUnitTest() {
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

  @Test
  public void threadWithCallableAndFutureUnitTest() throws ExecutionException, InterruptedException, TimeoutException {
    ExecutorService service = Executors.newFixedThreadPool(4);

    Callable<String> task = () -> {
      Thread.sleep(500);
      return "Executed task " + "[" + Thread.currentThread().getName() + "]";
    };

    try {
      for(int i = 0; i < 10; i++) {
        Future<String> future = service.submit(task);
        //System.out.println("I get " + future.get(200, TimeUnit.MILLISECONDS));
        System.out.println("I get " + future.get());
      }
    } finally {
      service.shutdown();
    }

  }
}