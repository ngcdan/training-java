## Completable Future


## Trigger a task on the completion of other task?

```
thenCompose() <=> flatMap() in Stream API and OptionalAPi
```

### When both tasks complete, you can:
- execute runnable
- execute BiConsumer
- execute BiFunction

Example Code: pattern can use when what u want to do wait for both results to become available 
```java
CompletableFuture<Long> cf1 = ...;
CompletableFuture<User> cf2 = ...;

// accept BiConsumer
CompletableFuture<Void> cf3 = cf1.thenAcceptBoth(cf2, (id, name) -> logger.info(...));

// Accept BiFunction
  CompletableFuture<List<User>> cf3 = cf1.thenCombine(cf2, (id, user) -> query(...));
```

### When either tasks complete, you can:
- execute runnable
- execute Consumer
- execute Function

In that case, both completable future must return objects of the same type 

Example Code: pattern can use when what u want to do wait for both results to become available
```java
CompletableFuture<Long> cf1 = ...;
CompletableFuture<Long> cf2 = ...;

// accept Completable Future and Consumer
CompletableFuture<Void> cf3 = cf1.thenAcceptEither(cf2, (id) -> logger.info(...));

// accept Completable Future and Function
CompletableFuture<List<User>> cf3 = cf1.thenApplyToEither(cf2, (id) -> readUser(...));

// accept Completable Future and Runnale
CompletableFuture<List<User>> cf3 = cf1.thenRunAfterEither(cf2, () -> logger.info(...));

  
```
