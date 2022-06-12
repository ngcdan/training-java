package training.java.core.settings.location.data.xlsx;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import training.java.core.settings.location.LocationService;
import training.java.core.settings.location.entity.Location;
import training.java.core.settings.location.entity.LocationAlias;
import training.java.core.settings.location.entity.LocationType;
import training.java.xlsx.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SectionLocationProcessor extends XLSXSectionProcessor<Location> {

  @Autowired
  private LocationService service;

  protected XSLXCell<Location> CODE, LOCATION_TYPE, AIR, SEA, COUNTRY_CODE;
  protected XSLXCell<Location> UNLOCODE, UNLOCODE_3L, US_CODE, CAN_CODE, AUS_CODE, IATA_CODE;

  public SectionLocationProcessor() {
    CODE = new XSLXCell.TextField<>("Code", "code", true);
    AIR = new XSLXCell<Location>("Air", false).mapper((ctx, row, location, cell) -> {});
    SEA = new XSLXCell<Location>("Sea", false).mapper((ctx, row, location, cell) -> {});
    COUNTRY_CODE = new XSLXCell.TextField<>("Country Code", "countryCode", false);

    LOCATION_TYPE = new XSLXCell<Location>("Location Type", false).mapper((ctx, row, location, cell) -> {
      if(StringUtils.isNotEmpty(cell)) {
        String[] types  = cell.replace(" ", "").split(",");
        List<LocationType> listLocationType = new ArrayList<>();
        for (String type : types) {
          LocationType locationType = service.getLocationType(type);
          if(Objects.isNull(locationType)) {
            throw new RuntimeException("Cannot find location type : " + type);
          }
          listLocationType.add(locationType);
        }
        location.setLocationTypes(listLocationType);
      } else {
        List<LocationType> typeInDB = service.getLocationTypeLogic().findAll();
        List<LocationType> types = new ArrayList<>();

        if (StringUtils.isNotEmpty(row.getCellAsString(AIR.header))) {
          types =
            typeInDB
              .stream()
              .filter(locationType -> !locationType.getType().equals("SEA"))
              .collect(Collectors.toList());
        }
        if(StringUtils.isNotEmpty(row.getCellAsString(SEA.header))) {
          types =
            typeInDB
              .stream()
              .filter(locationType -> !locationType.getType().equals("AIR"))
              .collect(Collectors.toList());
        }

        location.setLocationTypes(types);
      }
    });

    UNLOCODE = new XSLXCell<Location>("UNLOCODE", false).mapper((ctx, row, location, cell) -> {
      if (StringUtils.isNotEmpty(cell)) {
        location.getAliases().add(new LocationAlias(cell, "UNLOCODE", cell));
      }
    });

    UNLOCODE_3L = new XSLXCell<Location>("UNLOCODE (3L)", false).mapper((ctx, row, location, cell) -> {
      if (StringUtils.isNotEmpty(cell)) {
        location.getAliases().add(new LocationAlias(cell, "UNLOCODE (3L)", cell));
      }
    });

    US_CODE = new XSLXCell<Location>("US Code", false).mapper((ctx, row, location, cell) -> {
      if (StringUtils.isNotEmpty(cell)) {
        location.getAliases().add(new LocationAlias(cell, "US Code", cell));
      }
    });

    CAN_CODE = new XSLXCell<Location>("Can Code", false).mapper((ctx, row, location, cell) -> {
      if (StringUtils.isNotEmpty(cell)) {
        location.getAliases().add(new LocationAlias(cell, "Can Code", cell));
      }
    });

    AUS_CODE = new XSLXCell<Location>("Aus Code", false).mapper((ctx, row, location, cell) -> {
      if (StringUtils.isNotEmpty(cell)) {
        location.getAliases().add(new LocationAlias(cell, "Aus Code", cell));
      }
    });
    IATA_CODE = new XSLXCell<Location>("IATA Code", false).mapper((ctx, row, location, cell) -> {
      if (StringUtils.isNotEmpty(cell)) {
        location.getAliases().add(new LocationAlias(cell, "IATA Code", cell));
      }
    });

    add(CODE);
    add(LOCATION_TYPE);
    add(UNLOCODE);
    add(UNLOCODE_3L);
    add(US_CODE);
    add(CAN_CODE);
    add(AUS_CODE);
    add(IATA_CODE);
    add(new XSLXCell.TextField<>("Label", "label", true));
    add(new XSLXCell.TextField<>("Label 2", "otherLabel", false));
    add(new XSLXCell.TextField<>("Address", "address", false));
    add(new XSLXCell.TextField<>("District", "district", false));
    add(new XSLXCell.TextField<>("City Code", "cityCode", false));
    add(new XSLXCell.TextField<>("State Code", "stateCode", false));
    add(COUNTRY_CODE);
    add(new XSLXCell.TextField<>("Postal Code", "postalCode", false));
  }

  public void mapRow(SectionContext ctx, XLSXRow row) throws Exception {
    entity = new Location();
    mapRow(ctx, row, entity);
  }

  protected void onStartSection(IXLSXSheetProcessor sheetProcessor, SectionContext ctx) throws Exception {
    newCollector();
  }

  protected Location saveOrUpdate(Location location) {
    return service.getLocationLogic().saveLocation(location);
  }

  public void initDefaultStorePlugin() {
    setPlugin(new DefaultStorePlugin());
  }

  private class DefaultStorePlugin extends IXLSXSectionProcessorPlugin {
    @Override
    public void onProcessRow(IXLSXSheetProcessor sheetProcessor, SectionContext ctx, XLSXRow row, XLSXProcessMode mode) throws Exception {
      Location location = new Location();
      mapRow(ctx, row, location);
      location = saveOrUpdate(location);
    }
  }
}