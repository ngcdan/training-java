package training.java.reflection.util;

import java.lang.reflect.Field;

public class PrimaryKeyField {
  private Field field;
  
  public PrimaryKeyField(Field field) {
    this.field = field;
  }
  
  public Class<?> getType() {
    return field.getType();
  }
  
  public String getName() {
    return field.getName();
  }
  
  public Field getField() {
    return this.field;
  }
}
