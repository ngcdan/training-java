package training.java.core.settings.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(
  basePackages = {
    "training.java.core.settings.*"
  }
)
@EnableJpaRepositories(
  basePackages = {
    "training.java.core.settings.location.repository",
  }
)
@EntityScan(
  basePackages = {
    "training.java.core.settings.location.entity",
    "training.java.core.settings.location.entity",
    "training.java.core.settings.location.entity"
  }
)
@EnableConfigurationProperties
@EnableTransactionManagement
public class ModuleCoreSettingConfig {}