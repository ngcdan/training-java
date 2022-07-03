package training.java.core.data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class DBServicePlugin {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  public void initDb() throws Exception {}
}