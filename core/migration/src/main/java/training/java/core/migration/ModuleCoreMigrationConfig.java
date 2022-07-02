package training.java.core.migration;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
  basePackages = {
    "training.java.core.migration"
  }
)
@EnableConfigurationProperties
public class ModuleCoreMigrationConfig {}