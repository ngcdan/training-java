package training.java.reflection;

import org.h2.tools.Server;
import org.junit.jupiter.api.Test;
import training.java.reflection.beanmanager.BeanManager;
import training.java.reflection.entity.Person;
import training.java.reflection.orm.EntityManager;
import training.java.reflection.orm.ManagedEntityManager;

public class ObjectRelationalTest {

  @Test
  public void writingObject() throws Exception {
    Server.main("-ifNotExists");
    System.out.println("DB Launched!");

    BeanManager beanManager = BeanManager.getInstance();

    EntityManager<Person> entityManager = beanManager.getInstance(ManagedEntityManager.class);
    System.out.println("Create Table Person");
    entityManager.createTable(Person.class);

    System.out.println("Writing in DB...");
    Person dan = new Person("Dan", 23);
    Person duc = new Person("Duc", 21);
    Person nhat = new Person("Nhat", 22);

    entityManager.persist(dan);
    entityManager.persist(duc);
    entityManager.persist(nhat);
    System.out.println("Done!");

    System.out.println("Get Record in DB...");
    System.out.println(entityManager.getOne(Person.class, 1L));
    System.out.println(entityManager.getOne(Person.class, 2L));
    System.out.println(entityManager.getOne(Person.class, 3L));
    Thread.currentThread().join();
  }
}
