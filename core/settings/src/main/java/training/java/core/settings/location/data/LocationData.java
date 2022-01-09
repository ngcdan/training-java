package training.java.core.settings.location.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import training.java.core.settings.location.data.xlsx.XLSXLocationDataIO;

public class LocationData {
  @Autowired
  private ApplicationContext context;

  public void initialize() throws Exception {
    XLSXLocationDataIO dataIO = context.getAutowireCapableBeanFactory().createBean(XLSXLocationDataIO.class);
    String xlsxFile = "classpath:data/sample/settings/Location.xlsx";
    dataIO.inputLocations(xlsxFile);
  }

}