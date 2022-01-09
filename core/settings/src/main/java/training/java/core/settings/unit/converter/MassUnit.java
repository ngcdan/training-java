package training.java.core.settings.unit.converter;

import lombok.Getter;

public enum MassUnit {
  GRAM("g", 1), POUND("lbs", 454), POUNDS("lbr", 454), CARAT("car", 0.2, "ct"), OUNCE("oz", 28.350, "onz"), KILOGRAM(
    "kg", 1000, "kgm", "kgs"), TONE("ton", 1000000, "t", "tne"), SHORT_TON("stn", 907184.74), LONG_TON("ltn",
    1016046.9088);

  @Getter
  private final String unit;

  @Getter
  private final double scale;

  @Getter
  private String[] alias;

  private MassUnit(String unit, double scale, String... alias) {
    this.unit = unit;
    this.scale = scale;
    this.alias = alias;
  }

  public static MassUnit fromString(String unit) {
    String u = unit.toLowerCase();
    for(MassUnit distanceUnit : MassUnit.values()) {
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
    throw new IllegalArgumentException("No MassUnit with " + unit + " found!");
  }

  public double toGram(double amount) {
    return MassUnit.convert(amount, this, GRAM);
  }

  public double toPound(double amount) {
    return MassUnit.convert(amount, this, POUND);
  }

  public double toPounds(double amount) {
    return MassUnit.convert(amount, this, POUNDS);
  }

  public double toOunce(double amount) {
    return MassUnit.convert(amount, this, OUNCE);
  }

  public double toKilogram(double amount) {
    return MassUnit.convert(amount, this, KILOGRAM);
  }

  public double toTone(double amount) {
    return MassUnit.convert(amount, this, TONE);
  }

  public double toShortTone(double amount) {
    return MassUnit.convert(amount, this, SHORT_TON);
  }

  public double toLongTone(double amount) {
    return MassUnit.convert(amount, this, LONG_TON);
  }

  public double convert(double amount, MassUnit toUnit) {
    return switch(toUnit) {
      case GRAM -> this.toGram(amount);
      case POUND -> this.toPound(amount);
      case POUNDS -> this.toPounds(amount);
      case OUNCE -> this.toOunce(amount);
      case KILOGRAM -> this.toKilogram(amount);
      case TONE -> this.toTone(amount);
      case SHORT_TON -> this.toShortTone(amount);
      case LONG_TON -> this.toLongTone(amount);
      default -> convert(amount, this, toUnit);
    };
  }

  public static double convert(double amount, MassUnit from, MassUnit to) {
    if(from.equals(to)) {
      return amount;
    }
    return amount * (from.getScale() / to.getScale());
  }

  public static double convert(double amount, String fromUnit, String toUnit) {
    MassUnit from = MassUnit.fromString(fromUnit);
    MassUnit to = MassUnit.fromString(toUnit);
    return MassUnit.convert(amount, from, to);
  }
}