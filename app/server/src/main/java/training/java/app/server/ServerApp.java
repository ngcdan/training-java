package training.java.app.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import training.java.core.settings.config.ModuleCoreSettingConfig;

@Configuration
@EnableConfigurationProperties
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@Import(ModuleCoreSettingConfig.class)
public class ServerApp {
  static Logger logger = LoggerFactory.getLogger(ServerApp.class);

  public static void main(String[] args) {
    logger.info("Launch ServerApp with args: {}", StringUtils.join(args, " "));
    Class<?>[] source = {ModuleCoreSettingConfig.class, ServerApp.class};
    SpringApplication app = new SpringApplication(source);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }
}