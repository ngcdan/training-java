package training.java.core.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import training.java.core.settings.location.CountryGroupLogic;
import training.java.core.settings.location.data.LocationData;
import training.java.core.settings.location.entity.CountryGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ModuleCoreSettingConfig.class)
public class LocationUnitTest {
  @Autowired
  private ApplicationContext context;

  @Autowired
  private CountryGroupLogic countryGroupLogic;


  @Test
  public void contextLoads() {
    assertThat(countryGroupLogic.message()).isEqualTo("Hello");
  }

  @Test
  public void dataParserUnitTest() throws Exception {
    LocationData data = context.getAutowireCapableBeanFactory().createBean(LocationData.class);
    data.initialize();
    final List<CountryGroup> all = countryGroupLogic.findAll();
    Assertions.assertTrue(all.size() > 1);
  }


  @SpringBootApplication
  static class TestConfiguration {
  }
}