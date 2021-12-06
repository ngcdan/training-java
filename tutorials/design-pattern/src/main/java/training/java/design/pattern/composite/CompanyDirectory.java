package training.java.design.pattern.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyDirectory implements Employee {

  List<Employee> employees = new ArrayList<>();

  public void addEmployees(Employee... employees) {
    Collections.addAll(this.employees, employees);
  }

  @Override
  public void showEmployeeDetails() {}
}