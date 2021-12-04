package training.java.core.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppDemo {
  static Logger logger = Logger.getLogger("training.java.core");
  public static void main(String[] args) throws IOException {
    consoleLogger();
    fileLogger();
  }

  private static void fileLogger() throws IOException {
    FileHandler f = new FileHandler("%h/AppDemo_%g.log", 1000, 4);
    f.setFormatter(new SimpleFormatter());
    logger.addHandler(f);
    logger.log(Level.INFO, "We're Logging into a file");
  }

  private static void consoleLogger() {
    logger.setLevel(Level.INFO);
    final ConsoleHandler consoleHandler = new ConsoleHandler();
    final SimpleFormatter newFormatter = new SimpleFormatter();
    consoleHandler.setFormatter(newFormatter);
    logger.addHandler(consoleHandler);
    logger.log(Level.INFO, "We're Logging");
  }
}