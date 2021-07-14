package training.java.reflection;

import org.h2.tools.Server;
import org.junit.jupiter.api.Test;
import training.java.reflection.entity.Person;
import training.java.reflection.orm.EntityManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ObjectRelationalUnitTest {
  
  @Test
  public void writingObject() throws Exception {
    Server.main("-ifNotExists");
    System.out.println("DB Launched!");
  
    Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    PreparedStatement statement =
      conn.prepareStatement("create table Person ( id int primary key, name varchar(40), age int)");
    statement.executeUpdate();
  
    EntityManager<Person> entityManager = EntityManager.of(Person.class);
    System.out.println("Writing in DB...");
    Person dan = new Person("Dan", 23);
    Person duc = new Person("Duc", 21);
    Person nhat = new Person("Nhat", 22);
  
    entityManager.persist(dan);
    entityManager.persist(duc);
    entityManager.persist(nhat);
  
    System.out.println("Done!");
    System.out.println(entityManager.getOne(Person.class, 1L));
    System.out.println(entityManager.getOne(Person.class, 2L));
    System.out.println(entityManager.getOne(Person.class, 3L));
    Thread.currentThread().join();
  }
}
