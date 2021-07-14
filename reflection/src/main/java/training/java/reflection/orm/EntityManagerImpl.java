package training.java.reflection.orm;

import training.java.reflection.util.ColumnField;
import training.java.reflection.util.MetaModel;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
  }
  
}
