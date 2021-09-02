package training.java.reflection.provider;

import training.java.reflection.annotation.Provides;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionProvider {

  @Provides
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
  }
}
