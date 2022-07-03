package training.java.core.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import training.java.core.data.DBServicePlugin;
import training.java.core.settings.location.data.LocationData;

@Component
@Order(1)
public class SettingsDBServicePlugin extends DBServicePlugin {
  @Autowired
  private ApplicationContext context;

  @Override
  public void initDb() throws Exception {
    logger.info("Import data settings.........");
    new LocationData(context).initialize();
  }
}