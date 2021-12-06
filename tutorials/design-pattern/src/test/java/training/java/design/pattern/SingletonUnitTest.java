package training.java.design.pattern;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import training.java.design.pattern.singleton.DbSingleton;
import training.java.design.pattern.singleton.Singleton;

public class SingletonUnitTest {

  @Test
  public void runtimeSingletonUnitTest() throws Exception {
    Runtime singletonRuntime = Runtime.getRuntime();
    singletonRuntime.gc();
    System.out.println(singletonRuntime);

    Runtime anotherInstance = Runtime.getRuntime();
    System.out.println(anotherInstance);

    Assertions.assertEquals(singletonRuntime, anotherInstance);
  }

  @Test
  public void singletonUnitTest() throws Exception {
    Singleton instance = Singleton.getInstance();
    System.out.println(instance);

    Singleton anotherInstance = Singleton.getInstance();
    System.out.println(anotherInstance);

    Assertions.assertEquals(instance, anotherInstance);
  }

  @Test
  public void dbSingletonUnitTest() throws Exception {
    DbSingleton instance = DbSingleton.getInstance();
    long timeBefore = 0;
    long timeAfter = 0;

    System.out.println(instance);

    timeBefore = System.currentTimeMillis();
    Connection conn = instance.getConnection();
    timeAfter = System.currentTimeMillis();
    System.out.println(timeAfter - timeBefore);

    Statement sta;
    try {
      sta = conn.createStatement();
      int count = sta.executeUpdate("CREATE TABLE Address (ID INT, StreetName VARCHAR(20), City VARCHAR(20))");
      System.out.println("Table created.");
      sta.close();

    } catch(SQLException e) {
      e.printStackTrace();
    }

    timeBefore = System.currentTimeMillis();
    conn = instance.getConnection();
    timeAfter = System.currentTimeMillis();

    System.out.println(timeAfter - timeBefore);
  }
}