package training.java.reflection.orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager<T> {
  static <T> EntityManager<T> of(Class<T> clss) {
    return new H2EntityManager<>();
  }
  
  void createTable(Class<T> clss) throws SQLException;
  
  void persist(T t) throws SQLException, IllegalAccessException;
  
  T getOne(Class<T> clss, Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
