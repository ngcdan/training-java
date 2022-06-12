package training.java.app.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import training.java.core.settings.ModuleCoreSettingConfig;
import training.java.core.settings.location.data.LocationData;

@Configuration
@EnableConfigurationProperties
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ServerApp implements CommandLineRunner {
  static Logger logger = LoggerFactory.getLogger(ServerApp.class);

  @Autowired
  private ApplicationContext context;

  public static void main(String[] args) {
    logger.info("Launch ServerApp with args\n: {}", StringUtils.join(args, " \n"));

    Class<?>[] source = {ModuleCoreSettingConfig.class, ServerApp.class};
    SpringApplication app = new SpringApplication(source);
    app.setBannerMode(Banner.Mode.OFF);
    Runtime runtime = Runtime.getRuntime();

    System.out.printf("Heap size: %dMB%n", runtime.totalMemory()/1024/1024);
    System.out.printf("Maximum size of Heap: %dMB%n", runtime.maxMemory()/1024/1024);
    System.out.printf("Available processors: %d%n", runtime.availableProcessors());
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Import data location.........");
    LocationData data = new LocationData(context);
    data.initialize();
  }
}