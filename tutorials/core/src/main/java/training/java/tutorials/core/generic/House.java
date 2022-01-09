package training.java.tutorials.core.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class House extends Building {

  public static final Logger logger = LoggerFactory.getLogger(House.class);

  @Override
  public void paint() {
    logger.info("Painting house!");
  }
}