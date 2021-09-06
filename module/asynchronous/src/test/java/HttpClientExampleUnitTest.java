import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientExampleUnitTest {
  
  @Test
  public void demoHttpClientExampleUnitTest() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
    
    HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://www.google.com.vn")).build();
    
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
    int length = response.body().length();
    System.out.println("Length: " + length);
  }
  
  @Test
  public void demoAsyncHttpClientExampleUnitTest() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
    
    HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://www.google.com.vn")).build();
    
    CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, BodyHandlers.ofString());
    future
      .thenAcceptAsync(
        response -> {
          System.out.println(
            "Body = " + response.body().length() + " [" + Thread.currentThread().getName() + "]");
        }, executorService)
      .thenRun(() -> System.out.println("Done!"))
      .join();
  }
}
