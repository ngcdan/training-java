package training.java.app.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import training.java.core.data.DataInitService;

@Profile("data")
@Configuration
public class DataProfileConfig {
  @Bean
  public DataInitService createDataService() {
    return new DataInitService();
  }
}