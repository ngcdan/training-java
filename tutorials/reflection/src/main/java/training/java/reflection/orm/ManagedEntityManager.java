package training.java.reflection.orm;

import lombok.Getter;
import training.java.reflection.annotation.Inject;
import training.java.reflection.util.ColumnField;
import training.java.reflection.util.MetaModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public class ManagedEntityManager<T> implements EntityManager<T> {
  private AtomicLong idGenerator = new AtomicLong(0L);
  
  @Inject private Connection connection;
  
  @Override
  public void persist(T t) throws SQLException, IllegalAccessException {
    MetaModel metaModel = MetaModel.of(t.getClass());
    String sql = metaModel.buildInsertRequest();
    System.out.println(sql);
    
    try (PreparedStatement statement = prepareStatementWith(sql).addParameters(t); ) {
      statement.executeUpdate();
    }
  }
  
  @Override
  public T getOne(Class<T> clss, Object primaryKey)
    throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    
    MetaModel metaModel = MetaModel.of(clss);
    String sql = metaModel.buildSelectOneRequest();
    System.out.println(sql);
    try (PreparedStatement statement = prepareStatementWith(sql).addPrimaryKey(primaryKey);
         ResultSet resultSet = statement.executeQuery(); ) {
      return buildInstance(clss, resultSet);
    }
  }
  
  @Override
  public void createTable(Class<T> clss) throws SQLException {
    MetaModel metaModel = MetaModel.of(clss);
    String sql = metaModel.buildCreateTableRequest();
    System.out.println(sql);
    try (PreparedStatement statement = prepareStatementWith(sql).getStatement()) {
      statement.executeUpdate();
    }
  }
  
  private T buildInstance(Class<T> clss, ResultSet resultSet)
    throws NoSuchMethodException, InvocationTargetException, InstantiationException,
    IllegalAccessException, SQLException {
    
    MetaModel metaModel = MetaModel.of(clss);
    T t = clss.getConstructor().newInstance();
    Field primaryKeyField = metaModel.getPrimaryKey().getField();
    String primaryKeyColumnName = metaModel.getPrimaryKey().getName();
    Class<?> primaryKeyType = metaModel.getPrimaryKey().getType();
    
    resultSet.next();
    if (primaryKeyType == Long.class) {
      Long primaryKey = resultSet.getLong(primaryKeyColumnName);
      primaryKeyField.setAccessible(true);
      primaryKeyField.set(t, primaryKey);
    }
    
    for (ColumnField columnField : metaModel.getColumns()) {
      Field field = columnField.getField();
      field.setAccessible(true);
      String columnFieldName = columnField.getName();
      Class<?> columnType = columnField.getType();
      if (columnType == int.class) {
        int value = resultSet.getInt(columnFieldName);
        field.set(t, value);
      } else if (columnType == String.class) {
        String value = resultSet.getString(columnFieldName);
        field.set(t, value);
      }
    }
    
    return t;
  }
  
  private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(sql);
    return new PreparedStatementWrapper(statement);
  }
  
  class PreparedStatementWrapper {
    
    @Getter private PreparedStatement statement;
    
    public PreparedStatementWrapper(PreparedStatement statement) {
      this.statement = statement;
    }
    
    public PreparedStatement addParameters(T t) throws SQLException, IllegalAccessException {
      MetaModel metaModel = MetaModel.of(t.getClass());
      Class<?> primaryKeyType = metaModel.getPrimaryKey().getType();
      if (primaryKeyType == Long.class) {
        long id = idGenerator.incrementAndGet();
        statement.setLong(1, id);
        Field field = metaModel.getPrimaryKey().getField();
        field.setAccessible(true);
        field.set(t, id);
      }
      
      for (int columnIndex = 0; columnIndex < metaModel.getColumns().size(); columnIndex++) {
        ColumnField columnField = metaModel.getColumns().get(columnIndex);
        Class<?> fieldType = columnField.getType();
        Field field = columnField.getField();
        field.setAccessible(true);
        Object value = field.get(t);
        if (fieldType == int.class) {
          statement.setInt(columnIndex + 2, (int) value);
        } else if (fieldType == String.class) {
          statement.setString(columnIndex + 2, (String) value);
        }
      }
      return statement;
    }
    
    public PreparedStatement addPrimaryKey(Object primaryKey) throws SQLException {
      if (primaryKey.getClass() == Long.class) {
        statement.setLong(1, (Long) primaryKey);
      }
      return statement;
    }
  }
}
