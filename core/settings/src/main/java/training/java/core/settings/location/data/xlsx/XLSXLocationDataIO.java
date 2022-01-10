package training.java.core.settings.location.data.xlsx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import training.java.xlsx.XLSXDataSheetProcessor;
import training.java.xlsx.XLSXSimpleSheetProcessor;

public class XLSXLocationDataIO {
  @Autowired
  private ApplicationContext context;

  public void inputLocations(String xlsxFile) throws Exception {
    XLSXDataSheetProcessor xlsxProcessor =
      new XLSXDataSheetProcessor().open(xlsxFile);
    xlsxProcessor.process("Country Groups", createCountryGroupSheetProcessor());
    xlsxProcessor.close();
  }

  private XLSXSimpleSheetProcessor createCountryGroupSheetProcessor() {
    SectionCountryGroupProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionCountryGroupProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }
}