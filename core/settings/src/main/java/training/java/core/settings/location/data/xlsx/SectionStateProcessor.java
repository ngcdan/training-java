package training.java.core.settings.location.data.xlsx;

import org.springframework.beans.factory.annotation.Autowired;
import training.java.core.settings.location.LocationService;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.entity.State;
import training.java.xlsx.*;

import java.util.Objects;

public class SectionStateProcessor extends XLSXSectionProcessor<State> {
  @Autowired
  protected LocationService service;

  protected XSLXCell<State> CODE, COUNTRY_CODE;

  public SectionStateProcessor() {
    CODE = new XSLXCell<State>("Code", true).mapper((ctx, row, state, cell) -> {
      state.setCode(cell);
      state.setStateCode(cell);
    });

    COUNTRY_CODE = new XSLXCell<State>("Country Code", true).mapper((ctx, row, state, cell) -> {
      Country country = service.getCountry(cell);
      if(Objects.nonNull(country)) {
        state.setCountryCode(country.getCode());
      }
    });

    add(CODE);
    add(COUNTRY_CODE);
    add(new XSLXCell.TextField<>("Label", "label", true));
    add(new XSLXCell.TextField<>("Description", "description", false));
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    entity = new State();
    mapRow(ctx, row, entity);
  }

  protected void onStartSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {
    newCollector();
  }

  protected State saveOrUpdate(State state) {
    return service.getStateLogic().saveState(state);
  }

  public void initDefaultStorePlugin() {
    setPlugin(new DefaultStorePlugin());
  }

  private class DefaultStorePlugin extends IXLSXSectionProcessorPlugin {
    @Override
    public void onProcessRow(IXLSXSheetProcessor sheetProcessor, SectionContext ctx, XLSXRow row, XLSXProcessMode mode) throws Exception {
      State state = new State();
      mapRow(ctx, row, state);
      state = saveOrUpdate(state);
    }
  }
}