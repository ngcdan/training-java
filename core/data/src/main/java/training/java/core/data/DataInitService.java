package training.java.core.data;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.text.DecimalFormat;

@Slf4j
public class DataInitService {

  @Getter
  @Autowired
  private DBService dbService;

  @EventListener(ContextRefreshedEvent.class)
  public void startInitData() throws Exception {
    log.info("Data Application start init!");
    long start = System.currentTimeMillis();
    dbService.initDb();
    long execTime = System.currentTimeMillis() - start;
    log.info("Import Data In {0}", (asHumanReadable(execTime)));
  }

  @EventListener(ApplicationReadyEvent.class)
  public void initDataDone() {
    log.info("Data Application initialized!");
  }

  public String asHumanReadable(long timeInMs) {
    if(timeInMs > (60 * 60 * 1000L)) {
      return new DecimalFormat("#.##").format((double) timeInMs / (60 * 60 * 1000L)) + " h";
    } else if (timeInMs > 60 * 1000L) {
      return new DecimalFormat("#.##").format((double) timeInMs / (60 * 1000L)) + " m";
    } else if (timeInMs > 1000L) {
      return new DecimalFormat("#.##").format((double) timeInMs / 1000L) + " s";
    } else {
      return timeInMs + " ms";
    }
  }

}