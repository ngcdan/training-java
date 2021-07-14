package training.java.reflection;

import org.junit.jupiter.api.Test;
import training.java.reflection.entity.Person;
import training.java.reflection.orm.EntityManager;

public class ObjectRelationalUnitTest {
  
  @Test
  public void writingObject() throws Exception {
    EntityManager<Person> entityManager = EntityManager.of(Person.class);
    Person dan = new Person("Dan", 23);
    Person duc = new Person("Duc", 21);
    Person nhat = new Person("Nhat", 22);
    
    entityManager.persist(dan);
    entityManager.persist(duc);
    entityManager.persist(nhat);
  }
}
