package training.java.core.data;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBService {

  @Autowired
  private ApplicationContext context;

  @Autowired(required = false)
  private List<DBServicePlugin> plugins;

  public <T> void initDb() throws Exception {
    if(plugins == null) return;
    for(DBServicePlugin plugin : plugins) {
      Logger log = plugin.getLogger();
      log.info("initDb()");
      plugin.initDb();
    }
  }

}