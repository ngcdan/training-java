package training.java.core.settings.location.data.xlsx;

import org.springframework.beans.factory.annotation.Autowired;
import training.java.core.settings.location.LocationService;
import training.java.core.settings.location.entity.LocationType;
import training.java.xlsx.*;

public class SectionLocationTypeProcessor extends XLSXSectionProcessor<LocationType> {

  @Autowired
  protected LocationService service;

  protected XSLXCell<LocationType> CODE, TYPE;

  public SectionLocationTypeProcessor() {
    CODE = new XSLXCell.TextField<>("Label", "label", true);
    TYPE = new XSLXCell.TextField<>("Type", "type", true);

    add(CODE);
    add(TYPE);
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    entity = new LocationType();
    mapRow(ctx, row, entity);
  }

  protected void onStartSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {
    newCollector();
  }

  protected LocationType saveOrUpdate(LocationType type) {
    return service.getLocationTypeLogic().save(type);
  }

  public void initDefaultStorePlugin() {
    setPlugin(new DefaultStorePlugin());
  }

  private class DefaultStorePlugin extends IXLSXSectionProcessorPlugin {
    @Override
    public void onProcessRow(IXLSXSheetProcessor sheetProcessor, SectionContext ctx, XLSXRow row, XLSXProcessMode mode) throws Exception {
      LocationType type = new LocationType();
      mapRow(ctx, row, type);
      saveOrUpdate(type);
    }
  }
}