package training.java.reflection.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.reflection.annotation.Column;
import training.java.reflection.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
public class MetaModel<T> {
  
  private Class<T> clss;
  
  public MetaModel(Class<T> clss) {
    this.clss = clss;
  }
  
  public static <T> MetaModel<T> of(Class<T> clss) {
    return new MetaModel<>(clss);
  }
  
  public PrimaryKeyField getPrimaryKey() {
    Field[] declaredFields = clss.getDeclaredFields();
    for(Field field : declaredFields) {
      PrimaryKey primaryKeyField = field.getAnnotation(PrimaryKey.class);
      if(primaryKeyField != null) {
        return new PrimaryKeyField(field);
      }
    }
    
    throw new IllegalArgumentException("No primary key found in class " + clss.getSimpleName());
  }
  
  public List<ColumnField> getColumns() {
    List<ColumnField> columns = new ArrayList<>();
    Field[] declaredFields = clss.getDeclaredFields();
    for(Field field : declaredFields) {
      Column column = field.getAnnotation(Column.class);
      if(column != null) {
        columns.add(new ColumnField(field));
      }
    }
    return columns;
  }
}
