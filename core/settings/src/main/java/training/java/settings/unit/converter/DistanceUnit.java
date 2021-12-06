package training.java.settings.unit.converter;

import lombok.Getter;

public enum DistanceUnit {
  MM("mm", DistanceUnit.MILLIMETER_SCALE), CM("cm", DistanceUnit.CENTIMETER_SCALE), DM("dm",
    DistanceUnit.DECIMETER_SCALE), M("m", DistanceUnit.METER_SCALE), KM("km", DistanceUnit.KILOMETER_SCALE), INCH(
    "inch", DistanceUnit.METER_SCALE, "in");

  private static final double MILLIMETER_SCALE = 1;
  private static final double CENTIMETER_SCALE = 10 * MILLIMETER_SCALE;
  private static final double DECIMETER_SCALE = 10 * CENTIMETER_SCALE;
  private static final double METER_SCALE = 10 * DECIMETER_SCALE;
  private static final double KILOMETER_SCALE = 1000 * METER_SCALE;
  private static final double INCH_SCALE = 0.0254 * METER_SCALE;

  @Getter
  private final String unit;

  @Getter
  private final double scale;

  @Getter
  private String[] alias;

  private DistanceUnit(String unit, double scale, String... alias) {
    this.unit = unit;
    this.scale = scale;
    this.alias = alias;
  }

  public static DistanceUnit fromString(String unit) {
    String u = unit.toLowerCase();
    for(DistanceUnit distanceUnit : DistanceUnit.values()) {
      if(distanceUnit.unit.equals(u)) {
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
    throw new IllegalArgumentException("No DistanceUnit with " + unit + " found!");
  }

  public double toInch(double amount) {
    return DistanceUnit.convert(amount, this, INCH);
  }

  public double toKilometer(double amount) {
    return DistanceUnit.convert(amount, this, KM);
  }

  public double toMeter(double amount) {
    return DistanceUnit.convert(amount, this, M);
  }

  public double toDecimeter(double amount) {
    return DistanceUnit.convert(amount, this, DM);
  }

  public double toCentimeter(double amount) {
    return DistanceUnit.convert(amount, this, CM);
  }

  public double toMillimeter(double amount) {
    return DistanceUnit.convert(amount, this, MM);
  }

  public double convert(double amount, DistanceUnit toUnit) {
    return switch(toUnit) {
      case MM -> this.toMillimeter(amount);
      case CM -> this.toCentimeter(amount);
      case DM -> this.toDecimeter(amount);
      case KM -> this.toKilometer(amount);
      case INCH -> this.toInch(amount);
      default -> convert(amount, this, toUnit);
    };
  }

  public static double convert(double amount, DistanceUnit from, DistanceUnit to) {
    if(from.equals(to)) {
      return amount;
    }
    return amount * (from.getScale() / to.getScale());
  }

  public static double convert(double amount, String fromUnit, String toUnit) {
    if(fromUnit.equals(toUnit)) {
      return amount;
    }
    DistanceUnit from = DistanceUnit.fromString(fromUnit);
    DistanceUnit to = DistanceUnit.fromString(toUnit);
    return amount * (from.getScale() / to.getScale());
  }
}