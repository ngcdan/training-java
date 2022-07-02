package training.java.core.settings.location.data.xlsx;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import training.java.core.settings.location.CountryGroupLogic;
import training.java.core.settings.location.entity.CountryGroup;
import training.java.xlsx.*;

import java.util.Objects;

public class SectionCountryGroupProcessor  extends XLSXSectionProcessor<CountryGroup> {

  @Autowired
  protected CountryGroupLogic groupLogic;

  protected XSLXCell<CountryGroup> LABEL, NAME, PARENT_NAME;

  public SectionCountryGroupProcessor() {
    LABEL = new XSLXCell.TextField<>("Label", "label", true);
    NAME = new XSLXCell.TextField<>("Name", "name", true);
    PARENT_NAME = new XSLXCell<CountryGroup>("Parent Name", false).mapper((ctx, row, charge, cell) -> {
      if(StringUtils.isEmpty(cell)) return;
      CountryGroup countryGroup = groupLogic.getCountryGroup(cell);
      if(Objects.isNull(countryGroup)) return;
      charge.withParent(countryGroup);
    });

    add(LABEL);
    add(NAME);
    add(PARENT_NAME);
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    entity = new CountryGroup();
    mapRow(ctx, row, entity);
  }

  public void initDefaultStorePlugin() {
    setPlugin(new DefaultStorePlugin());
  }

  private class DefaultStorePlugin extends IXLSXSectionProcessorPlugin {
    @Override
    public void onProcessRow(IXLSXSheetProcessor sheetProcessor, SectionContext ctx, XLSXRow row, XLSXProcessMode mode) {
      CountryGroup group = new CountryGroup();
      mapRow(ctx, row, group);
      groupLogic.saveCountryGroup(group);
    }
  }
}