package training.java.settings.unit.converter;

import lombok.Getter;

public enum VolumeUnit {
  
  CUBIC_MILLIMETER("mm3", VolumeUnit.CUBIC_MILLIMETER_SCALE),
  CUBIC_CENTIMETER("cm3", VolumeUnit.CUBIC_CENTIMETER_SCALE),
  LITER("l", VolumeUnit.LITER_SCALE, "dm3"),
  CUBIC_METER("m3", VolumeUnit.CUBIC_METER_SCALE, "cbm"),
  CUBIC_KILOMETER("km3", VolumeUnit.CUBIC_KILOMETER_SCALE);
  
  private static final double CUBIC_MILLIMETER_SCALE = 1;
  private static final double CUBIC_CENTIMETER_SCALE = 1000 * CUBIC_MILLIMETER_SCALE;
  private static final double LITER_SCALE = 1000 * CUBIC_CENTIMETER_SCALE;
  private static final double CUBIC_METER_SCALE = 1000 * LITER_SCALE;
  private static final double CUBIC_KILOMETER_SCALE = 1000000000 * CUBIC_METER_SCALE;
  
  @Getter
  private final String unit;
  
  @Getter
  private final double scale;
  
  @Getter
  private String[] alias;
  
  private VolumeUnit(String unit, double scale, String ... alias) {
    this.unit = unit;
    this.scale = scale;
    this.alias = alias;
  }
  
  public static VolumeUnit fromString(String unit) {
    String u = unit.toLowerCase();
    for (VolumeUnit distanceUnit : VolumeUnit.values()) {
      if (distanceUnit.unit.equals(u)) {
        return distanceUnit;
      }
      String[] unitAlias = distanceUnit.getAlias();
      if(unitAlias != null && unitAlias.length > 0) {
        for(String alias : unitAlias) {
          if(alias.equals(u)) {
            return distanceUnit;
          }
        }
      }
    }
    throw new IllegalArgumentException("No VolumeUnit with " + unit + " found!");
  }
  
  public double toCubicMillimeter(double amount) {
    return VolumeUnit.convert(amount, this, CUBIC_MILLIMETER);
  }
  
  public double toCubicCentimeter(double amount) {
    return VolumeUnit.convert(amount, this, CUBIC_CENTIMETER);
  }
  
  public double toLiter(double amount) {
    return VolumeUnit.convert(amount, this, LITER);
  }
  
  public double toCubicMeter(double amount) {
    return VolumeUnit.convert(amount, this, CUBIC_METER);
  }
  
  public double toCubicKilometer(double amount) {
    return VolumeUnit.convert(amount, this, CUBIC_KILOMETER);
  }
  
  public double convert(double amount, VolumeUnit toUnit) {
    return switch(toUnit) {
      case CUBIC_MILLIMETER -> this.toCubicMillimeter(amount);
      case CUBIC_CENTIMETER -> this.toCubicCentimeter(amount);
      case LITER -> this.toLiter(amount);
      case CUBIC_METER -> this.toCubicMeter(amount);
      case CUBIC_KILOMETER -> this.toCubicKilometer(amount);
      default -> convert(amount, this, toUnit);
    };
  }
  
  public static double convert(double amount, VolumeUnit from, VolumeUnit to) {
    if(from.equals(to)) return amount;
    return amount * (from.getScale() / to.getScale());
  }
  
  public static double convert(double amount, String fromUnit, String toUnit) {
    VolumeUnit from = VolumeUnit.fromString(fromUnit);
    VolumeUnit to = VolumeUnit.fromString(toUnit);
    return VolumeUnit.convert(amount, from, to);
  }
}
