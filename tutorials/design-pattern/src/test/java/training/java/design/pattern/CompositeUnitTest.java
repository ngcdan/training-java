package training.java.design.pattern;

import org.junit.jupiter.api.Test;
import training.java.design.pattern.composite.CompanyDirectory;
import training.java.design.pattern.composite.Developer;
import training.java.design.pattern.composite.Manager;

public class CompositeUnitTest {

  @Test
  public void compositeUnitTest() throws Exception {
    Developer dev1 = new Developer("dan", "IT");
    Developer dev2 = new Developer("dan1", "IT");
    Manager manager = new Manager("Hieu", "Manager");

    CompanyDirectory it = new CompanyDirectory();
    it.addEmployees(dev1, dev2);

    CompanyDirectory directory = new CompanyDirectory();
    directory.addEmployees(manager);
    directory.addEmployees(it);

    directory.showEmployeeDetails();
  }
}