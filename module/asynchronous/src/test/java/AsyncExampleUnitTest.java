import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AsyncExampleUnitTest {
  
  @NoArgsConstructor
  static class User {
    
    @Setter @Getter
    private long id;
    
    public User(long id) { this.id = id;}
    
    @Override
    public String toString() {
      return "User{id=" + id + '}';
    }
  }
  
  @NoArgsConstructor
  static class Email {
    
    @Setter @Getter
    private long id;
    
    public Email(long id) { this.id = id;}
    
    @Override
    public String toString() {
      return "Email{id=" + id + '}';
    }
  }
  
  private void sleep(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void syncExampleUnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    
    Supplier<List<Long>> supplierIDs = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };
    
    Function<List<Long>, List<User>> fetchUsers = ids -> {
      sleep(300);
      return ids.stream().map(User::new).collect(Collectors.toList());
    };
    
    Consumer<List<User>> displayer = users -> {
      System.out.println("Running in " + Thread.currentThread().getName());
      users.forEach(System.out::println);
    };
    
    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplierIDs);
    completableFuture
      .thenApply(fetchUsers)
      .thenAcceptAsync(displayer, service);
    
    sleep(1_000);
    service.shutdown();
  }
  
  @Test
  public void asyncExampleUnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    
    Supplier<List<Long>> supplierIDs = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers = ids -> {
      sleep(300);
      System.out.println("Currently running in " + Thread.currentThread().getName());
      Supplier<List<User>> usersSupplier = () -> ids.stream().map(User::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(usersSupplier);
    };
    
    Consumer<List<User>> displayer = users -> {
      System.out.println("Running in " + Thread.currentThread().getName());
      users.forEach(System.out::println);
    };
    
    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplierIDs);
    completableFuture
      .thenCompose(fetchUsers)
      .thenAcceptAsync(displayer, service);
    
    sleep(1_000);
    service.shutdown();
  }
  
  @Test
  public void asyncExample2UnitTest() {
    ExecutorService service1 = Executors.newSingleThreadExecutor();
    ExecutorService service2 = Executors.newSingleThreadExecutor();
    
    Supplier<List<Long>> supplierIDs = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers = ids -> {
      System.out.println("Function is running in " + Thread.currentThread().getName());
      sleep(300);
      Supplier<List<User>> usersSupplier = () -> {
        System.out.println("Currently running in " + Thread.currentThread().getName());
        return ids.stream().map(User::new).collect(Collectors.toList());
      };
      
      //      return CompletableFuture.supplyAsync(usersSupplier);
      return CompletableFuture.supplyAsync(usersSupplier, service2);
    };
    
    Consumer<List<User>> displayer = users -> {
      System.out.println("Running in " + Thread.currentThread().getName());
      users.forEach(System.out::println);
    };
    
    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplierIDs);
    completableFuture
      .thenComposeAsync(fetchUsers, service2)
      .thenAcceptAsync(displayer, service1);
    
    sleep(1_000);
    service1.shutdown();
  }
  
  @Test
  public void asyncExampleMultiBranchUnitTest() {
    ExecutorService service = Executors.newSingleThreadExecutor();
    
    Supplier<List<Long>> supplierIDs = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers = ids -> {
      sleep(250);
      Supplier<List<User>> usersSupplier = () -> ids.stream().map(User::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(usersSupplier);
    };
    
    Function<List<Long>, CompletableFuture<List<Email>>> fetchEmails = ids -> {
      sleep(500);
      Supplier<List<Email>> emailsSupplier = () -> ids.stream().map(Email::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(emailsSupplier);
    };
    
    Consumer<List<User>> displayer = users -> users.forEach(System.out::println);
    
    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplierIDs);
    
    CompletableFuture<List<User>> userFuture = completableFuture.thenCompose(fetchUsers);
    CompletableFuture<List<Email>> emailFuture = completableFuture.thenCompose(fetchEmails);
    userFuture.thenAcceptBoth(
      emailFuture,
      (users, emails) -> {
        System.out.println(users.size() + " - " + emails.size());
      });
    
    sleep(1_000);
    service.shutdown();
  }
  
  @Test
  public void syncExampleMultiBranchEitherUnitTest() {
    Supplier<List<Long>> supplierIDs = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers1 = ids -> {
      sleep(250);
      Supplier<List<User>> users1Supplier = () -> ids.stream().map(User::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(users1Supplier);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers2 = ids -> {
      sleep(5000);
      Supplier<List<User>> users2Supplier = () -> ids.stream().map(User::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(users2Supplier);
    };
    
    Consumer<List<User>> displayer = users -> users.forEach(System.out::println);
    
    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplierIDs);
    
    CompletableFuture<List<User>> user1Future = completableFuture.thenCompose(fetchUsers1);
    CompletableFuture<List<User>> user2Future = completableFuture.thenCompose(fetchUsers2);
    
    user1Future.thenRun(() -> System.out.println("User 1"));
    user2Future.thenRun(() -> System.out.println("User 2"));
    
    user1Future.acceptEither(user2Future, displayer);
    
    sleep(6_000);
  }
  
  @Test
  public void asyncExampleMultiBranchEitherUnitTest() {
    
    Supplier<List<Long>> supplierIDs = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers1 = ids -> {
      sleep(250);
      Supplier<List<User>> users1Supplier = () -> ids.stream().map(User::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(users1Supplier);
    };
    
    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers2 = ids -> {
      sleep(5000);
      Supplier<List<User>> users2Supplier = () -> ids.stream().map(User::new).collect(Collectors.toList());
      return CompletableFuture.supplyAsync(users2Supplier);
    };
    
    Consumer<List<User>> displayer = users -> users.forEach(System.out::println);
    
    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplierIDs);
    
    CompletableFuture<List<User>> user1Future = completableFuture.thenComposeAsync(fetchUsers1);
    CompletableFuture<List<User>> user2Future = completableFuture.thenComposeAsync(fetchUsers2);
    
    user1Future.thenRun(() -> System.out.println("User 1"));
    user2Future.thenRun(() -> System.out.println("User 2"));
    
    user1Future.acceptEither(user2Future, displayer);
    
    sleep(6_000);
  }
}
