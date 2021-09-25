import org.junit.jupiter.api.Test;

public class ConsumerProducerUnitTest {
  private static final Object key = new Object();
  private static int[] buffer;
  private static int count;
  
  static class Producer {
    void produce() {
      synchronized (key) {
        if(isFull(buffer)) {
          try {
            key.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        buffer[count++] = 1;
        key.notify();
      }
    }
    
    private boolean isFull(int[] buffer) {
      return count == buffer.length;
    }
  }
  
  static class Consumer {
    void consume() {
      synchronized (key) {
        if(isEmpty()) {
          try {
            key.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        buffer[--count] = 0;
        key.notify();
      }
    }
    
    private boolean isEmpty() {
      return count == 0;
    }
  }
  
  
  @Test
  public void consumerProducerUnitTest() throws InterruptedException {
    buffer = new int[50];
    count = 0;
    
    Producer producer = new Producer();
    Consumer consumer = new Consumer();
    
    Runnable producerTasks = () -> {
      for (int i = 0; i < 50; i++) {
        producer.produce();
      }
      System.out.println("Done producing");
    };
    
    Runnable consumerTasks = () -> {
      for (int i = 0; i < 45; i++) {
        consumer.consume();
      }
      System.out.println("Done consuming");
    };
    
    Thread consumerThread = new Thread(consumerTasks);
    Thread producerThread = new Thread(producerTasks);
    
    consumerThread.start();
    producerThread.start();
    
    consumerThread.join();
    producerThread.join();
    
    System.out.println("Data in the buffer " + count);
  }
}
