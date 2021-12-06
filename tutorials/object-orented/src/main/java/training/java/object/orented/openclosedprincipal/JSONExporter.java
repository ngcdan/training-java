package training.java.object.orented.openclosedprincipal;

public class JSONExporter implements Employee.Export {

  private String name;

  @Override
  public void storeName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "JSONExporter{" + "\"name\":\"" + name + "\"}";
  }
}