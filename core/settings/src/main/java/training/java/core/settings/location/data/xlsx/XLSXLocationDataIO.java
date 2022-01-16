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
    xlsxProcessor.process("Countries", createCountrySheetProcessor());
    xlsxProcessor.process("State", createStateSheetProcessor());
    xlsxProcessor.process("City", createCitySheetProcessor());
    xlsxProcessor.process("Location Type", createLocationTypeSheetProcessor());
    xlsxProcessor.process("Locations", createLocationSheetProcessor());
    xlsxProcessor.close();
  }

  private XLSXSimpleSheetProcessor createCountryGroupSheetProcessor() {
    SectionCountryGroupProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionCountryGroupProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }

  private XLSXSimpleSheetProcessor createCountrySheetProcessor() {
    SectionCountryProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionCountryProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }

  public XLSXSimpleSheetProcessor createStateSheetProcessor() {
    SectionStateProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionStateProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }

  public XLSXSimpleSheetProcessor createCitySheetProcessor() {
    SectionCityProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionCityProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }

  public XLSXSimpleSheetProcessor createLocationTypeSheetProcessor() {
    SectionLocationTypeProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionLocationTypeProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }

  public XLSXSimpleSheetProcessor createLocationSheetProcessor() {
    SectionLocationProcessor sectionProcessor =
      context.getAutowireCapableBeanFactory().createBean(SectionLocationProcessor.class);
    sectionProcessor.initDefaultStorePlugin();
    return new XLSXSimpleSheetProcessor(sectionProcessor);
  }
}