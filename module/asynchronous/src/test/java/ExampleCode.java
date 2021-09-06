import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExampleCode {
  public void createHttpClient() throws IOException, InterruptedException {
    HttpClient client = HttpClient
      .newBuilder()
      .version(Version.HTTP_1_1) // work in http 2
      .build();
    
    
    // create a request
    HttpRequest request = HttpRequest
      .newBuilder()
      .GET()
      .uri(URI.create("https://anysite.com"))
      .build();
    
    // create a response
    HttpResponse<String> response = client.send(
      request,
      HttpResponse.BodyHandlers.ofString());
  }
  
  /*
  @Test
  private JSONObject sendRequest(JSONObject json) throws Exception {
    String BASE_URL = "https://anysite.com";
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest httpRequest = HttpRequest.newBuilder(new URI(BASE_URL))
      .header("Accept", "application/json")
      .header("Content-Type", "application/json")
      //      .timeout(TIMEOUT_DURATION)
      .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
      .build();
    
    HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    String jsonResponse = httpResponse.body();
    
    return new JSONObject(jsonResponse);
   */
  }
