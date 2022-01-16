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
//@Import(ModuleCoreSettingConfig.class)
public class ServerApp implements CommandLineRunner {
  static Logger logger = LoggerFactory.getLogger(ServerApp.class);

  /*
  static ConfigurableApplicationContext context;

  static public ApplicationContext run(String[] args, long wait) throws Exception {
    logger.info("Launch ServerApp with args: {}", StringUtils.join(args, " "));
    Class<?>[] sources = {ModuleCoreSettingConfig.class, ServerApp.class};
    context = SpringApplication.run(sources, args);
    isRunning(wait);

    logger.info("Import data location.........");
    LocationData data = new LocationData(context);
    data.initialize();
    return context;
  }

  static public boolean isRunning(long waitTime) {
    boolean running = false;
    if(waitTime <= 0) waitTime = 1;
    try {
      while(!running && waitTime > 0) {
        if(context != null) running = context.isRunning();
        waitTime -= 100;
        if(!running && waitTime > 0) Thread.sleep(100);
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }
    return running;
  }

  static public void exit() {
    if(context != null) {
      SpringApplication.exit(context);
      context = null;
    }
  }

  public static void main(String[] args) throws Exception {
    run(args, 30000);
    Thread.currentThread().join();
  }
   */

  @Autowired
  private ApplicationContext context;

  public static void main(String[] args) {
    logger.info("Launch ServerApp with args\n: {}", StringUtils.join(args, " \n"));
    Class<?>[] source = {ModuleCoreSettingConfig.class, ServerApp.class};
    SpringApplication app = new SpringApplication(source);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }


  @Override
  public void run(String... args) throws Exception {
    logger.info("Import data location.........");
    LocationData data = new LocationData(context);
    data.initialize();
  }
}