package training.java.reflection.util;

import java.lang.reflect.Field;
import training.java.reflection.annotation.Column;

public class ColumnField {

  private Field field;
  private Column column;

  public ColumnField(Field field) {
    this.field = field;
    this.column = this.field.getAnnotation(Column.class);
  }

  public String getName() {
    return this.column.name();
  }

  public Class<?> getType() {
    return field.getType();
  }

  public Field getField() {
    return field;
  }
}