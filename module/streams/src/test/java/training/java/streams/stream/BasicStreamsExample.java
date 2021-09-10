package training.java.streams.stream;

import org.junit.jupiter.api.Test;
import training.java.streams.ExampleData;
import training.java.streams.Product;

import java.util.List;
import java.util.stream.Stream;

public class BasicStreamsExample {
  
  @Test
  public void basicStreamExample01() {
    List<Product> products = ExampleData.getProducts();
    
    // Streams are lazy - without a terminal operation, no work is done
    // When you call intermediate operations on a stream, you're only building the pipeline;
    // no elements are flowing through it yet
    Stream<Product> stream = products.stream().map(product -> {
      System.out.println(product);
      return product;
    });
    
    // When you call a terminal operation, the stream will do the work
    stream.forEach(product -> {});
  }
}
