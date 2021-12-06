package training.java.reflection.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.reflection.annotation.Column;
import training.java.reflection.annotation.PrimaryKey;

@Setter
@Getter
@NoArgsConstructor
public class MetaModel {

  private Class<?> clss;

  public MetaModel(Class<?> clss) {
    this.clss = clss;
  }

  public static MetaModel of(Class<?> clss) {
    return new MetaModel(clss);
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

  public String buildCreateTableRequest() {
    // create table Person ( test_id int primary key, test_name varchar(40), test_age int)
    return "CREATE TABLE " + this.clss.getSimpleName() + " ( " + buildPrimaryKeyColumn() + " , " + buildFieldColumn()
      + " )";
  }

  private String buildFieldColumn() {
    List<ColumnField> columns = getColumns();
    StringBuilder buildFieldColumn = new StringBuilder();
    for(int i = 0; i < columns.size(); i++) {
      ColumnField columnField = columns.get(i);
      String columnType = null;
      Class<?> columnFieldType = columnField.getType();
      if(columnFieldType == String.class) {
        columnType = "VARCHAR(255)";
      } else if(columnFieldType == int.class) {
        columnType = "INT";
      }
      // TODO: handle diff column type
      buildFieldColumn.append(columnField.getName()).append(" ").append(columnType);
      if(i < columns.size() - 1) {
        buildFieldColumn.append(", ");
      }
    }

    return buildFieldColumn.toString();
  }

  public String buildSelectOneRequest() {
    // select id, name, age from person where id = ?
    String columnElement = buildColumnElement();
    return "SELECT " + columnElement + " FROM " + this.clss.getSimpleName() + " WHERE " + getPrimaryKey().getName()
      + "= ?";
  }

  public String buildInsertRequest() {
    // insert into Person (id, name, age) value(?, ?, ?)
    String columnElement = buildColumnElement();
    String questionMarksElement = buildQuestionMarksElement();
    return "INSERT INTO " + this.clss.getSimpleName() + " (" + columnElement + ") VALUES (" + questionMarksElement
      + ")";
  }

  private String buildPrimaryKeyColumn() {
    PrimaryKeyField primaryKey = getPrimaryKey();
    String primaryKeyColumnName = primaryKey.getName();
    Class<?> primaryKeyType = primaryKey.getType();
    String primaryKeyColumnType = null;
    if(primaryKeyType == Long.class) {
      primaryKeyColumnType = "INT";
    }
    return primaryKeyColumnName + " " + primaryKeyColumnType + " primary key";
  }

  private String buildQuestionMarksElement() {
    int numberOfColumns = getColumns().size() + 1;
    return IntStream.range(0, numberOfColumns).mapToObj(index -> "?").collect(Collectors.joining(","));
  }

  private String buildColumnElement() {
    String primaryKeyColumnName = getPrimaryKey().getName();
    List<String> columnNames = getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
    columnNames.add(0, primaryKeyColumnName);
    return String.join(", ", columnNames);
  }
}