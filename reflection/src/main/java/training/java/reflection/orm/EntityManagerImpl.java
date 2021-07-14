package training.java.reflection.orm;

import training.java.reflection.util.ColumnField;
import training.java.reflection.util.MetaModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;

public class EntityManagerImpl<T> implements EntityManager<T> {
  AtomicLong idGenerator = new AtomicLong(0L);
  
  @Override
  public void persist(T t) throws SQLException, IllegalAccessException {
    MetaModel metaModel = MetaModel.of(t.getClass());
    String sql = metaModel.buildInsertRequest();
    System.out.println(sql);
    
    PreparedStatement statement = prepareStatementWith(sql).addParameters(t);
    statement.executeUpdate();
    
  }
  
  @Override
  public T getOne(Class<T> clss, Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    MetaModel metaModel = MetaModel.of(clss);
    String sql = metaModel.buildSelectOneRequest();
    System.out.println(sql);
    PreparedStatement statement = prepareStatementWith(sql).addPrimaryKey(primaryKey);
    ResultSet resultSet = statement.executeQuery();
    return buildInstance(clss, resultSet);
  }
  
  private T buildInstance(Class<T> clss, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
    MetaModel metaModel = MetaModel.of(clss);
    T t = clss.getConstructor().newInstance();
    Field primaryKeyField = metaModel.getPrimaryKey().getField();
    String primaryKeyColumnName = metaModel.getPrimaryKey().getName();
    Class<?> primaryKeyType = metaModel.getPrimaryKey().getType();
    
    resultSet.next();
    if(primaryKeyType == Long.class) {
      Long primaryKey = resultSet.getLong(primaryKeyColumnName);
      primaryKeyField.setAccessible(true);
      primaryKeyField.set(t, primaryKey);
    }
    
    for(ColumnField columnField : metaModel.getColumns()) {
      Field field = columnField.getField();
      field.setAccessible(true);
      String columnFieldName = columnField.getName();
      Class<?> columnType = columnField.getType();
      if(columnType == int.class) {
        int value = resultSet.getInt(columnFieldName);
        field.set(t, value);
      } else if(columnType == String.class) {
        String value = resultSet.getString(columnFieldName);
        field.set(t, value);
      }
    }
    
    return t;
  }
  
  private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
    Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    PreparedStatement statement = conn.prepareStatement(sql);
    return new PreparedStatementWrapper(statement);
  }
  
  class PreparedStatementWrapper {
    private PreparedStatement statement;
    
    public PreparedStatementWrapper(PreparedStatement statement) {
      this.statement = statement;
    }
    
    public PreparedStatement addParameters(T t) throws SQLException, IllegalAccessException {
      MetaModel metaModel = MetaModel.of(t.getClass());
      Class<?> primaryKeyType = metaModel.getPrimaryKey().getType();
      if(primaryKeyType == Long.class) {
        long id = idGenerator.incrementAndGet();
        statement.setLong(1, id);
        Field field = metaModel.getPrimaryKey().getField();
        field.setAccessible(true);
        field.set(t, id);
      }
      
      for(int columIndex = 0; columIndex < metaModel.getColumns().size(); columIndex++) {
        ColumnField columnField = metaModel.getColumns().get(columIndex);
        Class<?> fieldType = columnField.getType();
        Field field = columnField.getField();
        field.setAccessible(true);
        Object value = field.get(t);
        if(fieldType == int.class) {
          statement.setInt(columIndex + 2, (int) value);
        } else if(fieldType == String.class) {
          statement.setString(columIndex + 2, (String) value);
        }
      }
      return statement;
    }
    
    public PreparedStatement addPrimaryKey(Object primaryKey) throws SQLException {
      if(primaryKey.getClass() == Long.class) {
        statement.setLong(1, (Long) primaryKey);
      }
      return statement;
    }
  }
  
}
