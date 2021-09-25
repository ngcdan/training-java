import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableFutureUnitTest {
  
  @Test
  public void firstCompletableFutureUnitTest() throws ExecutionException, InterruptedException {
    ExecutorService service = Executors.newSingleThreadExecutor();
    // running thread of common fork / join pool
    CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(
      () -> System.out.println(
        "[CompletableFuture.runAsync(runnable)] - Running in the thread " + Thread.currentThread().getName()));
    
    Future<?> future = service.submit(
      () -> System.out.println("[Executor.submit()] - Running in the thread " + Thread.currentThread().getName()));
    
    CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(
      () -> System.out.println(
        "[CompletableFuture.runAsync(runnable, executor)] - Running in the thread " + Thread.currentThread().getName()),
      service);
    
    
    Supplier<String> supplier = () -> Thread.currentThread().getName();
    CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(supplier, service);
    String rs = supplyAsync.join();
    System.out.println("Result : " + rs);
  }
  
  
  @Test
  public void completableFutureCompleteAPI1UnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    Supplier<String> supplier = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return Thread.currentThread().getName();
    };
    
    CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(supplier, service);
    String rs = supplyAsync.join();
    System.out.println("Result : " + rs);
    
    supplyAsync.complete("Tool long!");
    
    rs = supplyAsync.join();
    System.out.println("Result : " + rs);
  }
  
  @Test
  public void completableFutureCompleteAPI2UnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    Supplier<String> supplier = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return Thread.currentThread().getName();
    };
    
    CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(supplier, service);
    
    //does not wait 1s and returns the result passed in param
    supplyAsync.complete("Tool long!");
    
    String rs = supplyAsync.join();
    System.out.println("Result : " + rs);
  }
  
  @Test
  public void completableFutureObtrudeValueAPI2UnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    Supplier<String> supplier = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return Thread.currentThread().getName();
    };
    
    CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(supplier, service);
    
    String rs = supplyAsync.join();
    System.out.println("Result : " + rs);
    
    supplyAsync.obtrudeValue("Tool long!");
    
    rs = supplyAsync.join();
    System.out.println("Result : " + rs);
  }
  
  @Test
  public void completableFutureAPIUnitTest() {
    CompletableFuture<Void> future = new CompletableFuture<>();
    
    Runnable task = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      future.complete(null);
    };
    
    CompletableFuture.runAsync(task);
    System.out.println("Result : " + future.join());
    System.out.println("We are done!");
  }
}
