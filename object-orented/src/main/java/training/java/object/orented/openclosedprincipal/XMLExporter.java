package training.java.object.orented.openclosedprincipal;

public class XMLExporter implements Employee.Export {
  private String name;
  @Override
  public void storeName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "XMLExporter{ <name value=\"" + name + "\"> }";
  }
}



