package training.java.reflection;

import org.h2.tools.Server;
import org.junit.jupiter.api.Test;

public class DBLauncher {

  @Test
  public void start() throws Exception {
    Server.main("-ifNotExists");
    System.out.println("DB Launched!");
    Thread.currentThread().join();
  }
}
