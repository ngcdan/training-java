package training.java.core.settings.location.data.xlsx;

import org.springframework.beans.factory.annotation.Autowired;
import training.java.core.settings.location.LocationService;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.entity.CountryGroup;
import training.java.xlsx.*;

import java.util.Objects;

public class SectionCountryProcessor extends XLSXSectionProcessor<Country> {

  @Autowired
  private LocationService service;

  private final XSLXCell<Country> CODE, GROUP_NAME;

  public SectionCountryProcessor() {
    CODE = new XSLXCell.TextField<>("Code", "code", true);
    GROUP_NAME = new XSLXCell<Country>("Group Name", false).mapper((ctx, row, country, cell) -> {});

    add(CODE);
    add(GROUP_NAME);
    add(new XSLXCell.TextField<>("Label", "label", true));
    add(new XSLXCell.TextField<>("Label 2", "label2", false));
    add(new XSLXCell.TextField<>("Phone Code", "phoneCode", false));
    add(new XSLXCell.TextField<>("Currency", "currency", false));
    add(new XSLXCell.TextField<>("Description", "description", false));
    add(new XSLXCell.TextField<>("Address Format", "addressFormat", false));
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    entity = new Country();
    mapRow(ctx, row, entity);
  }

  protected void onStartSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {
    newCollector();
  }

  private Country saveOrUpdate(XLSXRow row, Country country) {
    country = service.getCountryLogic().saveCountry(country);
    createCountryGroupRelation(row, country);
    return country;
  }

  private void createCountryGroupRelation(XLSXRow row, Country country) {
    String groupName = row.getCellAsString(GROUP_NAME.header);
    CountryGroup countryGroup = service.getCountryGroup(groupName);
    if(Objects.isNull(countryGroup)) return;
    service.createCountryGroupRelation(countryGroup, country);
  }

  public void initDefaultStorePlugin() {
    setPlugin(new DefaultStorePlugin());
  }

  private class DefaultStorePlugin extends IXLSXSectionProcessorPlugin {
    @Override
    public void onProcessRow(IXLSXSheetProcessor sheetProcessor, SectionContext ctx, XLSXRow row, XLSXProcessMode mode) throws Exception {
      Country country = new Country();
      mapRow(ctx, row, country);
      saveOrUpdate(row, country);
    }
  }
}