package training.java.reflection.util;

import java.lang.reflect.Field;
import training.java.reflection.annotation.PrimaryKey;

public class PrimaryKeyField {

  private Field field;
  private PrimaryKey primaryKey;

  public PrimaryKeyField(Field field) {
    this.field = field;
    this.primaryKey = this.field.getAnnotation(PrimaryKey.class);
  }

  public Class<?> getType() {
    return field.getType();
  }

  public String getName() {
    return primaryKey.name();
  }

  public Field getField() {
    return this.field;
  }
}