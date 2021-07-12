package training.java.reflection.orm;

public interface EntityManager<T> {
  static <T> EntityManager<T> of(Class<T> clss) {
    return new EntityManagerImpl<>();
  }
  
  void persist(T t);
}
