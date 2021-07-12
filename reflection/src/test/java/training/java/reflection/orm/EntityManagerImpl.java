package training.java.reflection.orm;

import training.java.reflection.util.MetaModel;

public class EntityManagerImpl<T> implements EntityManager<T> {
  @Override
  public void persist(T t) {
    MetaModel metaModel = MetaModel.of(t.getClass());
    String sql = metaModel.buildInsertRequest();
    System.out.println(sql);
    
    /*
    PreparedStatement statement = prepareStatementWith(sql).addParameters(t);
    statement.executeUpdate();
    
     */
  
  }
}
