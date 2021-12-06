package training.java.object.orented;

import java.io.Reader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;
import training.java.object.orented.openclosedprincipal.Employee;
import training.java.object.orented.openclosedprincipal.JSONExporter;
import training.java.object.orented.openclosedprincipal.JsonImporter;

public class EmployeeUnitTest {

  @Test
  public void test() {
    String json = "{ \"name\": \"Dan\" }";
    Reader in = new StringReader(json);
    Employee me = new Employee(new JsonImporter(in));

    JSONExporter jsonExporter = new JSONExporter();
    me.export(jsonExporter);
    jsonExporter.toString();
  }
}