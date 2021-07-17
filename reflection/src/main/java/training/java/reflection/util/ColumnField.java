package training.java.reflection.util;

import training.java.reflection.annotation.Column;

import java.lang.reflect.Field;

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
