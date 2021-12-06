package training.java.settings.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import training.java.settings.unit.converter.DistanceUnit;
import training.java.settings.unit.converter.MassUnit;
import training.java.settings.unit.converter.VolumeUnit;

public class UnitConverterUnitTest {

  @Test
  @Tag("unit")
  public void testMassUnit() {
    assertEquals(1, MassUnit.convert(1, "g", "g"));
    assertEquals(1, MassUnit.convert(1000, "g", "kgs"));
    assertEquals(1, MassUnit.convert(454, "g", "lbs"));
    assertEquals(1, MassUnit.convert(0.2, "g", "car"));
    assertEquals(1, MassUnit.convert(28.350, "g", "oz"));
    assertEquals(1, MassUnit.convert(1000, "g", "kg"));
    assertEquals(1, MassUnit.convert(1000000, "g", "ton"));
    assertEquals(1, MassUnit.convert(907184.74, "g", "stn"));
    assertEquals(1, MassUnit.convert(1016046.9088, "g", "ltn"));

    assertEquals(1, MassUnit.GRAM.convert(1, MassUnit.GRAM));
    assertEquals(1, MassUnit.GRAM.toGram(1));
    assertEquals(1, MassUnit.GRAM.convert(1000, MassUnit.KILOGRAM));
    assertEquals(1, MassUnit.GRAM.toKilogram(1000));
    assertEquals(1, MassUnit.GRAM.convert(1000000, MassUnit.TONE));
    assertEquals(1, MassUnit.GRAM.toTone(1000000));
    assertEquals(1, MassUnit.KILOGRAM.convert(0.001, MassUnit.GRAM));
    assertEquals(1, MassUnit.KILOGRAM.convert(1, MassUnit.KILOGRAM));
    assertEquals(1, MassUnit.KILOGRAM.convert(1000, MassUnit.TONE));
    assertEquals(1000000, MassUnit.TONE.convert(1, MassUnit.GRAM));
    assertEquals(1000, MassUnit.TONE.convert(1, MassUnit.KILOGRAM));
    assertEquals(1, MassUnit.TONE.convert(1, MassUnit.TONE));

    assertEquals(454, MassUnit.POUND.convert(1, MassUnit.GRAM));
    assertEquals(0.454, MassUnit.POUND.convert(1, MassUnit.KILOGRAM));
    assertEquals(0.000454, MassUnit.POUND.convert(1, MassUnit.TONE));
  }

  @Test
  @Tag("unit")
  public void testVolumeUnit() {
    assertEquals(1, VolumeUnit.convert(1, "mm3", "mm3"));
    assertEquals(1, VolumeUnit.convert(1000, "mm3", "cm3"));
    assertEquals(1, VolumeUnit.convert(1000000, "mm3", "l"));
    assertEquals(1, VolumeUnit.convert(1000, "l", "m3"));
    assertEquals(1, VolumeUnit.convert(1000000000, "m3", "km3"));
  }

  @Test
  @Tag("unit")
  public void testDistanceUnit() {
    assertEquals(1, DistanceUnit.MM.toMillimeter(1));
    assertEquals(1, DistanceUnit.MM.toCentimeter(10));
    assertEquals(1, DistanceUnit.MM.toDecimeter(100));
    assertEquals(1, DistanceUnit.MM.toMeter(1000));
    assertEquals(1, DistanceUnit.MM.toKilometer(1000000));
    assertEquals(1, DistanceUnit.M.toKilometer(1000));

    assertEquals(1, DistanceUnit.convert(1000, DistanceUnit.M, DistanceUnit.KM));

    assertEquals(1, DistanceUnit.M.convert(1000, DistanceUnit.KM));

    assertEquals(1, DistanceUnit.convert(1, "mm", "mm"));
    assertEquals(1, DistanceUnit.convert(10, "mm", "cm"));
    assertEquals(1, DistanceUnit.convert(100, "mm", "dm"));
    assertEquals(1, DistanceUnit.convert(1000, "mm", "M"));
    assertEquals(1, DistanceUnit.convert(1000000, "mm", "KM"));

    assertEquals(1, DistanceUnit.convert(1000, "m", "km"));
  }
}