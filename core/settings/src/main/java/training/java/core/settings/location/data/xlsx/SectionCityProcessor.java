package training.java.core.settings.location.data.xlsx;


import org.springframework.beans.factory.annotation.Autowired;
import training.java.core.settings.location.LocationService;
import training.java.core.settings.location.entity.City;
import training.java.core.settings.location.entity.Country;
import training.java.xlsx.*;

import java.util.Objects;

public class SectionCityProcessor extends XLSXSectionProcessor<City> {

  @Autowired
  private LocationService service;

  public SectionCityProcessor() {
    add(new XSLXCell.TextField<>("Code", "code", true));
    add(new XSLXCell<City>("Country Code", true).mapper((ctx, row, city, cell) -> {
      final Country country = service.getCountry(cell);
      if(Objects.isNull(country)) {
        throw new RuntimeException("Cannot find country: " + cell);
      }
      city.setCountryCode(country.getCode());
    }));
    add(new XSLXCell<City>("State Code", false).mapper((ctx, row, city, cell) -> {
      city.setStateCode(cell);
    }));
    add(new XSLXCell.TextField<>("Label", "label", true));
    add(new XSLXCell.TextField<>("Description", "description", false));
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    entity = new City();
    mapRow(ctx, row, entity);
  }

  protected void onStartSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {
    newCollector();
  }

  protected City saveOrUpdate(City city) {
    return service.getCityLogic().saveCity(city);
  }

  public void initDefaultStorePlugin() {
    setPlugin(new DefaultStorePlugin());
  }

  private class DefaultStorePlugin extends IXLSXSectionProcessorPlugin {
    @Override
    public void onProcessRow(IXLSXSheetProcessor sheetProcessor, SectionContext ctx, XLSXRow row, XLSXProcessMode mode) throws Exception {
      City city = new City();
      mapRow(ctx, row, city);
      saveOrUpdate(city);
    }
  }
}