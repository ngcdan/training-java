package training.java.reflection.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import training.java.reflection.annotation.Provides;

public class H2ConnectionProvider {

  @Provides
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
  }
}